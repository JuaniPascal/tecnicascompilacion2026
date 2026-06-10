package com.cppcompiler.semantic;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.cppcompiler.ast.AstArraySubscript;
import com.cppcompiler.ast.AstBinOp;
import com.cppcompiler.ast.AstBoolLiteral;
import com.cppcompiler.ast.AstCallableRef;
import com.cppcompiler.ast.AstCallExpr;
import com.cppcompiler.ast.AstCharLiteral;
import com.cppcompiler.ast.AstError;
import com.cppcompiler.ast.AstExpr;
import com.cppcompiler.ast.AstFloatLiteral;
import com.cppcompiler.ast.AstIntLiteral;
import com.cppcompiler.ast.AstUnaryOp;
import com.cppcompiler.ast.AstVarRef;
import com.cppcompiler.parser.CPPSubsetParser;
import com.cppcompiler.parser.CPPSubsetParserBaseVisitor;

/**
 * Análisis semántico con SDT bottom-up: cada {@code visit*} devuelve un
 * {@link SemanticSynth} con tipo y AST compacto para expresiones.
 *
 * <p>Los problemas se reportan al {@link DiagnosticCollector} y el análisis CONTINÚA
 * (no se aborta), de modo que un único pase reporte todos los errores y warnings.
 */
public final class SemanticAnalyzer extends CPPSubsetParserBaseVisitor<SemanticSynth> {

    private final ScopedSymbolTable table = new ScopedSymbolTable();
    private final DiagnosticCollector diagnostics = new DiagnosticCollector();
    private Type currentFunctionReturn = Type.VOID;
    /** Profundidad de bucles activos. break/continue solo son válidos si > 0. */
    private int loopDepth = 0;

    public ScopedSymbolTable symbolTable() {
        return table;
    }

    public DiagnosticCollector diagnostics() {
        return diagnostics;
    }

    @Override
    public SemanticSynth visitProgram(CPPSubsetParser.ProgramContext ctx) {
        table.enterScope();
        for (int i = 0; i < ctx.getChildCount(); i++) {
            ParseTree c = ctx.getChild(i);
            if (c instanceof CPPSubsetParser.FuncDeclContext fd) {
                registerFunctionSignature(fd);
            }
        }
        for (int i = 0; i < ctx.getChildCount(); i++) {
            ParseTree c = ctx.getChild(i);
            if (c instanceof CPPSubsetParser.VarDeclContext vd) {
                visitVarDecl(vd);
            } else if (c instanceof CPPSubsetParser.FuncDeclContext fd) {
                visitFuncDeclBody(fd);
            }
        }
        Map<String, Symbol> globalScope = table.exitScopeAndCollect();
        emitUnusedWarnings(globalScope);
        return SemanticSynth.none();
    }

    private void registerFunctionSignature(CPPSubsetParser.FuncDeclContext ctx) {
        String name = ctx.IDENTIFIER().getText();
        Type ret = mapTypeName(ctx.typeName());
        List<Type> formals = new ArrayList<>();
        if (ctx.paramList() != null) {
            for (CPPSubsetParser.ParamContext p : ctx.paramList().param()) {
                formals.add(mapTypeName(p.typeName()));
            }
        }
        int line = ctx.IDENTIFIER().getSymbol().getLine();
        int col = ctx.IDENTIFIER().getSymbol().getCharPositionInLine() + 1;
        Symbol fn = Symbol.function(name, ret, formals, line, col);
        // Las funciones declaradas en el ámbito global no se reportan como "no usadas"
        // (main, etc. no se invocan desde el propio archivo); las marcamos como usadas.
        fn.markUsed();
        if (!table.define(fn)) {
            diagnostics.addError("símbolo duplicado en el mismo ámbito: " + name, ctx);
        }
    }

    private void visitFuncDeclBody(CPPSubsetParser.FuncDeclContext ctx) {
        currentFunctionReturn = mapTypeName(ctx.typeName());
        table.enterScope();
        if (ctx.paramList() != null) {
            for (CPPSubsetParser.ParamContext p : ctx.paramList().param()) {
                defineVariableFromParam(p);
            }
        }
        // Visitamos el cuerpo sin abrir scope adicional (los parámetros y locales comparten ámbito).
        for (CPPSubsetParser.StatementContext st : ctx.block().statement()) {
            visitStatement(st);
        }
        // Función no-void: ¿el cuerpo garantiza un return?
        if (currentFunctionReturn != Type.VOID && !blockGuaranteesReturn(ctx.block())) {
            diagnostics.addWarning(
                    "la función '" + ctx.IDENTIFIER().getText()
                            + "' no garantiza un return alcanzable en su tipo de retorno "
                            + currentFunctionReturn,
                    ctx);
        }
        Map<String, Symbol> funcScope = table.exitScopeAndCollect();
        emitUnusedWarnings(funcScope);
        currentFunctionReturn = Type.VOID;
    }

    private void defineVariableFromParam(CPPSubsetParser.ParamContext ctx) {
        Type t = mapTypeName(ctx.typeName());
        String name = ctx.IDENTIFIER().getText();
        int line = ctx.IDENTIFIER().getSymbol().getLine();
        int col = ctx.IDENTIFIER().getSymbol().getCharPositionInLine() + 1;
        if (t == Type.VOID) {
            diagnostics.addError("parámetro void no permitido: " + name, ctx);
            t = Type.ERROR;
        }
        checkShadowing(name, ctx);
        if (!table.define(Symbol.parameter(name, t, false, 0, line, col))) {
            diagnostics.addError("parámetro duplicado en la misma función: " + name, ctx);
        }
    }

