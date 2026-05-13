package com.cppcompiler.ast;

import com.cppcompiler.semantic.Type;

/**
 * Marcador en el AST para expresiones cuyo análisis semántico falló.
 * Lleva tipo {@link Type#ERROR} para evitar errores en cascada.
 */
public final class AstError extends AstExpr {
    public static final AstError INSTANCE = new AstError();

    private AstError() {}

    public static AstError placeholder() {
        return INSTANCE;
    }

    @Override
    public Type resultType() {
        return Type.ERROR;
    }
}
