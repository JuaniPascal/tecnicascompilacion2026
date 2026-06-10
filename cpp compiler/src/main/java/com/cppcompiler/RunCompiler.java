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
import java.util.ArrayList;
import java.util.Arrays;
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
import com.cppcompiler.semantic.Diagnostic;
import com.cppcompiler.semantic.SemanticAnalyzer;
import com.cppcompiler.symtab.SymbolTableBuilder;
import com.cppcompiler.tac.SimpleTacOptimizer;
import com.cppcompiler.tac.SimpleTacOptimizer.Result;
import com.cppcompiler.tac.TacGenerator;
import com.cppcompiler.tac.TacProgram;
import com.cppcompiler.util.AnsiColors;

/**
 * Pipeline completo: parser + visualización del árbol + tabla de símbolos +
 * análisis semántico (errores y warnings) + TAC (+ optimización básica).
 *
 * <p>Uso: {@code java -cp ... com.cppcompiler.RunCompiler <archivo.cpp> [--no-gui]}.
 * Por defecto se abre la ventana gráfica con el árbol sintáctico; usar {@code --no-gui}
 * para suprimirla (útil en entornos sin display).
 */
public final class RunCompiler {

    private static final String SEMANTIC_OUTPUT = "salida_compilador_semantico.txt";
    private static final String TREE_OUTPUT = "arbol_sintactico.txt";
    private static final String INTERMEDIATE_OUTPUT = "ejemplo_codigo_intermedio.txt";
    private static final String OPTIMIZED_OUTPUT = "ejemplo_codigo_optimizado.txt";

