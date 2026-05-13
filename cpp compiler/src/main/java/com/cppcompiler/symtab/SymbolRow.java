package com.cppcompiler.symtab;

/** Fila de la tabla de símbolos para impresión. */
public record SymbolRow(
        String name,
        String type,
        String category,
        int line,
        int column,
        String scope,
        String details) {}
