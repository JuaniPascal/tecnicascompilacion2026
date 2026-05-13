package com.cppcompiler.semantic;

import java.util.Locale;

/**
 * Diagnóstico semántico (error o advertencia) con ubicación en el archivo fuente.
 */
public record Diagnostic(Level level, String message, int line, int column) {

    public enum Level {
        ERROR,
        WARNING
    }

    @Override
    public String toString() {
        return String.format(
                Locale.ROOT,
                "[%s] linea %d, col %d: %s",
                level,
                line,
                column,
                message);
    }
}