    @Override
    public SemanticSynth visitVarDecl(CPPSubsetParser.VarDeclContext ctx) {
        Type t = mapTypeName(ctx.typeName());
        String name = ctx.IDENTIFIER().getText();
        int line = ctx.IDENTIFIER().getSymbol().getLine();
        int col = ctx.IDENTIFIER().getSymbol().getCharPositionInLine() + 1;
        if (t == Type.VOID) {
            diagnostics.addError("variable con tipo void: " + name, ctx);
            t = Type.ERROR;
        }
        boolean array = ctx.arrayDim() != null;
        int dim = 0;
        if (array) {
            dim = Integer.parseInt(ctx.arrayDim().INT_LITERAL().getText());
        }
        checkShadowing(name, ctx);
        if (!table.define(Symbol.variable(name, t, array, dim, line, col))) {
            diagnostics.addError("símbolo duplicado en el mismo ámbito: " + name, ctx);
        }
        return SemanticSynth.none();
    }

    @Override
    public SemanticSynth visitBlock(CPPSubsetParser.BlockContext ctx) {
        table.enterScope();
        for (CPPSubsetParser.StatementContext st : ctx.statement()) {
            visitStatement(st);
        }
        Map<String, Symbol> scope = table.exitScopeAndCollect();
        emitUnusedWarnings(scope);
        return SemanticSynth.none();
    }

    @Override
    public SemanticSynth visitStatement(CPPSubsetParser.StatementContext ctx) {
        if (ctx.assignment() != null) {
            return visitAssignment(ctx.assignment());
        }
        if (ctx.varDecl() != null) {
            return visitVarDecl(ctx.varDecl());
        }
        if (ctx.ifStmt() != null) {
            return visitIfStmt(ctx.ifStmt());
        }
        if (ctx.whileStmt() != null) {
            return visitWhileStmt(ctx.whileStmt());
        }
        if (ctx.forStmt() != null) {
            return visitForStmt(ctx.forStmt());
        }
        if (ctx.breakStmt() != null) {
            return visitBreakStmt(ctx.breakStmt());
        }
        if (ctx.continueStmt() != null) {
            return visitContinueStmt(ctx.continueStmt());
        }
        if (ctx.returnStmt() != null) {
            return visitReturnStmt(ctx.returnStmt());
        }
        if (ctx.block() != null) {
            return visitBlock(ctx.block());
        }
        return SemanticSynth.none();
    }

    @Override
    public SemanticSynth visitAssignment(CPPSubsetParser.AssignmentContext ctx) {
        LvalueSynth lhs = analyzeLvalue(ctx.lvalue());
        SemanticSynth rhs = visit(ctx.expr());
        if (!rhs.isExpr()) {
            diagnostics.addError("lado derecho sin valor de expresión", ctx);
            return SemanticSynth.none();
        }
        if (!isAssignable(lhs.type(), rhs.exprType())) {
            diagnostics.addError(
                    "tipos incompatibles en asignación: " + rhs.exprType() + " no asignable a " + lhs.type(),
                    ctx);
        }
        return SemanticSynth.none();
    }

    @Override
    public SemanticSynth visitIfStmt(CPPSubsetParser.IfStmtContext ctx) {
        SemanticSynth cond = visit(ctx.expr());
        if (!cond.isExpr()) {
            diagnostics.addError("condición de if inválida", ctx);
        } else if (!isConditionType(cond.exprType())) {
            diagnostics.addError(
                    "tipo incompatible en condición de if: " + cond.exprType() + " (se esperaba BOOL o numérico)",
                    ctx);
        }
        // Bloque "then"
        visitBlock(ctx.block(0));
        // Bloque "else" (opcional)
        if (ctx.block().size() > 1) {
            visitBlock(ctx.block(1));
        }
        return SemanticSynth.none();
    }

    @Override
    public SemanticSynth visitWhileStmt(CPPSubsetParser.WhileStmtContext ctx) {
        SemanticSynth cond = visit(ctx.expr());
        if (!cond.isExpr()) {
            diagnostics.addError("condición de while inválida", ctx);
        } else if (!isConditionType(cond.exprType())) {
            diagnostics.addError(
                    "tipo incompatible en condición de while: " + cond.exprType() + " (se esperaba BOOL o numérico)",
                    ctx);
        }
        loopDepth++;
        visitBlock(ctx.block());
        loopDepth--;
        return SemanticSynth.none();
    }

    @Override
    public SemanticSynth visitForStmt(CPPSubsetParser.ForStmtContext ctx) {
        // El init de un "for" puede declarar una variable nueva visible solo dentro del bucle.
        // Abrimos un scope dedicado para el for completo (init + cond + update + body).
        table.enterScope();
        if (ctx.forInit() != null) {
            analyzeForInit(ctx.forInit());
        }
        if (ctx.expr() != null) {
            SemanticSynth cond = visit(ctx.expr());
            if (!cond.isExpr()) {
                diagnostics.addError("condición de for inválida", ctx.expr());
            } else if (!isConditionType(cond.exprType())) {
                diagnostics.addError(
                        "tipo incompatible en condición de for: " + cond.exprType()
                                + " (se esperaba BOOL o numérico)",
                        ctx.expr());
            }
        }
        if (ctx.forUpdate() != null) {
            analyzeForUpdate(ctx.forUpdate());
        }
        loopDepth++;
        // El cuerpo abre su propio scope; conservamos el scope del init/update al exterior.
        visitBlock(ctx.block());
        loopDepth--;
        Map<String, Symbol> forScope = table.exitScopeAndCollect();
        emitUnusedWarnings(forScope);
        return SemanticSynth.none();
    }

