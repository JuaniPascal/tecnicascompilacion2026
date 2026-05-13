package com.cppcompiler.lexer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;

/**
 * Punto de entrada: lee un archivo fuente y lista los tokens del canal por defecto.
 *
 * <p>Uso:
 *
 * <pre>
 *   java -cp ... com.cppcompiler.lexer.RunCppLexer archivo.cpp
 *   java -cp ... com.cppcompiler.lexer.RunCppLexer archivo.cpp --with-hidden
 *   java -cp ... com.cppcompiler.lexer.RunCppLexer archivo.cpp --json
 * </pre>
 *
 * Con Maven (desde esta carpeta): {@code mvn -q compile exec:java -Dexec.args="ejemplo_entrada.cpp"}
 */
public final class RunCppLexer {

    private RunCppLexer() {}

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Uso: RunCppLexer <archivo.cpp> [--with-hidden] [--json]");
            System.exit(2);
            return;
        }

        Path path = Paths.get(args[0]);
        boolean withHidden = false;
        boolean asJson = false;
        for (int i = 1; i < args.length; i++) {
            if ("--with-hidden".equals(args[i])) {
                withHidden = true;
            } else if ("--json".equals(args[i])) {
                asJson = true;
            }
        }

        if (!Files.isRegularFile(path)) {
            System.err.println("Error: no existe el archivo " + path);
            System.exit(2);
            return;
        }

        String source = Files.readString(path, StandardCharsets.UTF_8);
        LexicalErrorCollector errCollector = new LexicalErrorCollector();
        CPPSubsetLexer lexer = new CPPSubsetLexer(CharStreams.fromString(source));
        lexer.removeErrorListeners();
        lexer.addErrorListener(errCollector);

        List<TokenRecord> records = collectTokens(lexer, withHidden);

        System.out.print(formatOutput(records, asJson));

        if (errCollector.hasErrors()) {
            System.err.println();
            System.err.println("--- Errores léxicos (ANTLR) ---");
            for (LexicalErrorCollector.LexicalError e : errCollector.getErrors()) {
                System.err.printf(
                        Locale.ROOT, "  línea %d, columna %d: %s%n", e.line(), e.column(), e.message());
            }
        }

        boolean badToken =
                records.stream().anyMatch(r -> "ERROR_CHAR".equals(r.typeName()));
        if (badToken) {
            System.err.println();
            System.err.println("--- Tokens marcados como error ---");
            for (TokenRecord r : records) {
                if ("ERROR_CHAR".equals(r.typeName())) {
                    System.err.printf(
                            Locale.ROOT,
                            "  línea %d, col %d: %s '%s'%n",
                            r.line(),
                            r.column(),
                            r.typeName(),
                            escapeLexeme(r.lexeme()));
                }
            }
        }

        int code = errCollector.hasErrors() || badToken ? 1 : 0;
        System.exit(code);
    }

    public record TokenRecord(
            int line, int column, String typeName, String lexeme, String channelName) {

        static TokenRecord from(Token t, CPPSubsetLexer lexer, boolean includeChannel) {
            int type = t.getType();
            org.antlr.v4.runtime.Vocabulary voc = lexer.getVocabulary();
            String name = voc.getSymbolicName(type);
            if (name == null) {
                name = voc.getLiteralName(type);
            }
            if (name == null) {
                name = String.valueOf(type);
            }
            String ch =
                    includeChannel && t.getChannel() < lexer.getChannelNames().length
                            ? lexer.getChannelNames()[t.getChannel()]
                            : null;
            return new TokenRecord(t.getLine(), t.getCharPositionInLine() + 1, name, t.getText(), ch);
        }
    }

    static List<TokenRecord> collectTokens(CPPSubsetLexer lexer, boolean withHidden) {
        List<TokenRecord> out = new ArrayList<>();
        for (Token t : lexer.getAllTokens()) {
            if (t == null || t.getType() == Token.EOF) {
                continue;
            }
            int ch = t.getChannel();
            if (!withHidden) {
                if (ch != Lexer.DEFAULT_TOKEN_CHANNEL) {
                    continue;
                }
            }
            TokenRecord r = TokenRecord.from(t, lexer, withHidden);
            out.add(r);
        }
        return out;
    }

    static String formatOutput(List<TokenRecord> records, boolean asJson) {
        if (asJson) {
            StringBuilder sb = new StringBuilder();
            sb.append("[\n");
            for (int i = 0; i < records.size(); i++) {
                TokenRecord r = records.get(i);
                sb.append("  {");
                sb.append("\"line\":").append(r.line()).append(",");
                sb.append("\"column\":").append(r.column()).append(",");
                sb.append("\"type\":\"").append(escapeJson(r.typeName())).append("\",");
                sb.append("\"lexeme\":\"").append(escapeJson(r.lexeme())).append("\"");
                if (r.channelName() != null) {
                    sb.append(",\"channel\":\"").append(escapeJson(r.channelName())).append("\"");
                }
                if ("ERROR_CHAR".equals(r.typeName())) {
                    sb.append(",\"error\":\"carácter no reconocido en el subconjunto léxico\"");
                }
                sb.append("}");
                if (i < records.size() - 1) {
                    sb.append(",");
                }
                sb.append("\n");
            }
            sb.append("]\n");
            return sb.toString();
        }

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(Locale.ROOT, "%5s %4s  %-18s  Lexema%n", "Linea", "Col", "Tipo"));
        sb.append("-".repeat(60)).append('\n');
        for (TokenRecord r : records) {
            sb.append(String.format(
                    Locale.ROOT,
                    "%5d %4d  %-18s  %s%n",
                    r.line(),
                    r.column(),
                    r.typeName(),
                    escapeLexeme(r.lexeme())));
        }
        return sb.toString();
    }

    static String escapeLexeme(String text) {
        if (text == null) {
            return "";
        }
        String t = text.replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t");
        int max = 64;
        if (t.length() > max) {
            return t.substring(0, max - 3) + "...";
        }
        return t;
    }

    static String escapeJson(String s) {
        if (s == null) {
            return "";
        }
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}
