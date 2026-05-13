package com.cppcompiler;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
}
