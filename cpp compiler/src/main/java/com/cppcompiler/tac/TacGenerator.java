package com.cppcompiler.tac;

import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.cppcompiler.parser.CPPSubsetParser;
import com.cppcompiler.parser.CPPSubsetParser.AddExprContext;
import com.cppcompiler.parser.CPPSubsetParser.AndExprContext;
import com.cppcompiler.parser.CPPSubsetParser.ArgListContext;
import com.cppcompiler.parser.CPPSubsetParser.AssignmentContext;
import com.cppcompiler.parser.CPPSubsetParser.AtomContext;
import com.cppcompiler.parser.CPPSubsetParser.BlockContext;
import com.cppcompiler.parser.CPPSubsetParser.EqExprContext;
import com.cppcompiler.parser.CPPSubsetParser.ExprContext;
import com.cppcompiler.parser.CPPSubsetParser.FuncDeclContext;
import com.cppcompiler.parser.CPPSubsetParser.IfStmtContext;
import com.cppcompiler.parser.CPPSubsetParser.LvalueContext;
import com.cppcompiler.parser.CPPSubsetParser.MulExprContext;
import com.cppcompiler.parser.CPPSubsetParser.OrExprContext;
import com.cppcompiler.parser.CPPSubsetParser.ParamContext;
import com.cppcompiler.parser.CPPSubsetParser.PostfixContext;
import com.cppcompiler.parser.CPPSubsetParser.PostfixSuffixContext;
import com.cppcompiler.parser.CPPSubsetParser.ProgramContext;
import com.cppcompiler.parser.CPPSubsetParser.RelExprContext;
import com.cppcompiler.parser.CPPSubsetParser.ReturnStmtContext;
import com.cppcompiler.parser.CPPSubsetParser.StatementContext;
import com.cppcompiler.parser.CPPSubsetParser.TypeNameContext;
import com.cppcompiler.parser.CPPSubsetParser.UnaryContext;
import com.cppcompiler.parser.CPPSubsetParser.VarDeclContext;

/** Genera TAC lineal a partir del árbol parseado. */
public final class TacGenerator {

    private final TacProgram out = new TacProgram();
    private int tempCounter;
    private int ifCounter;

    public TacProgram generate(ProgramContext ctx) {
        out.clear();
        tempCounter = 0;
        ifCounter = 0;
        out.emitComment("Código de tres direcciones generado");
        out.emit("PROGRAMA_INICIO:");
        out.emitComment("Declaración de variables globales");
        emitProgramLevel(ctx);
        return out;
    }

    private void emitProgramLevel(ProgramContext ctx) {
        for (int i = 0; i < ctx.getChildCount(); i++) {
            ParseTree c = ctx.getChild(i);
            if (c instanceof TerminalNode) {
                continue;
            }
            if (c instanceof VarDeclContext) {
                emitGlobalVarDecl((VarDeclContext) c);
            } else if (c instanceof FuncDeclContext) {
                emitFunc((FuncDeclContext) c);
            }
        }
        out.emit("PROGRAMA_FIN:");
    }

    private void emitGlobalVarDecl(VarDeclContext ctx) {
        String type = typeText(ctx.typeName());
        String name = ctx.IDENTIFIER().getText();
        if (ctx.arrayDim() != null) {
            String n = ctx.arrayDim().INT_LITERAL().getText();
            out.emit("DECLARE " + name + "[" + n + "] " + type);
        } else {
            out.emit("DECLARE " + name + " " + type);
        }
    }

    private void emitFunc(FuncDeclContext ctx) {
        String fname = ctx.IDENTIFIER().getText();
        out.emit("func_" + fname + ":");
        if (ctx.paramList() != null) {
            for (ParamContext p : ctx.paramList().param()) {
                String pt = typeText(p.typeName());
                String pn = p.IDENTIFIER().getText();
                out.emit("PARAM " + pn + " " + pt);
            }
        }
        emitBlock(ctx.block());
    }

    private void emitBlock(BlockContext block) {
        for (StatementContext st : block.statement()) {
            emitStatement(st);
        }
    }

    private void emitStatement(StatementContext ctx) {
        if (ctx.varDecl() != null) {
            emitLocalVarDecl(ctx.varDecl());
        } else if (ctx.assignment() != null) {
            emitAssignment(ctx.assignment());
        } else if (ctx.ifStmt() != null) {
            emitIf(ctx.ifStmt());
        } else if (ctx.returnStmt() != null) {
            emitReturn(ctx.returnStmt());
        } else if (ctx.block() != null) {
            emitBlock(ctx.block());
        }
    }

