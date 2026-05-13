package com.cppcompiler.ast;

import com.cppcompiler.semantic.Type;

public final class AstVarRef extends AstExpr {
    private final String name;
    private final Type refType;
    private final boolean array;

    public AstVarRef(String name, Type refType, boolean array) {
        this.name = name;
        this.refType = refType;
        this.array = array;
    }

    public String name() {
        return name;
    }

    public boolean isArray() {
        return array;
    }

    @Override
    public Type resultType() {
        return refType;
    }
}
