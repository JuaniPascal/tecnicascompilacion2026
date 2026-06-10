package com.cppcompiler.symtab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.cppcompiler.parser.CPPSubsetParser;
import com.cppcompiler.parser.CPPSubsetParser.ArrayDimContext;
import com.cppcompiler.parser.CPPSubsetParser.FuncDeclContext;
import com.cppcompiler.parser.CPPSubsetParser.ParamContext;
import com.cppcompiler.parser.CPPSubsetParser.ProgramContext;
import com.cppcompiler.parser.CPPSubsetParser.TypeNameContext;
import com.cppcompiler.parser.CPPSubsetParser.VarDeclContext;
import com.cppcompiler.parser.CPPSubsetParserBaseVisitor;

/**
 * Recorre el AST y acumula símbolos (orden de aparición similar al ejemplo de la cátedra).
 */
public final class SymbolTableBuilder extends CPPSubsetParserBaseVisitor<Void> {

    private final List<SymbolRow> rows = new ArrayList<>();
    private String currentScope = "global";
    private String currentFunction = null;

    public List<SymbolRow> getRows() {
        return Collections.unmodifiableList(rows);
    }

    public static String formatTable(List<SymbolRow> rows) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== TABLA DE SÍMBOLOS ===\n");
        sb.append(String.format(Locale.ROOT, "%-15s %-10s %-15s %-10s %-10s %-15s %s%n",
                "NOMBRE", "TIPO", "CATEGORÍA", "LÍNEA", "COLUMNA", "ÁMBITO", "DETALLES"));
        sb.append("-".repeat(92)).append('\n');
        for (SymbolRow r : rows) {
            sb.append(String.format(Locale.ROOT, "%-15s %-10s %-15s %-10d %-10d %-15s %s%n",
                    r.name(), r.type(), r.category(), r.line(), r.column(), r.scope(), r.details()));
        }
        return sb.toString();
    }

    @Override
    public Void visitProgram(ProgramContext ctx) {
        currentScope = "global";
        currentFunction = null;
        for (int i = 0; i < ctx.getChildCount(); i++) {
            ParseTree c = ctx.getChild(i);
            if (c instanceof TerminalNode) {
                continue;
            }
            if (c instanceof VarDeclContext) {
                visitVarDecl((VarDeclContext) c);
            } else if (c instanceof FuncDeclContext) {
                visitFuncDecl((FuncDeclContext) c);
            }
        }
        return null;
    }

    @Override
    public Void visitVarDecl(VarDeclContext ctx) {
        String type = typeText(ctx.typeName());
        String name = ctx.IDENTIFIER().getText();
        int line = ctx.IDENTIFIER().getSymbol().getLine();
        int col = ctx.IDENTIFIER().getSymbol().getCharPositionInLine();
        String details = "[private]";
        if (ctx.arrayDim() != null) {
            String dim = ctx.arrayDim().INT_LITERAL().getText();
            details = "[arr:" + dim + "] [private]";
        }
        if ("global".equals(currentScope)) {
            rows.add(new SymbolRow(name, type, "variable", line, col, "global", details));
        } else {
            rows.add(new SymbolRow(name, type, "variable", line, col, currentFunction, details));
        }
        return null;
    }

    @Override
    public Void visitFuncDecl(FuncDeclContext ctx) {
        String ret = typeText(ctx.typeName());
        String name = ctx.IDENTIFIER().getText();
        int nameLine = ctx.IDENTIFIER().getSymbol().getLine();
        int nameCol = ctx.IDENTIFIER().getSymbol().getCharPositionInLine();
        StringBuilder sig = new StringBuilder("[private] [");
        List<ParamContext> params = ctx.paramList() != null ? ctx.paramList().param() : List.of();
        for (int i = 0; i < params.size(); i++) {
            if (i > 0) {
                sig.append(", ");
            }
            sig.append(typeText(params.get(i).typeName()));
        }
        sig.append("]");
        rows.add(new SymbolRow(name, ret, "funcion", nameLine, nameCol, "global", sig.toString()));

        String prevScope = currentScope;
        String prevFn = currentFunction;
        currentScope = name;
        currentFunction = name;

        for (ParamContext p : params) {
            String pt = typeText(p.typeName());
            String pname = p.IDENTIFIER().getText();
            int pline = p.IDENTIFIER().getSymbol().getLine();
            int pcol = p.IDENTIFIER().getSymbol().getCharPositionInLine();
            rows.add(new SymbolRow(pname, pt, "parametro", pline, pcol, name, ""));
        }

        visitBlockStatements(ctx.block());
        currentScope = prevScope;
        currentFunction = prevFn;
        return null;
    }

    private void visitBlockStatements(CPPSubsetParser.BlockContext block) {
        for (CPPSubsetParser.StatementContext st : block.statement()) {
            processStatement(st);
        }
    }

    private void processStatement(CPPSubsetParser.StatementContext ctx) {
        if (ctx.varDecl() != null) {
            visitVarDecl(ctx.varDecl());
            return;
        }
        if (ctx.assignment() != null) {
            return;
        }
        if (ctx.ifStmt() != null) {
            for (CPPSubsetParser.BlockContext b : ctx.ifStmt().block()) {
                visitBlockStatements(b);
            }
            return;
        }
        if (ctx.whileStmt() != null) {
            visitBlockStatements(ctx.whileStmt().block());
            return;
        }
        if (ctx.forStmt() != null) {
            // El "for" puede declarar una variable en el init: la registramos en el ámbito actual.
            CPPSubsetParser.ForStmtContext fs = ctx.forStmt();
            if (fs.forInit() instanceof CPPSubsetParser.ForInitDeclContext fid) {
                String type = typeText(fid.typeName());
                String name = fid.IDENTIFIER().getText();
                int line = fid.IDENTIFIER().getSymbol().getLine();
                int col = fid.IDENTIFIER().getSymbol().getCharPositionInLine();
                String details = "[for-init] [private]";
                String scope = "global".equals(currentScope) ? "global" : currentFunction;
                rows.add(new SymbolRow(name, type, "variable", line, col, scope, details));
            }
            visitBlockStatements(fs.block());
            return;
        }
        if (ctx.breakStmt() != null || ctx.continueStmt() != null) {
            return;
        }
        if (ctx.returnStmt() != null) {
            return;
        }
        if (ctx.block() != null) {
            visitBlockStatements(ctx.block());
        }
    }

    private static String typeText(TypeNameContext t) {
        if (t == null) {
            return "void";
        }
        return t.getText();
    }
}
