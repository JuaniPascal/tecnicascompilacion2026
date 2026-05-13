package com.cppcompiler.ast;

import com.cppcompiler.semantic.Type;

public final class AstIntLiteral extends AstExpr {
    private final long value;

    public AstIntLiteral(long value) {
        this.value = value;
    }

    public long value() {
        return value;
    }

    @Override
    public Type resultType() {
        return Type.INT;
    }
}
