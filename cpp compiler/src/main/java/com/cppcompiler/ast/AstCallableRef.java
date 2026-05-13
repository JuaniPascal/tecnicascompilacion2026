package com.cppcompiler.ast;

import com.cppcompiler.semantic.Type;

/** Referencia a una función antes de aplicar el sufijo {@code ()} (p. ej. {@code f} o {@code (f)}). */
public final class AstCallableRef extends AstExpr {
    private final String name;
    private final Type returnType;

    public AstCallableRef(String name, Type returnType) {
        this.name = name;
        this.returnType = returnType;
    }

    public String name() {
        return name;
    }

    @Override
    public Type resultType() {
        return returnType;
    }
}
