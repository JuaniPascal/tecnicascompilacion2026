package com.cppcompiler.ast;

import com.cppcompiler.semantic.Type;

public final class AstBoolLiteral extends AstExpr {
    private final boolean value;

    public AstBoolLiteral(boolean value) {
        this.value = value;
    }

    public boolean value() {
        return value;
    }

    @Override
    public Type resultType() {
        return Type.BOOL;
    }
}