    @Override
    public SemanticSynth visitBreakStmt(CPPSubsetParser.BreakStmtContext ctx) {
        if (loopDepth == 0) {
            diagnostics.addError("'break' fuera de un bucle", ctx);
        }
        return SemanticSynth.none();
    }

    @Override
    public SemanticSynth visitContinueStmt(CPPSubsetParser.ContinueStmtContext ctx) {
        if (loopDepth == 0) {
            diagnostics.addError("'continue' fuera de un bucle", ctx);
        }
        return SemanticSynth.none();
    }

    private void analyzeForInit(CPPSubsetParser.ForInitContext ctx) {
        if (ctx instanceof CPPSubsetParser.ForInitDeclContext fid) {
            Type t = mapTypeName(fid.typeName());
            String name = fid.IDENTIFIER().getText();
            int line = fid.IDENTIFIER().getSymbol().getLine();
            int col = fid.IDENTIFIER().getSymbol().getCharPositionInLine() + 1;
            if (t == Type.VOID) {
                diagnostics.addError("variable con tipo void en for-init: " + name, fid);
                t = Type.ERROR;
            }
            checkShadowing(name, fid);
            if (!table.define(Symbol.variable(name, t, false, 0, line, col))) {
                diagnostics.addError("símbolo duplicado en el mismo ámbito: " + name, fid);
            }
            SemanticSynth rhs = visit(fid.expr());
            if (!rhs.isExpr()) {
                diagnostics.addError("expresión de inicialización inválida en for", fid);
            } else if (!isAssignable(t, rhs.exprType())) {
                diagnostics.addError(
                        "tipos incompatibles en for-init: " + rhs.exprType() + " no asignable a " + t,
                        fid);
            }
        } else if (ctx instanceof CPPSubsetParser.ForInitAssignContext fia) {
            // Se reusa la lógica de asignación.
            LvalueSynth lhs = analyzeLvalue(fia.lvalue());
            SemanticSynth rhs = visit(fia.expr());
            if (!rhs.isExpr()) {
                diagnostics.addError("lado derecho sin valor en for-init", fia);
                return;
            }
            if (!isAssignable(lhs.type(), rhs.exprType())) {
                diagnostics.addError(
                        "tipos incompatibles en for-init: " + rhs.exprType() + " no asignable a " + lhs.type(),
                        fia);
            }
        }
    }

    private void analyzeForUpdate(CPPSubsetParser.ForUpdateContext ctx) {
        LvalueSynth lhs = analyzeLvalue(ctx.lvalue());
        SemanticSynth rhs = visit(ctx.expr());
        if (!rhs.isExpr()) {
            diagnostics.addError("lado derecho sin valor en for-update", ctx);
            return;
        }
        if (!isAssignable(lhs.type(), rhs.exprType())) {
            diagnostics.addError(
                    "tipos incompatibles en for-update: " + rhs.exprType() + " no asignable a " + lhs.type(),
                    ctx);
        }
    }

    @Override
    public SemanticSynth visitReturnStmt(CPPSubsetParser.ReturnStmtContext ctx) {
        if (ctx.expr() == null) {
            if (currentFunctionReturn != Type.VOID) {
                diagnostics.addError("return sin expresión en función no void", ctx);
            }
            return SemanticSynth.none();
        }
        SemanticSynth v = visit(ctx.expr());
        if (!v.isExpr()) {
            diagnostics.addError("return con expresión inválida", ctx);
            return SemanticSynth.none();
        }
        checkReturnCompatible(currentFunctionReturn, v.exprType(), ctx);
        return SemanticSynth.none();
    }

    @Override
    public SemanticSynth visitExpr(CPPSubsetParser.ExprContext ctx) {
        return visit(ctx.orExpr());
    }

    @Override
    public SemanticSynth visitOrExpr(CPPSubsetParser.OrExprContext ctx) {
        SemanticSynth acc = visit(ctx.andExpr(0));
        for (int i = 1; i < ctx.andExpr().size(); i++) {
            SemanticSynth r = visit(ctx.andExpr(i));
            acc = combineLogical(acc, r, "||", ctx);
        }
        return acc;
    }

    @Override
    public SemanticSynth visitAndExpr(CPPSubsetParser.AndExprContext ctx) {
        SemanticSynth acc = visit(ctx.eqExpr(0));
        for (int i = 1; i < ctx.eqExpr().size(); i++) {
            SemanticSynth r = visit(ctx.eqExpr(i));
            acc = combineLogical(acc, r, "&&", ctx);
        }
        return acc;
    }

    @Override
    public SemanticSynth visitEqExpr(CPPSubsetParser.EqExprContext ctx) {
        SemanticSynth acc = visit(ctx.relExpr(0));
        for (int i = 1; i < ctx.relExpr().size(); i++) {
            String op = ((TerminalNode) ctx.getChild(2 * i - 1)).getText();
            SemanticSynth r = visit(ctx.relExpr(i));
            acc = combineEquality(acc, r, op, ctx);
        }
        return acc;
    }

    @Override
    public SemanticSynth visitRelExpr(CPPSubsetParser.RelExprContext ctx) {
        SemanticSynth acc = visit(ctx.addExpr(0));
        for (int i = 1; i < ctx.addExpr().size(); i++) {
            String op = ((TerminalNode) ctx.getChild(2 * i - 1)).getText();
            SemanticSynth r = visit(ctx.addExpr(i));
            acc = combineRelational(acc, r, op, ctx);
        }
        return acc;
    }

