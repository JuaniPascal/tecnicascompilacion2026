package com.cppcompiler.ast;

import com.cppcompiler.semantic.Type;

import java.util.List;

public final class AstCallExpr extends AstExpr {
    private final String functionName;
    private final List<AstExpr> arguments;
    private final Type returnType;

    public AstCallExpr(String functionName, List<AstExpr> arguments, Type returnType) {
        this.functionName = functionName;
        this.arguments = List.copyOf(arguments);
        this.returnType = returnType;
    }

    public String functionName() {
        return functionName;
    }

    public List<AstExpr> arguments() {
        return arguments;
    }

    @Override
    public Type resultType() {
        return returnType;
    }
}
