package com.cppcompiler.semantic;

import java.util.Collections;
import java.util.List;

/** Entrada de la tabla de símbolos (variable, parámetro o función). */
public final class Symbol {
    private final String name;
    private final Type type;
    private final SymbolKind kind;
    private final boolean array;
    private final int arrayLength;
    /** Para {@link SymbolKind#FUNCTION}: tipos formales en orden. */
    private final List<Type> parameterTypes;

    public Symbol(String name, Type type, SymbolKind kind, boolean array, int arrayLength, List<Type> parameterTypes) {
        this.name = name;
        this.type = type;
        this.kind = kind;
        this.array = array;
        this.arrayLength = arrayLength;
        this.parameterTypes = parameterTypes == null ? List.of() : List.copyOf(parameterTypes);
    }

    public static Symbol variable(String name, Type type, boolean array, int arrayLength) {
        return new Symbol(name, type, SymbolKind.VARIABLE, array, arrayLength, null);
    }

    public static Symbol parameter(String name, Type type, boolean array, int arrayLength) {
        return new Symbol(name, type, SymbolKind.PARAMETER, array, arrayLength, null);
    }

    public static Symbol function(String name, Type returnType, List<Type> formalTypes) {
        return new Symbol(name, returnType, SymbolKind.FUNCTION, false, 0, formalTypes);
    }

    public String name() {
        return name;
    }

    public Type type() {
        return type;
    }

    public SymbolKind kind() {
        return kind;
    }

    public boolean array() {
        return array;
    }

    public int arrayLength() {
        return arrayLength;
    }

    public List<Type> parameterTypes() {
        return Collections.unmodifiableList(parameterTypes);
    }

    /** Tipo del elemento si es arreglo; si no, el tipo escalar. */
    public Type valueType() {
        return type;
    }
}