    @Override
    public SemanticSynth visitAddExpr(CPPSubsetParser.AddExprContext ctx) {
        SemanticSynth acc = visit(ctx.mulExpr(0));
        for (int i = 1; i < ctx.mulExpr().size(); i++) {
            String op = ((TerminalNode) ctx.getChild(2 * i - 1)).getText();
            SemanticSynth r = visit(ctx.mulExpr(i));
            acc = combineArithmetic(acc, r, op, ctx);
        }
        return acc;
    }

    @Override
    public SemanticSynth visitMulExpr(CPPSubsetParser.MulExprContext ctx) {
        SemanticSynth acc = visit(ctx.unary(0));
        for (int i = 1; i < ctx.unary().size(); i++) {
            String op = ((TerminalNode) ctx.getChild(2 * i - 1)).getText();
            SemanticSynth r = visit(ctx.unary(i));
            if ("%".equals(op)) {
                acc = combineMod(acc, r, ctx);
            } else {
                acc = combineArithmetic(acc, r, op, ctx);
            }
        }
        return acc;
    }

    @Override
    public SemanticSynth visitUnary(CPPSubsetParser.UnaryContext ctx) {
        SemanticSynth inner = visit(ctx.postfix());
        if (ctx.NOT() != null) {
            if (!requireBool(inner, "!", ctx)) {
                return SemanticSynth.errorExpr();
            }
            AstExpr ast = new AstUnaryOp("!", inner.exprAst(), Type.BOOL);
            return SemanticSynth.expr(Type.BOOL, ast);
        }
        if (ctx.MINUS() != null) {
            if (!requireNumeric(inner, "-", ctx)) {
                return SemanticSynth.errorExpr();
            }
            Type t = inner.exprType();
            AstExpr ast = new AstUnaryOp("-", inner.exprAst(), t);
            return SemanticSynth.expr(t, ast);
        }
        if (ctx.PLUS() != null) {
            if (!requireNumeric(inner, "+", ctx)) {
                return SemanticSynth.errorExpr();
            }
            Type t = inner.exprType();
            AstExpr ast = new AstUnaryOp("+", inner.exprAst(), t);
            return SemanticSynth.expr(t, ast);
        }
        return inner;
    }

    @Override
    public SemanticSynth visitPostfix(CPPSubsetParser.PostfixContext ctx) {
        return buildPostfix(ctx.atom(), ctx.postfixSuffix());
    }

    @Override
    public SemanticSynth visitAtom(CPPSubsetParser.AtomContext ctx) {
        if (ctx.literal() != null) {
            return visitLiteral(ctx.literal());
        }
        if (ctx.IDENTIFIER() != null) {
            String name = ctx.IDENTIFIER().getText();
            Symbol sym = resolveAndMark(name);
            if (sym == null) {
                diagnostics.addError("identificador no declarado: " + name, ctx);
                return SemanticSynth.errorExpr();
            }
            if (sym.kind() == SymbolKind.FUNCTION) {
                return SemanticSynth.expr(sym.type(), new AstCallableRef(name, sym.type()));
            }
            if (sym.array()) {
                diagnostics.addError("el arreglo '" + name + "' requiere subíndice en una expresión", ctx);
                return SemanticSynth.errorExpr();
            }
            AstExpr ref = new AstVarRef(name, sym.type(), false);
            return SemanticSynth.expr(sym.type(), ref);
        }
        return visit(ctx.expr());
    }

    @Override
    public SemanticSynth visitLiteral(CPPSubsetParser.LiteralContext ctx) {
        if (ctx.INT_LITERAL() != null) {
            long v = Long.parseLong(ctx.INT_LITERAL().getText());
            return SemanticSynth.expr(Type.INT, new AstIntLiteral(v));
        }
        if (ctx.FLOAT_LITERAL() != null) {
            double v = Double.parseDouble(ctx.FLOAT_LITERAL().getText());
            return SemanticSynth.expr(Type.FLOAT, new AstFloatLiteral(v));
        }
        if (ctx.CHAR_LITERAL() != null) {
            Integer ch = parseCharLiteral(ctx.CHAR_LITERAL().getText(), ctx);
            if (ch == null) {
                return SemanticSynth.errorExpr();
            }
            return SemanticSynth.expr(Type.INT, new AstCharLiteral(ch));
        }
        if (ctx.KW_TRUE() != null) {
            return SemanticSynth.expr(Type.BOOL, new AstBoolLiteral(true));
        }
        return SemanticSynth.expr(Type.BOOL, new AstBoolLiteral(false));
    }

    // --- reglas no expresión: devolver none por defecto ---

    @Override
    public SemanticSynth visitArrayDim(CPPSubsetParser.ArrayDimContext ctx) {
        return SemanticSynth.none();
    }

    @Override
    public SemanticSynth visitParamList(CPPSubsetParser.ParamListContext ctx) {
        return SemanticSynth.none();
    }

    @Override
    public SemanticSynth visitParam(CPPSubsetParser.ParamContext ctx) {
        return SemanticSynth.none();
    }

    @Override
    public SemanticSynth visitFuncDecl(CPPSubsetParser.FuncDeclContext ctx) {
        return SemanticSynth.none();
    }

    @Override
    public SemanticSynth visitLvalue(CPPSubsetParser.LvalueContext ctx) {
        return SemanticSynth.none();
    }

