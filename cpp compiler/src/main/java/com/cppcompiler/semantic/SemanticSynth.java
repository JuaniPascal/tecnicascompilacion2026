package com.cppcompiler.semantic;

import com.cppcompiler.ast.AstError;
import com.cppcompiler.ast.AstExpr;

/**
 * Atributo sintetizado genérico del SDT: expresiones aportan tipo y AST compacto;
 * el resto de nodos usan {@link #none()}.
 */
public final class SemanticSynth {

    private static final SemanticSynth ERROR = new SemanticSynth(Type.ERROR, AstError.INSTANCE);

    private final Type exprType;
    private final AstExpr expr;

    private SemanticSynth(Type exprType, AstExpr expr) {
        this.exprType = exprType;
        this.expr = expr;
    }

    public static SemanticSynth none() {
        return new SemanticSynth(null, null);
    }

    public static SemanticSynth expr(Type type, AstExpr ast) {
        return new SemanticSynth(type, ast);
    }

    /**
     * Centinela para expresiones cuyo análisis semántico falló:
     * conserva un AST de tipo {@link Type#ERROR} para no romper el flujo bottom-up.
     */
    public static SemanticSynth errorExpr() {
        return ERROR;
    }

    public boolean isExpr() {
        return exprType != null;
    }

    public boolean isError() {
        return exprType == Type.ERROR;
    }

    public Type exprType() {
        return exprType;
    }

    public AstExpr exprAst() {
        return expr;
    }
}
