package com.cppcompiler.semantic;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import com.cppcompiler.util.AnsiColors;

/**
 * Acumula errores y warnings semánticos para no abortar el análisis ante el primer fallo.
 */
public final class DiagnosticCollector {

    private final List<Diagnostic> errors = new ArrayList<>();
    private final List<Diagnostic> warnings = new ArrayList<>();

    public void addError(String message, ParserRuleContext ctx) {
        errors.add(new Diagnostic(Diagnostic.Level.ERROR, message, lineOf(ctx), columnOf(ctx)));
    }

    public void addError(String message, Token tok) {
        errors.add(new Diagnostic(Diagnostic.Level.ERROR, message, lineOf(tok), columnOf(tok)));
    }

    public void addError(String message, int line, int column) {
        errors.add(new Diagnostic(Diagnostic.Level.ERROR, message, line, column));
    }

    public void addError(String message) {
        errors.add(new Diagnostic(Diagnostic.Level.ERROR, message, 0, 0));
    }

    public void addWarning(String message, ParserRuleContext ctx) {
        warnings.add(new Diagnostic(Diagnostic.Level.WARNING, message, lineOf(ctx), columnOf(ctx)));
    }

    public void addWarning(String message, Token tok) {
        warnings.add(new Diagnostic(Diagnostic.Level.WARNING, message, lineOf(tok), columnOf(tok)));
    }

    public void addWarning(String message, int line, int column) {
        warnings.add(new Diagnostic(Diagnostic.Level.WARNING, message, line, column));
    }

    public void addWarning(String message) {
        warnings.add(new Diagnostic(Diagnostic.Level.WARNING, message, 0, 0));
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public boolean hasWarnings() {
        return !warnings.isEmpty();
    }

    public int errorCount() {
        return errors.size();
    }

    public int warningCount() {
        return warnings.size();
    }

    public List<Diagnostic> errors() {
        return Collections.unmodifiableList(errors);
    }

    public List<Diagnostic> warnings() {
        return Collections.unmodifiableList(warnings);
    }

    /** Imprime warnings y luego errores, en orden de aparición, con colores. */
    public void printAll(PrintStream out) {
        for (Diagnostic w : warnings) {
            out.println(AnsiColors.yellow(w.toString()));
        }
        for (Diagnostic e : errors) {
            out.println(AnsiColors.red(e.toString()));
        }
    }

    private static int lineOf(ParserRuleContext ctx) {
        if (ctx == null || ctx.getStart() == null) {
            return 0;
        }
        return ctx.getStart().getLine();
    }

    private static int columnOf(ParserRuleContext ctx) {
        if (ctx == null || ctx.getStart() == null) {
            return 0;
        }
        return ctx.getStart().getCharPositionInLine() + 1;
    }

    private static int lineOf(Token tok) {
        return tok == null ? 0 : tok.getLine();
    }

    private static int columnOf(Token tok) {
        return tok == null ? 0 : tok.getCharPositionInLine() + 1;
    }
}
