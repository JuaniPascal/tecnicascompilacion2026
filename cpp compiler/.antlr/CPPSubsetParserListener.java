// Generated from c:\Users\Juani\Documents\GitHub\tecnicascompilacion2026\cpp compiler\CPPSubsetParser.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CPPSubsetParser}.
 */
public interface CPPSubsetParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(CPPSubsetParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(CPPSubsetParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(CPPSubsetParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(CPPSubsetParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#arrayDim}.
	 * @param ctx the parse tree
	 */
	void enterArrayDim(CPPSubsetParser.ArrayDimContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#arrayDim}.
	 * @param ctx the parse tree
	 */
	void exitArrayDim(CPPSubsetParser.ArrayDimContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#funcDecl}.
	 * @param ctx the parse tree
	 */
	void enterFuncDecl(CPPSubsetParser.FuncDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#funcDecl}.
	 * @param ctx the parse tree
	 */
	void exitFuncDecl(CPPSubsetParser.FuncDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#paramList}.
	 * @param ctx the parse tree
	 */
	void enterParamList(CPPSubsetParser.ParamListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#paramList}.
	 * @param ctx the parse tree
	 */
	void exitParamList(CPPSubsetParser.ParamListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(CPPSubsetParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(CPPSubsetParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(CPPSubsetParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(CPPSubsetParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(CPPSubsetParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(CPPSubsetParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(CPPSubsetParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(CPPSubsetParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void enterLvalue(CPPSubsetParser.LvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void exitLvalue(CPPSubsetParser.LvalueContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(CPPSubsetParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(CPPSubsetParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#whileStmt}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(CPPSubsetParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#whileStmt}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(CPPSubsetParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#forStmt}.
	 * @param ctx the parse tree
	 */
	void enterForStmt(CPPSubsetParser.ForStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#forStmt}.
	 * @param ctx the parse tree
	 */
	void exitForStmt(CPPSubsetParser.ForStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ForInitDecl}
	 * labeled alternative in {@link CPPSubsetParser#forInit}.
	 * @param ctx the parse tree
	 */
	void enterForInitDecl(CPPSubsetParser.ForInitDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ForInitDecl}
	 * labeled alternative in {@link CPPSubsetParser#forInit}.
	 * @param ctx the parse tree
	 */
	void exitForInitDecl(CPPSubsetParser.ForInitDeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ForInitAssign}
	 * labeled alternative in {@link CPPSubsetParser#forInit}.
	 * @param ctx the parse tree
	 */
	void enterForInitAssign(CPPSubsetParser.ForInitAssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ForInitAssign}
	 * labeled alternative in {@link CPPSubsetParser#forInit}.
	 * @param ctx the parse tree
	 */
	void exitForInitAssign(CPPSubsetParser.ForInitAssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#forUpdate}.
	 * @param ctx the parse tree
	 */
	void enterForUpdate(CPPSubsetParser.ForUpdateContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#forUpdate}.
	 * @param ctx the parse tree
	 */
	void exitForUpdate(CPPSubsetParser.ForUpdateContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#breakStmt}.
	 * @param ctx the parse tree
	 */
	void enterBreakStmt(CPPSubsetParser.BreakStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#breakStmt}.
	 * @param ctx the parse tree
	 */
	void exitBreakStmt(CPPSubsetParser.BreakStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#continueStmt}.
	 * @param ctx the parse tree
	 */
	void enterContinueStmt(CPPSubsetParser.ContinueStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#continueStmt}.
	 * @param ctx the parse tree
	 */
	void exitContinueStmt(CPPSubsetParser.ContinueStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#returnStmt}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(CPPSubsetParser.ReturnStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#returnStmt}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(CPPSubsetParser.ReturnStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#typeName}.
	 * @param ctx the parse tree
	 */
	void enterTypeName(CPPSubsetParser.TypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#typeName}.
	 * @param ctx the parse tree
	 */
	void exitTypeName(CPPSubsetParser.TypeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(CPPSubsetParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(CPPSubsetParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(CPPSubsetParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(CPPSubsetParser.OrExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(CPPSubsetParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(CPPSubsetParser.AndExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#eqExpr}.
	 * @param ctx the parse tree
	 */
	void enterEqExpr(CPPSubsetParser.EqExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#eqExpr}.
	 * @param ctx the parse tree
	 */
	void exitEqExpr(CPPSubsetParser.EqExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#relExpr}.
	 * @param ctx the parse tree
	 */
	void enterRelExpr(CPPSubsetParser.RelExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#relExpr}.
	 * @param ctx the parse tree
	 */
	void exitRelExpr(CPPSubsetParser.RelExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#addExpr}.
	 * @param ctx the parse tree
	 */
	void enterAddExpr(CPPSubsetParser.AddExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#addExpr}.
	 * @param ctx the parse tree
	 */
	void exitAddExpr(CPPSubsetParser.AddExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#mulExpr}.
	 * @param ctx the parse tree
	 */
	void enterMulExpr(CPPSubsetParser.MulExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#mulExpr}.
	 * @param ctx the parse tree
	 */
	void exitMulExpr(CPPSubsetParser.MulExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#unary}.
	 * @param ctx the parse tree
	 */
	void enterUnary(CPPSubsetParser.UnaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#unary}.
	 * @param ctx the parse tree
	 */
	void exitUnary(CPPSubsetParser.UnaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#postfix}.
	 * @param ctx the parse tree
	 */
	void enterPostfix(CPPSubsetParser.PostfixContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#postfix}.
	 * @param ctx the parse tree
	 */
	void exitPostfix(CPPSubsetParser.PostfixContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#postfixSuffix}.
	 * @param ctx the parse tree
	 */
	void enterPostfixSuffix(CPPSubsetParser.PostfixSuffixContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#postfixSuffix}.
	 * @param ctx the parse tree
	 */
	void exitPostfixSuffix(CPPSubsetParser.PostfixSuffixContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(CPPSubsetParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(CPPSubsetParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#argList}.
	 * @param ctx the parse tree
	 */
	void enterArgList(CPPSubsetParser.ArgListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#argList}.
	 * @param ctx the parse tree
	 */
	void exitArgList(CPPSubsetParser.ArgListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPPSubsetParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(CPPSubsetParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPPSubsetParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(CPPSubsetParser.LiteralContext ctx);
}