package com.cppcompiler.semantic;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.cppcompiler.ast.AstArraySubscript;
import com.cppcompiler.ast.AstBinOp;
import com.cppcompiler.ast.AstBoolLiteral;
import com.cppcompiler.ast.AstCallableRef;
import com.cppcompiler.ast.AstCallExpr;
import com.cppcompiler.ast.AstCharLiteral;
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
 */
public final class SemanticAnalyzer extends CPPSubsetParserBaseVisitor<SemanticSynth> {

    private final ScopedSymbolTable table = new ScopedSymbolTable();
    private Type currentFunctionReturn = Type.VOID;

    public ScopedSymbolTable symbolTable() {
        return table;
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
        table.exitScope();
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
        table.define(Symbol.function(name, ret, formals));
    }

    private void visitFuncDeclBody(CPPSubsetParser.FuncDeclContext ctx) {
        currentFunctionReturn = mapTypeName(ctx.typeName());
        table.enterScope();
        if (ctx.paramList() != null) {
            for (CPPSubsetParser.ParamContext p : ctx.paramList().param()) {
                defineVariableFromParam(p);
            }
        }
        visitBlock(ctx.block());
        table.exitScope();
        currentFunctionReturn = Type.VOID;
    }

    private void defineVariableFromParam(CPPSubsetParser.ParamContext ctx) {
        Type t = mapTypeName(ctx.typeName());
        if (t == Type.VOID) {
            throw new RuntimeException("parámetro void no permitido: " + ctx.IDENTIFIER().getText());
        }
        String name = ctx.IDENTIFIER().getText();
        table.define(Symbol.parameter(name, t, false, 0));
    }

    @Override
    public SemanticSynth visitVarDecl(CPPSubsetParser.VarDeclContext ctx) {
        Type t = mapTypeName(ctx.typeName());
        if (t == Type.VOID) {
            throw new RuntimeException("variable con tipo void: " + ctx.IDENTIFIER().getText());
        }
        String name = ctx.IDENTIFIER().getText();
        boolean array = ctx.arrayDim() != null;
        int dim = 0;
        if (array) {
            dim = Integer.parseInt(ctx.arrayDim().INT_LITERAL().getText());
        }
        table.define(Symbol.variable(name, t, array, dim));
        return SemanticSynth.none();
    }

