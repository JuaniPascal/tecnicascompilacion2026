package com.cppcompiler.ast;

import com.cppcompiler.semantic.Type;

public final class AstArraySubscript extends AstExpr {
    private final AstExpr array;
    private final AstExpr index;
    private final Type elementType;

    public AstArraySubscript(AstExpr array, AstExpr index, Type elementType) {
        this.array = array;
        this.index = index;
        this.elementType = elementType;
    }

    public AstExpr array() {
        return array;
    }

    public AstExpr index() {
        return index;
    }

    @Override
    public Type resultType() {
        return elementType;
    }
}
