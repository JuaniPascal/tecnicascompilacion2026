package com.cppcompiler.ast;

import com.cppcompiler.semantic.Type;

/** Carácter tratado como valor entero pequeño (compatible con aritmética INT). */
public final class AstCharLiteral extends AstExpr {
    private final int codeUnit;

    public AstCharLiteral(int codeUnit) {
        this.codeUnit = codeUnit;
    }

    public int codeUnit() {
        return codeUnit;
    }

    @Override
    public Type resultType() {
        return Type.INT;
    }
}
