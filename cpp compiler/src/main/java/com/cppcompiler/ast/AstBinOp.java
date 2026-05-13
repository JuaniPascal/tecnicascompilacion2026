package com.cppcompiler.ast;

import com.cppcompiler.semantic.Type;

public final class AstBinOp extends AstExpr {
    private final AstExpr left;
    private final String operator;
    private final AstExpr right;
    private final Type resultType;

    public AstBinOp(AstExpr left, String operator, AstExpr right, Type resultType) {
        this.left = left;
        this.operator = operator;
        this.right = right;
        this.resultType = resultType;
    }

    public AstExpr left() {
        return left;
    }

    public String operator() {
        return operator;
    }

    public AstExpr right() {
        return right;
    }

    @Override
    public Type resultType() {
        return resultType;
    }
}
