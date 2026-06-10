// Generated from C:/Users/Juani/Documents/GitHub/tecnicascompilacion2026/cpp compiler/CPPSubsetParser.g4 by ANTLR 4.13.2
package com.cppcompiler.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CPPSubsetParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CPPSubsetParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(CPPSubsetParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#varDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(CPPSubsetParser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#arrayDim}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayDim(CPPSubsetParser.ArrayDimContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#funcDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDecl(CPPSubsetParser.FuncDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#paramList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamList(CPPSubsetParser.ParamListContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(CPPSubsetParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(CPPSubsetParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(CPPSubsetParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(CPPSubsetParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#lvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLvalue(CPPSubsetParser.LvalueContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#ifStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmt(CPPSubsetParser.IfStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#whileStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStmt(CPPSubsetParser.WhileStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#forStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStmt(CPPSubsetParser.ForStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ForInitDecl}
	 * labeled alternative in {@link CPPSubsetParser#forInit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForInitDecl(CPPSubsetParser.ForInitDeclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ForInitAssign}
	 * labeled alternative in {@link CPPSubsetParser#forInit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForInitAssign(CPPSubsetParser.ForInitAssignContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#forUpdate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForUpdate(CPPSubsetParser.ForUpdateContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#breakStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStmt(CPPSubsetParser.BreakStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#continueStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStmt(CPPSubsetParser.ContinueStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#returnStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStmt(CPPSubsetParser.ReturnStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#typeName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeName(CPPSubsetParser.TypeNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(CPPSubsetParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#orExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpr(CPPSubsetParser.OrExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#andExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr(CPPSubsetParser.AndExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#eqExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqExpr(CPPSubsetParser.EqExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#relExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelExpr(CPPSubsetParser.RelExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#addExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExpr(CPPSubsetParser.AddExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#mulExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulExpr(CPPSubsetParser.MulExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#unary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary(CPPSubsetParser.UnaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#postfix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfix(CPPSubsetParser.PostfixContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#postfixSuffix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfixSuffix(CPPSubsetParser.PostfixSuffixContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(CPPSubsetParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#argList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgList(CPPSubsetParser.ArgListContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPPSubsetParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(CPPSubsetParser.LiteralContext ctx);
}