    @Override
    public SemanticSynth visitPostfixSuffix(CPPSubsetParser.PostfixSuffixContext ctx) {
        return SemanticSynth.none();
    }

    @Override
    public SemanticSynth visitArgList(CPPSubsetParser.ArgListContext ctx) {
        return SemanticSynth.none();
    }

    @Override
    public SemanticSynth visitTypeName(CPPSubsetParser.TypeNameContext ctx) {
        return SemanticSynth.none();
    }

    private SemanticSynth buildPostfix(
            CPPSubsetParser.AtomContext atom, List<CPPSubsetParser.PostfixSuffixContext> suffixes) {
        if (atom.IDENTIFIER() != null) {
            String name = atom.IDENTIFIER().getText();
            Symbol sym = resolveAndMark(name);
            if (sym == null) {
                diagnostics.addError("identificador no declarado: " + name, atom);
                // Aún visitamos los sufijos (índices/argumentos) para reportar sus errores internos.
                for (CPPSubsetParser.PostfixSuffixContext s : suffixes) {
                    visitSuffixForErrors(s);
                }
                return SemanticSynth.errorExpr();
            }
            if (sym.kind() == SymbolKind.FUNCTION) {
                if (suffixes.isEmpty()) {
                    return SemanticSynth.expr(sym.type(), new AstCallableRef(name, sym.type()));
                }
                return buildCallChain(name, sym, suffixes);
            }
            if (sym.array()) {
                if (suffixes.isEmpty()) {
                    diagnostics.addError("el arreglo '" + name + "' requiere subíndice", atom);
                    return SemanticSynth.errorExpr();
                }
                return buildArrayChain(name, sym, suffixes);
            }
        }
        SemanticSynth base = visit(atom);
        AstExpr cur = base.exprAst();
        Type t = base.exprType();
        for (CPPSubsetParser.PostfixSuffixContext suf : suffixes) {
            if (suf.LBRACK() != null) {
                SemanticSynth idx = visit(suf.expr());
                if (!idx.isExpr()) {
                    diagnostics.addError("índice de arreglo inválido", suf);
                    cur = AstError.placeholder();
                    t = Type.ERROR;
                    continue;
                }
                if (idx.exprType() != Type.INT && idx.exprType() != Type.ERROR) {
                    diagnostics.addError("índice de arreglo debe ser INT, se obtuvo " + idx.exprType(), suf);
                }
                if (cur instanceof AstArraySubscript) {
                    diagnostics.addError("demasiados subíndices (arreglo de una sola dimensión)", suf);
                    continue;
                }
                Type elem;
                if (cur instanceof AstVarRef vr) {
                    if (!vr.isArray()) {
                        diagnostics.addError("subíndice en expresión que no es arreglo", suf);
                        cur = AstError.placeholder();
                        t = Type.ERROR;
                        continue;
                    }
                    elem = vr.resultType();
                } else if (t == Type.ERROR) {
                    elem = Type.ERROR;
                } else {
                    diagnostics.addError("subíndice inválido: el operando no es un arreglo", suf);
                    cur = AstError.placeholder();
                    t = Type.ERROR;
                    continue;
                }
                cur = new AstArraySubscript(cur, idx.exprAst(), elem);
                t = elem;
            } else if (suf.LPAREN() != null) {
                if (cur instanceof AstCallableRef cr) {
                    Symbol fun = table.resolve(cr.name()).orElse(null);
                    List<AstExpr> args = new ArrayList<>();
                    List<Type> argTypes = new ArrayList<>();
                    if (suf.argList() != null) {
                        for (CPPSubsetParser.ExprContext ex : suf.argList().expr()) {
                            SemanticSynth a = visit(ex);
                            if (!a.isExpr()) {
                                diagnostics.addError(
                                        "argumento inválido en llamada a " + cr.name(), ex);
                                args.add(AstError.placeholder());
                                argTypes.add(Type.ERROR);
                            } else {
                                args.add(a.exprAst());
                                argTypes.add(a.exprType());
                            }
                        }
                    }
                    if (fun != null) {
                        checkCall(fun, argTypes, suf);
                        cur = new AstCallExpr(cr.name(), args, fun.type());
                        t = fun.type();
                    } else {
                        cur = AstError.placeholder();
                        t = Type.ERROR;
                    }
                } else if (t == Type.ERROR) {
                    // ya hubo un error previo: visitamos los args para acumular errores internos.
                    visitSuffixForErrors(suf);
                } else {
                    diagnostics.addError("llamada inválida: el operando no es invocable", suf);
                    visitSuffixForErrors(suf);
                    cur = AstError.placeholder();
                    t = Type.ERROR;
                }
            }
        }
        if (cur instanceof AstCallableRef) {
            diagnostics.addError("la función debe invocarse con ()", atom);
            return SemanticSynth.errorExpr();
        }
        if (cur == null) {
            return SemanticSynth.errorExpr();
        }
        return SemanticSynth.expr(t, cur);
    }

