package com.cppcompiler.ast;

import com.cppcompiler.semantic.Type;

public final class AstUnaryOp extends AstExpr {
    private final String operator;
    private final AstExpr operand;
    private final Type resultType;

    public AstUnaryOp(String operator, AstExpr operand, Type resultType) {
        this.operator = operator;
        this.operand = operand;
        this.resultType = resultType;
    }

    public String operator() {
        return operator;
    }

    public AstExpr operand() {
        return operand;
    }

    @Override
    public Type resultType() {
        return resultType;
    }
}
