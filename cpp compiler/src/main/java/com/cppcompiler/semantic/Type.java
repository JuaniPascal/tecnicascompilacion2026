package com.cppcompiler.semantic;

/**
 * Tipos del lenguaje para el chequeo estático.
 * INT, FLOAT, STRING según el enunciado; BOOL y VOID cubren el subconjunto C++ del parser.
 * ERROR es un sentinel que matchea con cualquier tipo para evitar errores en cascada.
 */
public enum Type {
    INT,
    FLOAT,
    STRING,
    BOOL,
    VOID,
    ERROR;

    public boolean isNumeric() {
        return this == INT || this == FLOAT || this == ERROR;
    }
}
