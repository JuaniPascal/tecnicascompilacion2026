package com.cppcompiler;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import com.cppcompiler.lexer.CPPSubsetLexer;
import com.cppcompiler.parser.CPPSubsetParser;
import com.cppcompiler.parser.CPPSubsetParser.ProgramContext;
import com.cppcompiler.semantic.SemanticAnalyzer;
import com.cppcompiler.symtab.SymbolTableBuilder;
import com.cppcompiler.tac.SimpleTacOptimizer;
import com.cppcompiler.tac.SimpleTacOptimizer.Result;
import com.cppcompiler.tac.TacGenerator;
import com.cppcompiler.tac.TacProgram;

/**
 * Análisis sintáctico + tabla de símbolos + TAC (+ optimización básica).
 *
 * <p>Uso: {@code java -cp ... com.cppcompiler.RunCompiler archivo.cpp}
 */
public final class RunCompiler {

    private static final String SEMANTIC_OUTPUT = "salida_compilador_semantico.txt";

    private RunCompiler() {}

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Uso: RunCompiler <archivo.cpp>");
            System.exit(2);
            return;
        }
        Path path = Paths.get(args[0]);
        if (!Files.isRegularFile(path)) {
            System.err.println("No existe el archivo: " + path);
            System.exit(2);
            return;
        }

        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(System.err, true, StandardCharsets.UTF_8));

        System.out.println("=== com.cppcompiler.RunCompiler (parser + tabla + semantica + TAC) ===");
        System.out.println("Archivo: " + path.toAbsolutePath());
        System.out.println();

        CPPSubsetLexer lexer = new CPPSubsetLexer(CharStreams.fromPath(path, StandardCharsets.UTF_8));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CPPSubsetParser parser = new CPPSubsetParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(
                new BaseErrorListener() {
                    @Override
                    public void syntaxError(
                            Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line,
                            int charPositionInLine,
                            String msg,
                            RecognitionException e) {
                        System.err.printf(
                                Locale.ROOT,
                                "Error sintáctico línea %d, columna %d: %s%n",
                                line,
                                charPositionInLine + 1,
                                msg);
                    }
                });

        ProgramContext tree = parser.program();
        if (parser.getNumberOfSyntaxErrors() > 0) {
            System.exit(1);
            return;
        }

        SymbolTableBuilder sym = new SymbolTableBuilder();
        sym.visit(tree);
        System.out.println(SymbolTableBuilder.formatTable(sym.getRows()));
        System.out.println();
        System.out.flush();

        System.out.println(">>> Fase: analisis semantico (tipos, simbolos, AST compacto)");
        System.out.flush();

        SemanticAnalyzer sem = new SemanticAnalyzer();
        try {
            sem.visitProgram(tree);
            System.out.println("Análisis semántico: OK (tipos y ámbitos).");
            Path semOut = semanticReportPath(path);
            Path semOutCwd = Paths.get(System.getProperty("user.dir")).resolve(SEMANTIC_OUTPUT);
            Files.deleteIfExists(semOut);
            Files.deleteIfExists(semOutCwd);
        } catch (Throwable ex) {
            String msg = ex.getMessage() != null ? ex.getMessage() : ex.getClass().getName();
            System.err.println("Error semántico: " + msg);
            System.out.println();
            System.out.println("*** ERROR SEMANTICO *** " + msg);
            ex.printStackTrace(System.err);
            writeSemanticErrorReport(path, msg, ex);
            System.exit(1);
            return;
        }
        System.out.println();
        System.out.flush();

        TacGenerator gen = new TacGenerator();
        TacProgram tac = gen.generate(tree);
        List<String> raw = tac.getLines();
        System.out.println(
                TacProgram.formatNumbered(
                        raw,
                        "Código de tres direcciones generado",
                        "Archivo: ejemplo_codigo_intermedio.txt"));

        Result opt = SimpleTacOptimizer.optimize(raw);
        System.out.print(
                SimpleTacOptimizer.formatOptimizedHeader(
                        raw.size(), opt.lines().size(), opt.foldedExpressions()));
        System.out.println(
                TacProgram.formatNumbered(
                        opt.lines(),
                        "Código de tres direcciones generado",
                        "Archivo: ejemplo_codigo_optimizado.txt"));
    }

    private static Path semanticReportPath(Path sourceFile) {
        Path parent = sourceFile.toAbsolutePath().getParent();
        if (parent == null) {
            return Paths.get(SEMANTIC_OUTPUT).toAbsolutePath();
        }
        return parent.resolve(SEMANTIC_OUTPUT);
    }

    /**
     * Informe visible en disco cuando falla el analizador semántico.
     */
    private static void writeSemanticErrorReport(Path sourceFile, String errorMessage, Throwable cause) {
        Path outNextToSource = semanticReportPath(sourceFile);
        Path outInCwd = Paths.get(System.getProperty("user.dir")).resolve(SEMANTIC_OUTPUT);
        String ts = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now());
        writeSemanticErrorReportToPath(outNextToSource, sourceFile, errorMessage, cause, ts);
        if (!outNextToSource.toAbsolutePath().equals(outInCwd.toAbsolutePath())) {
            writeSemanticErrorReportToPath(outInCwd, sourceFile, errorMessage, cause, ts);
        }
    }

    private static void writeSemanticErrorReportToPath(
            Path out, Path sourceFile, String errorMessage, Throwable cause, String ts) {
        try (PrintWriter w = new PrintWriter(Files.newBufferedWriter(out, StandardCharsets.UTF_8))) {
            w.println("################################################################################");
            w.println("##                                                                            ##");
            w.println("##   >>>  SALIDA DEL ANALIZADOR SEMANTICO  —  COMPILACION DETENIDA  <<<       ##");
            w.println("##                                                                            ##");
            w.println("################################################################################");
            w.println();
            w.println("Fecha y hora: " + ts);
            w.println("Archivo fuente: " + sourceFile.toAbsolutePath());
            w.println("Informe generado: " + out.toAbsolutePath());
            w.println();
            w.println("--------------------------------------------------------------------------------");
            w.println("  MENSAJE DEL ERROR (primer fallo detectado)");
            w.println("--------------------------------------------------------------------------------");
            w.println(errorMessage);
            w.println();
            if (cause != null) {
                w.println("--------------------------------------------------------------------------------");
                w.println("  PILA (stack trace)");
                w.println("--------------------------------------------------------------------------------");
                cause.printStackTrace(w);
                w.println();
            }
            w.println("--------------------------------------------------------------------------------");
            w.println("  CASOS DE PRUEBA EN profesora.cpp (activar uno a la vez)");
            w.println("--------------------------------------------------------------------------------");
            w.println("[ERR-1] variableQueNuncaSeDeclaro = ...  → identificador no declarado");
            w.println("[ERR-2] temp = true;                     → tipos incompatibles (bool → int)");
            w.println("[ERR-3] estado = sumar(temp);            → aridad incorrecta en llamada");
            w.println("[ERR-4] temp = temp && true;             → && exige BOOL en ambos lados");
            w.println("[ERR-5] return true;                     → return incompatible con int");
            w.println();
            w.println("################################################################################");
        } catch (IOException e) {
            System.err.println("No se pudo escribir " + out + ": " + e.getMessage());
        }
        System.out.println("Informe semantico escrito en: " + out.toAbsolutePath());
        System.out.flush();
    }
}
