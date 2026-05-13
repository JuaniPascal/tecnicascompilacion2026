package com.cppcompiler.ast;

import com.cppcompiler.semantic.Type;

/** Expresión del AST compacto (sin reglas intermedias del parse tree). */
public abstract sealed class AstExpr permits AstBinOp, AstUnaryOp, AstIntLiteral, AstFloatLiteral,
        AstBoolLiteral, AstCharLiteral, AstVarRef, AstArraySubscript, AstCallExpr, AstCallableRef {
    public abstract Type resultType();
}