    private SemanticSynth buildCallChain(
            String name,
            Symbol fun,
            List<CPPSubsetParser.PostfixSuffixContext> suffixes) {
        CPPSubsetParser.PostfixSuffixContext first = suffixes.get(0);
        if (first.LBRACK() != null) {
            diagnostics.addError("una función no se indexa como arreglo", first);
            visitSuffixForErrors(first);
            return SemanticSynth.errorExpr();
        }
        List<AstExpr> args = new ArrayList<>();
        List<Type> argTypes = new ArrayList<>();
        if (first.argList() != null) {
            for (CPPSubsetParser.ExprContext ex : first.argList().expr()) {
                SemanticSynth a = visit(ex);
                if (!a.isExpr()) {
                    diagnostics.addError("argumento inválido en llamada a " + name, ex);
                    args.add(AstError.placeholder());
                    argTypes.add(Type.ERROR);
                } else {
                    args.add(a.exprAst());
                    argTypes.add(a.exprType());
                }
            }
        }
        checkCall(fun, argTypes, first);
        AstExpr call = new AstCallExpr(name, args, fun.type());
        SemanticSynth cur = SemanticSynth.expr(fun.type(), call);
        List<CPPSubsetParser.PostfixSuffixContext> rest = suffixes.subList(1, suffixes.size());
        return applySuffixesAfterValue(cur, rest);
    }

    private SemanticSynth buildArrayChain(
            String name,
            Symbol arr,
            List<CPPSubsetParser.PostfixSuffixContext> suffixes) {
        CPPSubsetParser.PostfixSuffixContext first = suffixes.get(0);
        if (first.LPAREN() != null) {
            diagnostics.addError("un arreglo no es invocable", first);
            visitSuffixForErrors(first);
            return SemanticSynth.errorExpr();
        }
        if (first.LBRACK() == null) {
            diagnostics.addError("se esperaba '[' para el arreglo '" + name + "'", first);
            return SemanticSynth.errorExpr();
        }
        SemanticSynth idx = visit(first.expr());
        if (!idx.isExpr()) {
            diagnostics.addError("índice de arreglo inválido", first);
            return SemanticSynth.errorExpr();
        }
        if (idx.exprType() != Type.INT && idx.exprType() != Type.ERROR) {
            diagnostics.addError("índice de arreglo debe ser INT, se obtuvo " + idx.exprType(), first);
        }
        AstExpr baseRef = new AstVarRef(name, arr.type(), true);
        AstExpr sub = new AstArraySubscript(baseRef, idx.exprAst(), arr.type());
        SemanticSynth cur = SemanticSynth.expr(arr.type(), sub);
        List<CPPSubsetParser.PostfixSuffixContext> rest = suffixes.subList(1, suffixes.size());
        return applySuffixesAfterValue(cur, rest);
    }

    private SemanticSynth applySuffixesAfterValue(
            SemanticSynth cur, List<CPPSubsetParser.PostfixSuffixContext> rest) {
        AstExpr e = cur.exprAst();
        Type t = cur.exprType();
        for (CPPSubsetParser.PostfixSuffixContext suf : rest) {
            if (suf.LBRACK() != null) {
                diagnostics.addError("demasiados subíndices (arreglo de una sola dimensión)", suf);
                visitSuffixForErrors(suf);
            }
            if (suf.LPAREN() != null) {
                diagnostics.addError("no se puede llamar al resultado de una expresión no función", suf);
                visitSuffixForErrors(suf);
            }
        }
        return SemanticSynth.expr(t, e);
    }

    /** Visita los hijos de un sufijo (expr de índice, argumentos) sólo para acumular sus errores. */
    private void visitSuffixForErrors(CPPSubsetParser.PostfixSuffixContext suf) {
        if (suf.expr() != null) {
            visit(suf.expr());
        }
        if (suf.argList() != null) {
            for (CPPSubsetParser.ExprContext ex : suf.argList().expr()) {
                visit(ex);
            }
        }
    }

    private void checkCall(Symbol fun, List<Type> argTypes, ParserRuleContext ctx) {
        List<Type> formals = fun.parameterTypes();
        if (argTypes.size() != formals.size()) {
            diagnostics.addError(
                    String.format(
                            Locale.ROOT,
                            "aridad incorrecta en llamada a '%s': se esperaban %d argumentos y hay %d",
                            fun.name(),
                            formals.size(),
                            argTypes.size()),
                    ctx);
            return;
        }
        for (int i = 0; i < argTypes.size(); i++) {
            Type got = argTypes.get(i);
            Type want = formals.get(i);
            if (!isAssignable(want, got)) {
                diagnostics.addError(
                        String.format(
                                Locale.ROOT,
                                "tipo incompatible en argumento %d de '%s': se esperaba %s y se obtuvo %s",
                                i + 1,
                                fun.name(),
                                want,
                                got),
                        ctx);
            }
        }
    }

    private LvalueSynth analyzeLvalue(CPPSubsetParser.LvalueContext ctx) {
        String name = ctx.IDENTIFIER().getText();
        Symbol sym = resolveAndMark(name);
        if (sym == null) {
            diagnostics.addError("identificador no declarado: " + name, ctx);
            if (ctx.expr() != null) {
                visit(ctx.expr());
            }
            return new LvalueSynth(Type.ERROR, ctx.LBRACK() != null);
        }
        if (sym.kind() == SymbolKind.FUNCTION) {
            diagnostics.addError("no se puede asignar a una función: " + name, ctx);
            if (ctx.expr() != null) {
                visit(ctx.expr());
            }
            return new LvalueSynth(Type.ERROR, ctx.LBRACK() != null);
        }
        if (ctx.LBRACK() == null) {
            if (sym.array()) {
                diagnostics.addError("falta subíndice en arreglo: " + name, ctx);
                return new LvalueSynth(Type.ERROR, false);
            }
            return new LvalueSynth(sym.type(), false);
        }
        if (!sym.array()) {
            diagnostics.addError("subíndice en variable no arreglo: " + name, ctx);
            visit(ctx.expr());
            return new LvalueSynth(Type.ERROR, true);
        }
        SemanticSynth idx = visit(ctx.expr());
        if (!idx.isExpr()) {
            diagnostics.addError("índice inválido en asignación", ctx);
        } else if (idx.exprType() != Type.INT && idx.exprType() != Type.ERROR) {
            diagnostics.addError("índice de arreglo debe ser INT", ctx);
        }
        return new LvalueSynth(sym.type(), true);
    }

