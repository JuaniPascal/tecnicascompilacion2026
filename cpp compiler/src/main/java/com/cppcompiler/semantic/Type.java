package com.cppcompiler.semantic;

/**
 * Tipos del lenguaje para el chequeo estático.
 * Incluye INT, FLOAT y STRING según el enunciado; BOOL y VOID cubren el subconjunto C++ del parser.
 */
public enum Type {
    INT,
    FLOAT,
    STRING,
    BOOL,
    VOID;

    public boolean isNumeric() {
        return this == INT || this == FLOAT;
    }
}
