package com.cppcompiler.ast;

import com.cppcompiler.semantic.Type;

public final class AstFloatLiteral extends AstExpr {
    private final double value;

    public AstFloatLiteral(double value) {
        this.value = value;
    }

    public double value() {
        return value;
    }

    @Override
    public Type resultType() {
        return Type.FLOAT;
    }
}