    private record LvalueSynth(Type type, boolean arrayElement) {}

    private static Type mapTypeName(CPPSubsetParser.TypeNameContext ctx) {
        if (ctx.KW_INT() != null) {
            return Type.INT;
        }
        if (ctx.KW_DOUBLE() != null) {
            return Type.FLOAT;
        }
        if (ctx.KW_CHAR() != null) {
            return Type.INT;
        }
        if (ctx.KW_BOOL() != null) {
            return Type.BOOL;
        }
        if (ctx.KW_VOID() != null) {
            return Type.VOID;
        }
        return Type.ERROR;
    }

    private Integer parseCharLiteral(String text, ParserRuleContext ctx) {
        if (text.length() < 2 || text.charAt(0) != '\'' || text.charAt(text.length() - 1) != '\'') {
            diagnostics.addError("literal de carácter mal formado", ctx);
            return null;
        }
        String inner = text.substring(1, text.length() - 1);
        if (inner.isEmpty()) {
            diagnostics.addError("carácter vacío", ctx);
            return null;
        }
        if (inner.charAt(0) == '\\') {
            if (inner.length() < 2) {
                diagnostics.addError("escape incompleto", ctx);
                return null;
            }
            return switch (inner.charAt(1)) {
                case 'n' -> (int) '\n';
                case 't' -> (int) '\t';
                case 'r' -> (int) '\r';
                case '\\' -> (int) '\\';
                case '\'' -> (int) '\'';
                case '0' -> 0;
                default -> (int) inner.charAt(1);
            };
        }
        return (int) inner.charAt(0);
    }

    // ---- combinadores binarios ----

    private SemanticSynth combineArithmetic(SemanticSynth a, SemanticSynth b, String op, ParserRuleContext ctx) {
        if (!a.isExpr() || !b.isExpr()) {
            diagnostics.addError("operandos inválidos en operación " + op, ctx);
            return SemanticSynth.errorExpr();
        }
        Type ta = a.exprType();
        Type tb = b.exprType();
        if (ta == Type.ERROR || tb == Type.ERROR) {
            return SemanticSynth.errorExpr();
        }
        if (!ta.isNumeric() || !tb.isNumeric()) {
            diagnostics.addError("tipos incompatibles en " + op + ": " + ta + " y " + tb, ctx);
            return SemanticSynth.errorExpr();
        }
        Type out = (ta == Type.FLOAT || tb == Type.FLOAT) ? Type.FLOAT : Type.INT;
        AstExpr ast = new AstBinOp(a.exprAst(), op, b.exprAst(), out);
        return SemanticSynth.expr(out, ast);
    }

    private SemanticSynth combineMod(SemanticSynth a, SemanticSynth b, ParserRuleContext ctx) {
        if (!a.isExpr() || !b.isExpr()) {
            diagnostics.addError("operandos inválidos en %", ctx);
            return SemanticSynth.errorExpr();
        }
        Type ta = a.exprType();
        Type tb = b.exprType();
        if (ta == Type.ERROR || tb == Type.ERROR) {
            return SemanticSynth.errorExpr();
        }
        if (ta != Type.INT || tb != Type.INT) {
            diagnostics.addError("tipos incompatibles en %: se requieren INT e INT", ctx);
            return SemanticSynth.errorExpr();
        }
        AstExpr ast = new AstBinOp(a.exprAst(), "%", b.exprAst(), Type.INT);
        return SemanticSynth.expr(Type.INT, ast);
    }

    private SemanticSynth combineRelational(SemanticSynth a, SemanticSynth b, String op, ParserRuleContext ctx) {
        if (!a.isExpr() || !b.isExpr()) {
            diagnostics.addError("operandos inválidos en " + op, ctx);
            return SemanticSynth.errorExpr();
        }
        Type ta = a.exprType();
        Type tb = b.exprType();
        if (ta == Type.ERROR || tb == Type.ERROR) {
            return SemanticSynth.expr(Type.BOOL, new AstBinOp(a.exprAst(), op, b.exprAst(), Type.BOOL));
        }
        if (!ta.isNumeric() || !tb.isNumeric()) {
            diagnostics.addError("tipos incompatibles en " + op + ": " + ta + " y " + tb, ctx);
            return SemanticSynth.errorExpr();
        }
        AstExpr ast = new AstBinOp(a.exprAst(), op, b.exprAst(), Type.BOOL);
        return SemanticSynth.expr(Type.BOOL, ast);
    }

    private SemanticSynth combineEquality(SemanticSynth a, SemanticSynth b, String op, ParserRuleContext ctx) {
        if (!a.isExpr() || !b.isExpr()) {
            diagnostics.addError("operandos inválidos en " + op, ctx);
            return SemanticSynth.errorExpr();
        }
        Type ta = a.exprType();
        Type tb = b.exprType();
        if (ta == Type.ERROR || tb == Type.ERROR) {
            return SemanticSynth.expr(Type.BOOL, new AstBinOp(a.exprAst(), op, b.exprAst(), Type.BOOL));
        }
        boolean ok;
        if (ta == Type.BOOL || tb == Type.BOOL) {
            ok = ta == tb;
        } else if (ta.isNumeric() && tb.isNumeric()) {
            ok = true;
        } else {
            ok = ta == tb; // STRING == STRING reservado por extensión futura
        }
        if (!ok) {
            diagnostics.addError("tipos incompatibles en " + op + ": " + ta + " y " + tb, ctx);
            return SemanticSynth.errorExpr();
        }
        AstExpr ast = new AstBinOp(a.exprAst(), op, b.exprAst(), Type.BOOL);
        return SemanticSynth.expr(Type.BOOL, ast);
    }