    private RunCompiler() {}

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Uso: RunCompiler <archivo.cpp> [--no-gui]");
            System.exit(2);
            return;
        }
        boolean showGui = true;
        String sourceArg = null;
        for (String a : args) {
            if ("--no-gui".equalsIgnoreCase(a)) {
                showGui = false;
            } else if (sourceArg == null) {
                sourceArg = a;
            }
        }
        if (sourceArg == null) {
            System.err.println("Uso: RunCompiler <archivo.cpp> [--no-gui]");
            System.exit(2);
            return;
        }

        Path path = Paths.get(sourceArg);
        if (!Files.isRegularFile(path)) {
            System.err.println("No existe el archivo: " + path);
            System.exit(2);
            return;
        }

        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(System.err, true, StandardCharsets.UTF_8));

        System.out.println("=== com.cppcompiler.RunCompiler (parser + arbol + tabla + semantica + TAC) ===");
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

        boolean guiShown = printAndSaveParseTree(tree, parser, path, showGui);

        SymbolTableBuilder sym = new SymbolTableBuilder();
        sym.visit(tree);
        System.out.println(SymbolTableBuilder.formatTable(sym.getRows()));
        System.out.println();
        System.out.flush();

        System.out.println(">>> Fase: analisis semantico (tipos, simbolos, AST compacto)");
        System.out.flush();

        SemanticAnalyzer sem = new SemanticAnalyzer();
        sem.visitProgram(tree);

        sem.diagnostics().printAll(System.out);
        int errs = sem.diagnostics().errorCount();
        int warns = sem.diagnostics().warningCount();
        System.out.println();
        String resumen = String.format(
                Locale.ROOT, "Resumen semantico: %d errores, %d warnings.", errs, warns);
        if (errs > 0) {
            System.out.println(AnsiColors.red(resumen));
        } else if (warns > 0) {
            System.out.println(AnsiColors.yellow(resumen));
        } else {
            System.out.println(AnsiColors.green(resumen));
        }
        System.out.flush();

        if (sem.diagnostics().hasErrors()) {
            writeSemanticReport(path, sem.diagnostics().errors(), sem.diagnostics().warnings());
            // Si la ventana del árbol está abierta, NO llamamos System.exit para no
            // matar la JVM (y con ella la ventana). El proceso queda vivo gracias al
            // EDT de Swing y termina cuando el usuario cierra la ventana.
            if (guiShown) {
                System.out.println(AnsiColors.red(
                        "Compilacion detenida por errores semanticos. Cerrá la ventana del árbol para terminar."));
                System.out.flush();
                return;
            }
            System.exit(1);
            return;
        }

        // Sin errores: limpiamos un informe previo y continuamos con la generación de TAC.
        Path semOut = semanticReportPath(path);
        Path semOutCwd = Paths.get(System.getProperty("user.dir")).resolve(SEMANTIC_OUTPUT);
        Files.deleteIfExists(semOut);
        Files.deleteIfExists(semOutCwd);
        System.out.println(AnsiColors.green("Análisis semántico: OK (tipos y ámbitos)."));
        System.out.println();
        System.out.flush();

        System.out.println(AnsiColors.green(">>> Fase: generación de código intermedio (TAC)"));
        TacGenerator gen = new TacGenerator();
        TacProgram tac = gen.generate(tree);
        List<String> raw = tac.getLines();
        String intermediateText = TacProgram.formatNumbered(
                raw,
                "Código de tres direcciones generado",
                "Archivo: " + INTERMEDIATE_OUTPUT);
        System.out.println(intermediateText);
        Path interOut = intermediatePath(path);
        writeTextFile(interOut, intermediateText);
        System.out.println(AnsiColors.green("TAC intermedio escrito en: " + interOut.toAbsolutePath()));
        System.out.println();

        System.out.println(AnsiColors.green(">>> Fase: optimización del código intermedio"));
        Result opt = SimpleTacOptimizer.optimize(raw);
        String optHeader = SimpleTacOptimizer.formatOptimizedHeader(
                raw.size(), opt.lines().size(), opt);
        String optBody = TacProgram.formatNumbered(
                opt.lines(),
                "Código de tres direcciones OPTIMIZADO",
                "Archivo: " + OPTIMIZED_OUTPUT);
        System.out.print(optHeader);
        System.out.println(optBody);
        Path optOut = optimizedPath(path);
        writeTextFile(optOut, optHeader + optBody);
        System.out.println(AnsiColors.green("TAC optimizado escrito en: " + optOut.toAbsolutePath()));
        System.out.println();
        System.out.println(AnsiColors.green("=== Compilación finalizada con éxito ==="));
    }

    private static Path intermediatePath(Path sourceFile) {
        Path parent = sourceFile.toAbsolutePath().getParent();
        if (parent == null) {
            return Paths.get(INTERMEDIATE_OUTPUT).toAbsolutePath();
        }
        return parent.resolve(INTERMEDIATE_OUTPUT);
    }

    private static Path optimizedPath(Path sourceFile) {
        Path parent = sourceFile.toAbsolutePath().getParent();
        if (parent == null) {
            return Paths.get(OPTIMIZED_OUTPUT).toAbsolutePath();
        }
        return parent.resolve(OPTIMIZED_OUTPUT);
    }

    private static void writeTextFile(Path out, String content) {
        try (PrintWriter w = new PrintWriter(Files.newBufferedWriter(out, StandardCharsets.UTF_8))) {
            w.print(content);
        } catch (IOException e) {
            System.err.println(AnsiColors.red("No se pudo escribir " + out + ": " + e.getMessage()));
        }
    }

    /**
     * Imprime el árbol en formato LISP, lo guarda en disco y abre la GUI salvo --no-gui.
     *
     * @return {@code true} si la ventana gráfica del árbol llegó a abrirse (entonces el
     *     llamador NO debe invocar {@link System#exit(int)} para no matar el JFrame).
     */
    private static boolean printAndSaveParseTree(
            ProgramContext tree, CPPSubsetParser parser, Path sourceFile, boolean showGui) {
        List<String> ruleNames = Arrays.asList(parser.getRuleNames());
        String lisp = tree.toStringTree(parser);

        System.out.println(">>> Arbol sintactico (formato LISP)");
        System.out.println("-----------------------------------");
        System.out.println(lisp);
        System.out.println();

        Path treeOut = treeOutputPath(sourceFile);
        try (PrintWriter w = new PrintWriter(Files.newBufferedWriter(treeOut, StandardCharsets.UTF_8))) {
            w.println("Archivo fuente: " + sourceFile.toAbsolutePath());
            w.println("Fecha: " + DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now()));
            w.println();
            w.println("Arbol sintactico (formato LISP):");
            w.println(lisp);
        } catch (IOException ex) {
            System.err.println("No se pudo escribir " + treeOut + ": " + ex.getMessage());
        }
        System.out.println("Arbol sintactico guardado en: " + treeOut.toAbsolutePath());
        System.out.flush();

        if (!showGui) {
            return false;
        }
        try {
            Class<?> treesCls = Class.forName("org.antlr.v4.gui.Trees");
            treesCls.getMethod("inspect", org.antlr.v4.runtime.tree.Tree.class, List.class)
                    .invoke(null, tree, ruleNames);
            System.out.println("Ventana grafica del arbol sintactico abierta (cerrarla para terminar el proceso).");
            return true;
        } catch (ClassNotFoundException ex) {
            System.err.println(
                    "Aviso: org.antlr.v4.gui.Trees no esta en el classpath; se omite la ventana. "
                            + "Agregar la dependencia 'org.antlr:antlr4:4.13.2' en pom.xml.");
        } catch (java.awt.HeadlessException ex) {
            System.err.println("Aviso: entorno headless, no se puede abrir la ventana del arbol.");
        } catch (ReflectiveOperationException ex) {
            Throwable cause = ex.getCause() != null ? ex.getCause() : ex;
            if (cause instanceof java.awt.HeadlessException) {
                System.err.println("Aviso: entorno headless, no se puede abrir la ventana del arbol.");
            } else {
                System.err.println("Aviso: no se pudo abrir la ventana del arbol: " + cause.getMessage());
            }
        }
        return false;
    }

    private static Path semanticReportPath(Path sourceFile) {
        Path parent = sourceFile.toAbsolutePath().getParent();
        if (parent == null) {
            return Paths.get(SEMANTIC_OUTPUT).toAbsolutePath();
        }
        return parent.resolve(SEMANTIC_OUTPUT);
    }

    private static Path treeOutputPath(Path sourceFile) {
        Path parent = sourceFile.toAbsolutePath().getParent();
        if (parent == null) {
            return Paths.get(TREE_OUTPUT).toAbsolutePath();
        }
        return parent.resolve(TREE_OUTPUT);
    }

    /** Informe en disco con TODOS los errores (y warnings) acumulados por el analizador. */
    private static void writeSemanticReport(Path sourceFile, List<Diagnostic> errors, List<Diagnostic> warnings) {
        Path outNextToSource = semanticReportPath(sourceFile);
        Path outInCwd = Paths.get(System.getProperty("user.dir")).resolve(SEMANTIC_OUTPUT);
        String ts = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now());
        List<Path> targets = new ArrayList<>();
        targets.add(outNextToSource);
        if (!outNextToSource.toAbsolutePath().equals(outInCwd.toAbsolutePath())) {
            targets.add(outInCwd);
        }
        for (Path out : targets) {
            writeSemanticReportToPath(out, sourceFile, errors, warnings, ts);
        }
    }

    private static void writeSemanticReportToPath(
            Path out, Path sourceFile, List<Diagnostic> errors, List<Diagnostic> warnings, String ts) {
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
            w.printf(Locale.ROOT, "  RESUMEN: %d errores, %d warnings%n", errors.size(), warnings.size());
            w.println("--------------------------------------------------------------------------------");
            w.println();
            if (!warnings.isEmpty()) {
                w.println("WARNINGS:");
                for (Diagnostic d : warnings) {
                    w.println("  " + d);
                }
                w.println();
            }
            if (!errors.isEmpty()) {
                w.println("ERRORES:");
                for (Diagnostic d : errors) {
                    w.println("  " + d);
                }
                w.println();
            }
            w.println("--------------------------------------------------------------------------------");
            w.println("  CASOS DE PRUEBA EN profesora.cpp (descomentar para activar)");
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
