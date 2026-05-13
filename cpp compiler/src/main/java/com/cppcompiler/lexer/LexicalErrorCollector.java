package com.cppcompiler.lexer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

/**
 * Acumula errores emitidos por el lexer cuando no puede tokenizar (carácter ilegal,
 * cadena sin cerrar, etc.), equivalente al listener Python de la versión anterior.
 */
public final class LexicalErrorCollector extends BaseErrorListener {

    public record LexicalError(int line, int column, String message) {}

    private final List<LexicalError> errors = new ArrayList<>();

    @Override
    public void syntaxError(
            Recognizer<?, ?> recognizer,
            Object offendingSymbol,
            int line,
            int charPositionInLine,
            String msg,
            RecognitionException e) {
        errors.add(new LexicalError(line, charPositionInLine + 1, msg));
    }

    public List<LexicalError> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }
}