    private void emitLocalVarDecl(VarDeclContext ctx) {
        String type = typeText(ctx.typeName());
        String name = ctx.IDENTIFIER().getText();
        if (ctx.arrayDim() != null) {
            String n = ctx.arrayDim().INT_LITERAL().getText();
            out.emit("DECLARE " + name + "[" + n + "] " + type);
        } else {
            out.emit("DECLARE " + name + " " + type);
        }
    }

    private void emitAssignment(AssignmentContext ctx) {
        String rhs = genExpr(ctx.expr());
        String lhs = genLvalue(ctx.lvalue());
        out.emit(lhs + " = " + rhs);
    }

    private String genLvalue(LvalueContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        if (ctx.LBRACK() != null) {
            String idx = genExpr(ctx.expr());
            return id + "[" + idx + "]";
        }
        return id;
    }

    private void emitIf(IfStmtContext ctx) {
        int id = ++ifCounter;
        String cond = genExpr(ctx.expr());
        String thenL = "THEN_" + id;
        String endL = "END_IF_" + id;
        out.emit("if " + cond + " goto " + thenL);
        out.emit("goto " + endL);
        out.emit(thenL + ":");
        emitBlock(ctx.block());
        out.emit(endL + ":");
    }

    private void emitReturn(ReturnStmtContext ctx) {
        if (ctx.expr() != null) {
            String v = genExpr(ctx.expr());
            out.emit("return " + v);
        } else {
            out.emit("return");
        }
    }

    private String genExpr(ExprContext ctx) {
        return genOrExpr(ctx.orExpr());
    }

    private String genOrExpr(OrExprContext ctx) {
        String v = genAndExpr(ctx.andExpr(0));
        for (int i = 1; i < ctx.andExpr().size(); i++) {
            String r = genAndExpr(ctx.andExpr(i));
            v = binop(v, "||", r);
        }
        return v;
    }

    private String genAndExpr(AndExprContext ctx) {
        String v = genEqExpr(ctx.eqExpr(0));
        for (int i = 1; i < ctx.eqExpr().size(); i++) {
            String r = genEqExpr(ctx.eqExpr(i));
            v = binop(v, "&&", r);
        }
        return v;
    }

    private String genEqExpr(EqExprContext ctx) {
        String v = genRelExpr(ctx.relExpr(0));
        for (int i = 1; i < ctx.relExpr().size(); i++) {
            String op = ctx.getChild(2 * i - 1).getText();
            String r = genRelExpr(ctx.relExpr(i));
            v = binop(v, op, r);
        }
        return v;
    }

    private String genRelExpr(RelExprContext ctx) {
        String v = genAddExpr(ctx.addExpr(0));
        for (int i = 1; i < ctx.addExpr().size(); i++) {
            String op = ctx.getChild(2 * i - 1).getText();
            String r = genAddExpr(ctx.addExpr(i));
            v = binop(v, op, r);
        }
        return v;
    }

    private String genAddExpr(AddExprContext ctx) {
        String v = genMulExpr(ctx.mulExpr(0));
        for (int i = 1; i < ctx.mulExpr().size(); i++) {
            String op = ctx.getChild(2 * i - 1).getText();
            String r = genMulExpr(ctx.mulExpr(i));
            v = binop(v, op, r);
        }
        return v;
    }

    private String genMulExpr(MulExprContext ctx) {
        String v = genUnary(ctx.unary(0));
        for (int i = 1; i < ctx.unary().size(); i++) {
            String op = ctx.getChild(2 * i - 1).getText();
            String r = genUnary(ctx.unary(i));
            v = binop(v, op, r);
        }
        return v;
    }

    private String genUnary(UnaryContext ctx) {
        String inner = genPostfix(ctx.postfix());
        if (ctx.PLUS() != null) {
            return inner;
        }
        if (ctx.MINUS() != null) {
            return unary("-", inner);
        }
        if (ctx.NOT() != null) {
            return unary("!", inner);
        }
        return inner;
    }

    private String unary(String op, String operand) {
        if (isSimpleAtom(operand)) {
            return op + operand;
        }
        String t = newTemp();
        out.emit(t + " = " + op + operand);
        return t;
    }

    private static boolean isSimpleAtom(String s) {
        return s.matches("-?\\d+")
                || s.matches("-?\\d+\\.\\d+([eE][+-]?\\d+)?")
                || s.matches("'.*'")
                || "true".equals(s)
                || "false".equals(s)
                || s.matches("[a-zA-Z_][a-zA-Z0-9_]*");
    }