    @Override
    public SemanticSynth visitBlock(CPPSubsetParser.BlockContext ctx) {
        table.enterScope();
        for (CPPSubsetParser.StatementContext st : ctx.statement()) {
            visitStatement(st);
        }
        table.exitScope();
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
            throw new RuntimeException("lado derecho sin valor de expresión");
        }
        checkAssignable(lhs.type(), rhs.exprType());
        return SemanticSynth.none();
    }

    @Override
    public SemanticSynth visitIfStmt(CPPSubsetParser.IfStmtContext ctx) {
        SemanticSynth cond = visit(ctx.expr());
        if (!cond.isExpr()) {
            throw new RuntimeException("condición de if inválida");
        }
        if (!isConditionType(cond.exprType())) {
            throw new RuntimeException(
                    "tipo incompatible en condición de if: " + cond.exprType() + " (se esperaba BOOL o numérico)");
        }
        visitBlock(ctx.block());
        return SemanticSynth.none();
    }

    @Override
    public SemanticSynth visitReturnStmt(CPPSubsetParser.ReturnStmtContext ctx) {
        if (ctx.expr() == null) {
            if (currentFunctionReturn != Type.VOID) {
                throw new RuntimeException("return sin expresión en función no void");
            }
            return SemanticSynth.none();
        }
        SemanticSynth v = visit(ctx.expr());
        if (!v.isExpr()) {
            throw new RuntimeException("return con expresión inválida");
        }
        checkReturnCompatible(currentFunctionReturn, v.exprType());
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
            acc = combineLogical(acc, r, "||");
        }
        return acc;
    }

    @Override
    public SemanticSynth visitAndExpr(CPPSubsetParser.AndExprContext ctx) {
        SemanticSynth acc = visit(ctx.eqExpr(0));
        for (int i = 1; i < ctx.eqExpr().size(); i++) {
            SemanticSynth r = visit(ctx.eqExpr(i));
            acc = combineLogical(acc, r, "&&");
        }
        return acc;
    }

    @Override
    public SemanticSynth visitEqExpr(CPPSubsetParser.EqExprContext ctx) {
        SemanticSynth acc = visit(ctx.relExpr(0));
        for (int i = 1; i < ctx.relExpr().size(); i++) {
            String op = ((TerminalNode) ctx.getChild(2 * i - 1)).getText();
            SemanticSynth r = visit(ctx.relExpr(i));
            acc = combineEquality(acc, r, op);
        }
        return acc;
    }

    @Override
    public SemanticSynth visitRelExpr(CPPSubsetParser.RelExprContext ctx) {
        SemanticSynth acc = visit(ctx.addExpr(0));
        for (int i = 1; i < ctx.addExpr().size(); i++) {
            String op = ((TerminalNode) ctx.getChild(2 * i - 1)).getText();
            SemanticSynth r = visit(ctx.addExpr(i));
            acc = combineRelational(acc, r, op);
        }
        return acc;
    }

    @Override
    public SemanticSynth visitAddExpr(CPPSubsetParser.AddExprContext ctx) {
        SemanticSynth acc = visit(ctx.mulExpr(0));
        for (int i = 1; i < ctx.mulExpr().size(); i++) {
            String op = ((TerminalNode) ctx.getChild(2 * i - 1)).getText();
            SemanticSynth r = visit(ctx.mulExpr(i));
            acc = combineArithmetic(acc, r, op);
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
                acc = combineMod(acc, r);
            } else {
                acc = combineArithmetic(acc, r, op);
            }
        }
        return acc;
    }

    @Override
    public SemanticSynth visitUnary(CPPSubsetParser.UnaryContext ctx) {
        SemanticSynth inner = visit(ctx.postfix());
        if (ctx.NOT() != null) {
            requireBool(inner, "!");
            AstExpr ast = new AstUnaryOp("!", inner.exprAst(), Type.BOOL);
            return SemanticSynth.expr(Type.BOOL, ast);
        }
        if (ctx.MINUS() != null) {
            requireNumeric(inner, "-");
            Type t = inner.exprType();
            AstExpr ast = new AstUnaryOp("-", inner.exprAst(), t);
            return SemanticSynth.expr(t, ast);
        }
        if (ctx.PLUS() != null) {
            requireNumeric(inner, "+");
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
            Symbol sym = table.resolve(name).orElseThrow(() -> new RuntimeException("identificador no declarado: " + name));
            if (sym.kind() == SymbolKind.FUNCTION) {
                return SemanticSynth.expr(sym.type(), new AstCallableRef(name, sym.type()));
            }
            if (sym.array()) {
                throw new RuntimeException("el arreglo '" + name + "' requiere subíndice en una expresión");
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
            int ch = parseCharLiteral(ctx.CHAR_LITERAL().getText(), ctx.CHAR_LITERAL().getSymbol().getLine());
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
            Symbol sym = table.resolve(name).orElseThrow(() -> new RuntimeException("identificador no declarado: " + name));
            if (sym.kind() == SymbolKind.FUNCTION) {
                if (suffixes.isEmpty()) {
                    return SemanticSynth.expr(sym.type(), new AstCallableRef(name, sym.type()));
                }
                return buildCallChain(name, sym, suffixes);
            }
            if (sym.array()) {
                if (suffixes.isEmpty()) {
                    throw new RuntimeException("el arreglo '" + name + "' requiere subíndice");
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
                    throw new RuntimeException("índice de arreglo inválido");
                }
                if (idx.exprType() != Type.INT) {
                    throw new RuntimeException("índice de arreglo debe ser INT, se obtuvo " + idx.exprType());
                }
                if (cur instanceof AstArraySubscript) {
                    throw new RuntimeException("demasiados subíndices (arreglo de una sola dimensión)");
                }
                Type elem;
                if (cur instanceof AstVarRef vr) {
                    if (!vr.isArray()) {
                        throw new RuntimeException("subíndice en expresión que no es arreglo");
                    }
                    elem = vr.resultType();
                } else {
                    throw new RuntimeException("subíndice inválido: el operando no es un arreglo");
                }
                cur = new AstArraySubscript(cur, idx.exprAst(), elem);
                t = elem;
            } else if (suf.LPAREN() != null) {
                if (cur instanceof AstCallableRef cr) {
                    Symbol fun = table.resolve(cr.name()).orElseThrow();
                    List<AstExpr> args = new ArrayList<>();
                    if (suf.argList() != null) {
                        for (CPPSubsetParser.ExprContext ex : suf.argList().expr()) {
                            SemanticSynth a = visit(ex);
                            if (!a.isExpr()) {
                                throw new RuntimeException("argumento inválido en llamada a " + cr.name());
                            }
                            args.add(a.exprAst());
                        }
                    }
                    checkCall(fun, args);
                    cur = new AstCallExpr(cr.name(), args, fun.type());
                    t = fun.type();
                } else {
                    throw new RuntimeException("llamada inválida: el operando no es invocable");
                }
            }
        }
        if (cur instanceof AstCallableRef) {
            throw new RuntimeException("la función debe invocarse con ()");
        }
        return SemanticSynth.expr(t, cur);
    }

    private SemanticSynth buildCallChain(
            String name, Symbol fun, List<CPPSubsetParser.PostfixSuffixContext> suffixes) {
        if (suffixes.isEmpty()) {
            throw new RuntimeException("la función '" + name + "' debe invocarse con ()");
        }
        CPPSubsetParser.PostfixSuffixContext first = suffixes.get(0);
        if (first.LBRACK() != null) {
            throw new RuntimeException("una función no se indexa como arreglo");
        }
        List<AstExpr> args = new ArrayList<>();
        if (first.argList() != null) {
            for (CPPSubsetParser.ExprContext ex : first.argList().expr()) {
                SemanticSynth a = visit(ex);
                if (!a.isExpr()) {
                    throw new RuntimeException("argumento inválido en llamada a " + name);
                }
                args.add(a.exprAst());
            }
        }
        checkCall(fun, args);
        AstExpr call = new AstCallExpr(name, args, fun.type());
        SemanticSynth cur = SemanticSynth.expr(fun.type(), call);
        List<CPPSubsetParser.PostfixSuffixContext> rest = suffixes.subList(1, suffixes.size());
        return applySuffixesAfterValue(cur, rest);
    }

    private SemanticSynth buildArrayChain(
            String name, Symbol arr, List<CPPSubsetParser.PostfixSuffixContext> suffixes) {
        if (suffixes.isEmpty()) {
            throw new RuntimeException("el arreglo '" + name + "' requiere subíndice");
        }
        CPPSubsetParser.PostfixSuffixContext first = suffixes.get(0);
        if (first.LPAREN() != null) {
            throw new RuntimeException("un arreglo no es invocable");
        }
        if (first.LBRACK() == null) {
            throw new RuntimeException("se esperaba '[' para el arreglo '" + name + "'");
        }
        SemanticSynth idx = visit(first.expr());
        if (!idx.isExpr()) {
            throw new RuntimeException("índice de arreglo inválido");
        }
        if (idx.exprType() != Type.INT) {
            throw new RuntimeException("índice de arreglo debe ser INT, se obtuvo " + idx.exprType());
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
                throw new RuntimeException("demasiados subíndices (arreglo de una sola dimensión)");
            }
            if (suf.LPAREN() != null) {
                throw new RuntimeException("no se puede llamar al resultado de una expresión no función");
            }
        }
        return SemanticSynth.expr(t, e);
    }

    private void checkCall(Symbol fun, List<AstExpr> args) {
        List<Type> formals = fun.parameterTypes();
        if (args.size() != formals.size()) {
            throw new RuntimeException(
                    String.format(
                            Locale.ROOT,
                            "aridad incorrecta en llamada a '%s': se esperaban %d argumentos y hay %d",
                            fun.name(),
                            formals.size(),
                            args.size()));
        }
        for (int i = 0; i < args.size(); i++) {
            Type got = args.get(i).resultType();
            Type want = formals.get(i);
            if (!isAssignable(want, got)) {
                throw new RuntimeException(
                        String.format(
                                Locale.ROOT,
                                "tipo incompatible en argumento %d de '%s': se esperaba %s y se obtuvo %s",
                                i + 1,
                                fun.name(),
                                want,
                                got));
            }
        }
    }

    private LvalueSynth analyzeLvalue(CPPSubsetParser.LvalueContext ctx) {
        String name = ctx.IDENTIFIER().getText();
        Symbol sym = table.resolve(name).orElseThrow(() -> new RuntimeException("identificador no declarado: " + name));
        if (sym.kind() == SymbolKind.FUNCTION) {
            throw new RuntimeException("no se puede asignar a una función: " + name);
        }
        if (ctx.LBRACK() == null) {
            if (sym.array()) {
                throw new RuntimeException("falta subíndice en arreglo: " + name);
            }
            return new LvalueSynth(sym.type(), false);
        }
        if (!sym.array()) {
            throw new RuntimeException("subíndice en variable no arreglo: " + name);
        }
        SemanticSynth idx = visit(ctx.expr());
        if (!idx.isExpr()) {
            throw new RuntimeException("índice inválido en asignación");
        }
        if (idx.exprType() != Type.INT) {
            throw new RuntimeException("índice de arreglo debe ser INT");
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
        throw new RuntimeException("tipo no soportado: " + ctx.getText());
    }

    private static int parseCharLiteral(String text, int line) {
        if (text.length() < 2 || text.charAt(0) != '\'' || text.charAt(text.length() - 1) != '\'') {
            throw new RuntimeException("literal de carácter mal formado en línea " + line);
        }
        String inner = text.substring(1, text.length() - 1);
        if (inner.isEmpty()) {
            throw new RuntimeException("carácter vacío en línea " + line);
        }
        if (inner.charAt(0) == '\\') {
            if (inner.length() < 2) {
                throw new RuntimeException("escape incompleto en línea " + line);
            }
            return switch (inner.charAt(1)) {
                case 'n' -> '\n';
                case 't' -> '\t';
                case 'r' -> '\r';
                case '\\' -> '\\';
                case '\'' -> '\'';
                case '0' -> '\0';
                default -> inner.charAt(1);
            };
        }
        return inner.charAt(0);
    }

    private SemanticSynth combineArithmetic(SemanticSynth a, SemanticSynth b, String op) {
        if (!a.isExpr() || !b.isExpr()) {
            throw new RuntimeException("operandos inválidos en operación " + op);
        }
        Type ta = a.exprType();
        Type tb = b.exprType();
        if (!ta.isNumeric() || !tb.isNumeric()) {
            throw new RuntimeException("tipos incompatibles en " + op + ": " + ta + " y " + tb);
        }
        Type out = (ta == Type.FLOAT || tb == Type.FLOAT) ? Type.FLOAT : Type.INT;
        AstExpr ast = new AstBinOp(a.exprAst(), op, b.exprAst(), out);
        return SemanticSynth.expr(out, ast);
    }

    private SemanticSynth combineMod(SemanticSynth a, SemanticSynth b) {
        if (!a.isExpr() || !b.isExpr()) {
            throw new RuntimeException("operandos inválidos en %");
        }
        if (a.exprType() != Type.INT || b.exprType() != Type.INT) {
            throw new RuntimeException("tipos incompatibles en %: se requieren INT e INT");
        }
        AstExpr ast = new AstBinOp(a.exprAst(), "%", b.exprAst(), Type.INT);
        return SemanticSynth.expr(Type.INT, ast);
    }

    private SemanticSynth combineRelational(SemanticSynth a, SemanticSynth b, String op) {
        if (!a.isExpr() || !b.isExpr()) {
            throw new RuntimeException("operandos inválidos en " + op);
        }
        Type ta = a.exprType();
        Type tb = b.exprType();
        if (!ta.isNumeric() || !tb.isNumeric()) {
            throw new RuntimeException("tipos incompatibles en " + op + ": " + ta + " y " + tb);
        }
        AstExpr ast = new AstBinOp(a.exprAst(), op, b.exprAst(), Type.BOOL);
        return SemanticSynth.expr(Type.BOOL, ast);
    }

    private SemanticSynth combineEquality(SemanticSynth a, SemanticSynth b, String op) {
        if (!a.isExpr() || !b.isExpr()) {
            throw new RuntimeException("operandos inválidos en " + op);
        }
        Type ta = a.exprType();
        Type tb = b.exprType();
        if (ta == Type.BOOL || tb == Type.BOOL) {
            if (ta != tb) {
                throw new RuntimeException("tipos incompatibles en " + op + ": " + ta + " y " + tb);
            }
        } else if (ta.isNumeric() && tb.isNumeric()) {
            // ok
        } else if (ta == Type.STRING && tb == Type.STRING) {
            // reservado por si se amplía el lexer/gramática
        } else {
            throw new RuntimeException("tipos incompatibles en " + op + ": " + ta + " y " + tb);
        }
        AstExpr ast = new AstBinOp(a.exprAst(), op, b.exprAst(), Type.BOOL);
        return SemanticSynth.expr(Type.BOOL, ast);
    }

    private SemanticSynth combineLogical(SemanticSynth a, SemanticSynth b, String op) {
        if (!a.isExpr() || !b.isExpr()) {
            throw new RuntimeException("operandos inválidos en " + op);
        }
        if (a.exprType() != Type.BOOL || b.exprType() != Type.BOOL) {
            throw new RuntimeException("tipos incompatibles en " + op + ": se esperaba BOOL y BOOL");
        }
        AstExpr ast = new AstBinOp(a.exprAst(), op, b.exprAst(), Type.BOOL);
        return SemanticSynth.expr(Type.BOOL, ast);
    }

    private static void requireNumeric(SemanticSynth s, String op) {
        if (!s.isExpr() || !s.exprType().isNumeric()) {
            throw new RuntimeException("tipo incompatible en operador " + op + ": se esperaba numérico");
        }
    }

    private static void requireBool(SemanticSynth s, String op) {
        if (!s.isExpr() || s.exprType() != Type.BOOL) {
            throw new RuntimeException("tipo incompatible en operador " + op + ": se esperaba BOOL");
        }
    }

    private static boolean isConditionType(Type t) {
        return t == Type.BOOL || t == Type.INT || t == Type.FLOAT;
    }

    private static void checkAssignable(Type lhs, Type rhs) {
        if (!isAssignable(lhs, rhs)) {
            throw new RuntimeException("tipos incompatibles en asignación: " + rhs + " no asignable a " + lhs);
        }
    }

    private static boolean isAssignable(Type lhs, Type rhs) {
        if (lhs == rhs) {
            return true;
        }
        if (lhs == Type.FLOAT && rhs == Type.INT) {
            return true;
        }
        return false;
    }

    private static void checkReturnCompatible(Type declared, Type actual) {
        if (declared == Type.VOID) {
            throw new RuntimeException("return con valor en función void");
        }
        if (!isAssignable(declared, actual)) {
            throw new RuntimeException("tipo incompatible en return: se esperaba " + declared + " y se obtuvo " + actual);
        }
    }
}