    private SemanticSynth combineLogical(SemanticSynth a, SemanticSynth b, String op, ParserRuleContext ctx) {
        if (!a.isExpr() || !b.isExpr()) {
            diagnostics.addError("operandos inválidos en " + op, ctx);
            return SemanticSynth.errorExpr();
        }
        Type ta = a.exprType();
        Type tb = b.exprType();
        if (ta == Type.ERROR || tb == Type.ERROR) {
            return SemanticSynth.expr(Type.BOOL, new AstBinOp(a.exprAst(), op, b.exprAst(), Type.BOOL));
        }
        if (ta != Type.BOOL || tb != Type.BOOL) {
            diagnostics.addError("tipos incompatibles en " + op + ": se esperaba BOOL y BOOL", ctx);
            return SemanticSynth.errorExpr();
        }
        AstExpr ast = new AstBinOp(a.exprAst(), op, b.exprAst(), Type.BOOL);
        return SemanticSynth.expr(Type.BOOL, ast);
    }

    private boolean requireNumeric(SemanticSynth s, String op, ParserRuleContext ctx) {
        if (!s.isExpr()) {
            diagnostics.addError("operando inválido en operador " + op, ctx);
            return false;
        }
        if (s.exprType() == Type.ERROR) {
            return true;
        }
        if (!s.exprType().isNumeric()) {
            diagnostics.addError("tipo incompatible en operador " + op + ": se esperaba numérico", ctx);
            return false;
        }
        return true;
    }

    private boolean requireBool(SemanticSynth s, String op, ParserRuleContext ctx) {
        if (!s.isExpr()) {
            diagnostics.addError("operando inválido en operador " + op, ctx);
            return false;
        }
        if (s.exprType() == Type.ERROR) {
            return true;
        }
        if (s.exprType() != Type.BOOL) {
            diagnostics.addError("tipo incompatible en operador " + op + ": se esperaba BOOL", ctx);
            return false;
        }
        return true;
    }

    private static boolean isConditionType(Type t) {
        return t == Type.BOOL || t == Type.INT || t == Type.FLOAT || t == Type.ERROR;
    }

    private static boolean isAssignable(Type lhs, Type rhs) {
        if (lhs == Type.ERROR || rhs == Type.ERROR) {
            return true;
        }
        if (lhs == rhs) {
            return true;
        }
        if (lhs == Type.FLOAT && rhs == Type.INT) {
            return true;
        }
        return false;
    }

    private void checkReturnCompatible(Type declared, Type actual, ParserRuleContext ctx) {
        if (declared == Type.ERROR || actual == Type.ERROR) {
            return;
        }
        if (declared == Type.VOID) {
            diagnostics.addError("return con valor en función void", ctx);
            return;
        }
        if (!isAssignable(declared, actual)) {
            diagnostics.addError(
                    "tipo incompatible en return: se esperaba " + declared + " y se obtuvo " + actual, ctx);
        }
    }

    // ---- warnings: no usados, shadowing, return alcanzable ----

    private Symbol resolveAndMark(String name) {
        Symbol sym = table.resolve(name).orElse(null);
        if (sym != null) {
            sym.markUsed();
        }
        return sym;
    }

    private void checkShadowing(String name, ParserRuleContext ctx) {
        if (table.resolveOuter(name).isPresent()) {
            diagnostics.addWarning("la variable '" + name + "' oculta una declaración externa", ctx);
        }
    }

    private void emitUnusedWarnings(Map<String, Symbol> scope) {
        for (Symbol s : scope.values()) {
            if (s.used()) {
                continue;
            }
            switch (s.kind()) {
                case VARIABLE -> diagnostics.addWarning(
                        "variable declarada y nunca usada: " + s.name(), s.line(), s.column());
                case PARAMETER -> diagnostics.addWarning(
                        "parámetro declarado y nunca usado: " + s.name(), s.line(), s.column());
                case FUNCTION -> { /* las funciones del scope global no se reportan */ }
            }
        }
    }

    /**
     * Chequeo simple: el bloque garantiza retorno si alguno de sus statements
     * (no solo el último) lo garantiza.
     */
    private static boolean blockGuaranteesReturn(CPPSubsetParser.BlockContext block) {
        for (CPPSubsetParser.StatementContext st : block.statement()) {
            if (statementGuaranteesReturn(st)) {
                return true;
            }
        }
        return false;
    }

    private static boolean statementGuaranteesReturn(CPPSubsetParser.StatementContext st) {
        if (st.returnStmt() != null) {
            return true;
        }
        if (st.block() != null) {
            return blockGuaranteesReturn(st.block());
        }
        if (st.ifStmt() != null) {
            // Solo si tiene "else" Y ambos bloques garantizan retorno.
            CPPSubsetParser.IfStmtContext is = st.ifStmt();
            if (is.block().size() < 2) {
                return false;
            }
            return blockGuaranteesReturn(is.block(0)) && blockGuaranteesReturn(is.block(1));
        }
        return false;
    }
}