    private String genPostfix(PostfixContext ctx) {
        String v = genAtom(ctx.atom());
        for (PostfixSuffixContext suf : ctx.postfixSuffix()) {
            if (suf.LBRACK() != null) {
                String idx = genExpr(suf.expr());
                if (idx.matches("-?\\d+")) {
                    v = v + "[" + idx + "]";
                } else {
                    String t = newTemp();
                    out.emit(t + " = " + v + "[" + idx + "]");
                    v = t;
                }
            } else if (suf.LPAREN() != null) {
                StringBuilder args = new StringBuilder();
                ArgListContext al = suf.argList();
                if (al != null) {
                    List<ExprContext> es = al.expr();
                    for (int i = 0; i < es.size(); i++) {
                        if (i > 0) {
                            args.append(", ");
                        }
                        args.append(genExpr(es.get(i)));
                    }
                }
                String func = stripCalls(v);
                String argStr = args.toString();
                if (argStr.isEmpty()) {
                    out.emit("CALL func_" + func);
                } else {
                    out.emit("CALL func_" + func + ", " + argStr);
                }
                v = "RETURN_VALUE";
            }
        }
        return v;
    }

    /** Si v es una cadena tipo nombre[...] o tN, extrae el identificador base de llamada. */
    private static String stripCalls(String v) {
        int idx = v.indexOf('[');
        if (idx > 0) {
            return v.substring(0, idx);
        }
        return v;
    }

    private String genAtom(AtomContext ctx) {
        if (ctx.literal() != null) {
            return literalText(ctx.literal());
        }
        if (ctx.IDENTIFIER() != null) {
            return ctx.IDENTIFIER().getText();
        }
        return genExpr(ctx.expr());
    }

    private static String literalText(CPPSubsetParser.LiteralContext lit) {
        if (lit.INT_LITERAL() != null) {
            return lit.INT_LITERAL().getText();
        }
        if (lit.FLOAT_LITERAL() != null) {
            return lit.FLOAT_LITERAL().getText();
        }
        if (lit.CHAR_LITERAL() != null) {
            return lit.CHAR_LITERAL().getText();
        }
        if (lit.KW_TRUE() != null) {
            return "true";
        }
        return "false";
    }

    private String binop(String left, String op, String right) {
        if (isFoldableLiteral(left, op, right)) {
            String folded = foldConst(left, op, right);
            if (folded != null) {
                return folded;
            }
        }
        String t = newTemp();
        out.emit(t + " = " + left + " " + op + " " + right);
        return t;
    }

    private static boolean isFoldableLiteral(String a, String op, String b) {
        try {
            if (a.contains(".") || b.contains(".")) {
                double da = Double.parseDouble(a);
                double db = Double.parseDouble(b);
                return !Double.isNaN(da) && !Double.isNaN(db);
            }
            Long.parseLong(a);
            long lb = Long.parseLong(b);
            if ("%".equals(op) && lb == 0) {
                return false;
            }
            if ("/".equals(op) && lb == 0) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static String foldConst(String left, String op, String right) {
        if (left.contains(".") || right.contains(".")) {
            double a = Double.parseDouble(left);
            double b = Double.parseDouble(right);
            if (("/".equals(op) || "%".equals(op)) && b == 0.0) {
                return null;
            }
            double r =
                    switch (op) {
                        case "+" -> a + b;
                        case "-" -> a - b;
                        case "*" -> a * b;
                        case "/" -> a / b;
                        case "%" -> a % b;
                        case ">" -> a > b ? 1 : 0;
                        case "<" -> a < b ? 1 : 0;
                        case ">=" -> a >= b ? 1 : 0;
                        case "<=" -> a <= b ? 1 : 0;
                        case "==" -> a == b ? 1 : 0;
                        case "!=" -> a != b ? 1 : 0;
                        default -> Double.NaN;
                    };
            if (Double.isNaN(r)) {
                return null;
            }
            if (op.matches("[<>=!]+")) {
                return r != 0 ? "1" : "0";
            }
            if (r == (long) r) {
                return String.valueOf((long) r);
            }
            return String.valueOf(r);
        }
        long a = Long.parseLong(left);
        long b = Long.parseLong(right);
        if ("/".equals(op) && b == 0) {
            return null;
        }
        if ("%".equals(op) && b == 0) {
            return null;
        }
        Long r =
                switch (op) {
                    case "+" -> a + b;
                    case "-" -> a - b;
                    case "*" -> a * b;
                    case "/" -> a / b;
                    case "%" -> a % b;
                    case ">" -> (long) (a > b ? 1 : 0);
                    case "<" -> (long) (a < b ? 1 : 0);
                    case ">=" -> (long) (a >= b ? 1 : 0);
                    case "<=" -> (long) (a <= b ? 1 : 0);
                    case "==" -> (long) (a == b ? 1 : 0);
                    case "!=" -> (long) (a != b ? 1 : 0);
                    default -> null;
                };
        if (r == null) {
            return null;
        }
        if (op.matches("[<>=!]+")) {
            return r != 0 ? "1" : "0";
        }
        return String.valueOf(r);
    }

    private String newTemp() {
        return "t" + (++tempCounter);
    }

    private static String typeText(TypeNameContext t) {
        return t.getText();
    }
}
