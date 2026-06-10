// Generated from C:/Users/Escuela Cornu pc-1/Documents/GitHub/tecnicascompilacion2026/cpp compiler/CPPSubsetParser.g4 by ANTLR 4.13.2
package com.cppcompiler.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class CPPSubsetParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LINE_COMMENT=1, BLOCK_COMMENT=2, PREPROCESSOR=3, WS=4, FLOAT_LITERAL=5, 
		INT_LITERAL=6, STRING_LITERAL=7, CHAR_LITERAL=8, KW_VOID=9, KW_BOOL=10, 
		KW_CHAR=11, KW_SHORT=12, KW_INT=13, KW_LONG=14, KW_FLOAT=15, KW_DOUBLE=16, 
		KW_CONST=17, KW_STATIC=18, KW_UNSIGNED=19, KW_SIGNED=20, KW_VIRTUAL=21, 
		KW_IF=22, KW_ELSE=23, KW_WHILE=24, KW_FOR=25, KW_BREAK=26, KW_CONTINUE=27, 
		KW_RETURN=28, KW_CLASS=29, KW_STRUCT=30, KW_PUBLIC=31, KW_PRIVATE=32, 
		KW_PROTECTED=33, KW_THIS=34, KW_NEW=35, KW_DELETE=36, KW_SIZEOF=37, KW_NAMESPACE=38, 
		KW_USING=39, KW_TRUE=40, KW_FALSE=41, KW_NULLPTR=42, ELLIPSIS=43, SCOPE=44, 
		ARROW=45, INC=46, DEC=47, SHL=48, SHR=49, LE=50, GE=51, EQ=52, NE=53, 
		AND_AND=54, OR_OR=55, MUL_ASSIGN=56, DIV_ASSIGN=57, MOD_ASSIGN=58, ADD_ASSIGN=59, 
		SUB_ASSIGN=60, SHL_ASSIGN=61, SHR_ASSIGN=62, AND_ASSIGN=63, XOR_ASSIGN=64, 
		OR_ASSIGN=65, LPAREN=66, RPAREN=67, LBRACE=68, RBRACE=69, LBRACK=70, RBRACK=71, 
		SEMI=72, COMMA=73, DOT=74, QUEST=75, COLON=76, STAR=77, PLUS=78, MINUS=79, 
		DIV=80, MOD=81, TILDE=82, NOT=83, LT=84, GT=85, AMP=86, BIT_OR=87, BIT_XOR=88, 
		ASSIGN=89, IDENTIFIER=90, ERROR_CHAR=91;
	public static final int
		RULE_program = 0, RULE_varDecl = 1, RULE_arrayDim = 2, RULE_funcDecl = 3, 
		RULE_paramList = 4, RULE_param = 5, RULE_block = 6, RULE_statement = 7, 
		RULE_assignment = 8, RULE_lvalue = 9, RULE_ifStmt = 10, RULE_whileStmt = 11, 
		RULE_returnStmt = 12, RULE_typeName = 13, RULE_expr = 14, RULE_orExpr = 15, 
		RULE_andExpr = 16, RULE_eqExpr = 17, RULE_relExpr = 18, RULE_addExpr = 19, 
		RULE_mulExpr = 20, RULE_unary = 21, RULE_postfix = 22, RULE_postfixSuffix = 23, 
		RULE_atom = 24, RULE_argList = 25, RULE_literal = 26;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "varDecl", "arrayDim", "funcDecl", "paramList", "param", "block", 
			"statement", "assignment", "lvalue", "ifStmt", "whileStmt", "returnStmt", 
			"typeName", "expr", "orExpr", "andExpr", "eqExpr", "relExpr", "addExpr", 
			"mulExpr", "unary", "postfix", "postfixSuffix", "atom", "argList", "literal"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "'void'", "'bool'", 
			"'char'", "'short'", "'int'", "'long'", "'float'", "'double'", "'const'", 
			"'static'", "'unsigned'", "'signed'", "'virtual'", "'if'", "'else'", 
			"'while'", "'for'", "'break'", "'continue'", "'return'", "'class'", "'struct'", 
			"'public'", "'private'", "'protected'", "'this'", "'new'", "'delete'", 
			"'sizeof'", "'namespace'", "'using'", "'true'", "'false'", "'nullptr'", 
			"'...'", "'::'", "'->'", "'++'", "'--'", "'<<'", "'>>'", "'<='", "'>='", 
			"'=='", "'!='", "'&&'", "'||'", "'*='", "'/='", "'%='", "'+='", "'-='", 
			"'<<='", "'>>='", "'&='", "'^='", "'|='", "'('", "')'", "'{'", "'}'", 
			"'['", "']'", "';'", "','", "'.'", "'?'", "':'", "'*'", "'+'", "'-'", 
			"'/'", "'%'", "'~'", "'!'", "'<'", "'>'", "'&'", "'|'", "'^'", "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "LINE_COMMENT", "BLOCK_COMMENT", "PREPROCESSOR", "WS", "FLOAT_LITERAL", 
			"INT_LITERAL", "STRING_LITERAL", "CHAR_LITERAL", "KW_VOID", "KW_BOOL", 
			"KW_CHAR", "KW_SHORT", "KW_INT", "KW_LONG", "KW_FLOAT", "KW_DOUBLE", 
			"KW_CONST", "KW_STATIC", "KW_UNSIGNED", "KW_SIGNED", "KW_VIRTUAL", "KW_IF", 
			"KW_ELSE", "KW_WHILE", "KW_FOR", "KW_BREAK", "KW_CONTINUE", "KW_RETURN", 
			"KW_CLASS", "KW_STRUCT", "KW_PUBLIC", "KW_PRIVATE", "KW_PROTECTED", "KW_THIS", 
			"KW_NEW", "KW_DELETE", "KW_SIZEOF", "KW_NAMESPACE", "KW_USING", "KW_TRUE", 
			"KW_FALSE", "KW_NULLPTR", "ELLIPSIS", "SCOPE", "ARROW", "INC", "DEC", 
			"SHL", "SHR", "LE", "GE", "EQ", "NE", "AND_AND", "OR_OR", "MUL_ASSIGN", 
			"DIV_ASSIGN", "MOD_ASSIGN", "ADD_ASSIGN", "SUB_ASSIGN", "SHL_ASSIGN", 
			"SHR_ASSIGN", "AND_ASSIGN", "XOR_ASSIGN", "OR_ASSIGN", "LPAREN", "RPAREN", 
			"LBRACE", "RBRACE", "LBRACK", "RBRACK", "SEMI", "COMMA", "DOT", "QUEST", 
			"COLON", "STAR", "PLUS", "MINUS", "DIV", "MOD", "TILDE", "NOT", "LT", 
			"GT", "AMP", "BIT_OR", "BIT_XOR", "ASSIGN", "IDENTIFIER", "ERROR_CHAR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "CPPSubsetParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CPPSubsetParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(CPPSubsetParser.EOF, 0); }
		public List<VarDeclContext> varDecl() {
			return getRuleContexts(VarDeclContext.class);
		}
		public VarDeclContext varDecl(int i) {
			return getRuleContext(VarDeclContext.class,i);
		}
		public List<FuncDeclContext> funcDecl() {
			return getRuleContexts(FuncDeclContext.class);
		}
		public FuncDeclContext funcDecl(int i) {
			return getRuleContext(FuncDeclContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPPSubsetParserVisitor ) return ((CPPSubsetParserVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 77312L) != 0)) {
				{
				setState(56);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(54);
					varDecl();
					}
					break;
				case 2:
					{
					setState(55);
					funcDecl();
					}
					break;
				}
				}
				setState(60);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(61);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarDeclContext extends ParserRuleContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(CPPSubsetParser.IDENTIFIER, 0); }
		public TerminalNode SEMI() { return getToken(CPPSubsetParser.SEMI, 0); }
		public ArrayDimContext arrayDim() {
			return getRuleContext(ArrayDimContext.class,0);
		}
		public VarDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPPSubsetParserVisitor ) return ((CPPSubsetParserVisitor<? extends T>)visitor).visitVarDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclContext varDecl() throws RecognitionException {
		VarDeclContext _localctx = new VarDeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_varDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			typeName();
			setState(64);
			match(IDENTIFIER);
			setState(66);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACK) {
				{
				setState(65);
				arrayDim();
				}
			}

			setState(68);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayDimContext extends ParserRuleContext {
		public TerminalNode LBRACK() { return getToken(CPPSubsetParser.LBRACK, 0); }
		public TerminalNode INT_LITERAL() { return getToken(CPPSubsetParser.INT_LITERAL, 0); }
		public TerminalNode RBRACK() { return getToken(CPPSubsetParser.RBRACK, 0); }
		public ArrayDimContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayDim; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPPSubsetParserVisitor ) return ((CPPSubsetParserVisitor<? extends T>)visitor).visitArrayDim(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayDimContext arrayDim() throws RecognitionException {
		ArrayDimContext _localctx = new ArrayDimContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_arrayDim);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(LBRACK);
			setState(71);
			match(INT_LITERAL);
			setState(72);
			match(RBRACK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FuncDeclContext extends ParserRuleContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(CPPSubsetParser.IDENTIFIER, 0); }
		public TerminalNode LPAREN() { return getToken(CPPSubsetParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(CPPSubsetParser.RPAREN, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class,0);
		}
		public FuncDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPPSubsetParserVisitor ) return ((CPPSubsetParserVisitor<? extends T>)visitor).visitFuncDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncDeclContext funcDecl() throws RecognitionException {
		FuncDeclContext _localctx = new FuncDeclContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_funcDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			typeName();
			setState(75);
			match(IDENTIFIER);
			setState(76);
			match(LPAREN);
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 77312L) != 0)) {
				{
				setState(77);
				paramList();
				}
			}

			setState(80);
			match(RPAREN);
			setState(81);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParamListContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CPPSubsetParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CPPSubsetParser.COMMA, i);
		}
		public ParamListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPPSubsetParserVisitor ) return ((CPPSubsetParserVisitor<? extends T>)visitor).visitParamList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamListContext paramList() throws RecognitionException {
		ParamListContext _localctx = new ParamListContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_paramList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			param();
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(84);
				match(COMMA);
				setState(85);
				param();
				}
				}
				setState(90);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParamContext extends ParserRuleContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(CPPSubsetParser.IDENTIFIER, 0); }
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPPSubsetParserVisitor ) return ((CPPSubsetParserVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			typeName();
			setState(92);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(CPPSubsetParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(CPPSubsetParser.RBRACE, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPPSubsetParserVisitor ) return ((CPPSubsetParserVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			match(LBRACE);
			setState(98);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 289484288L) != 0) || _la==LBRACE || _la==IDENTIFIER) {
				{
				{
				setState(95);
				statement();
				}
				}
				setState(100);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(101);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public VarDeclContext varDecl() {
			return getRuleContext(VarDeclContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public IfStmtContext ifStmt() {
			return getRuleContext(IfStmtContext.class,0);
		}
		public WhileStmtContext whileStmt() {
			return getRuleContext(WhileStmtContext.class,0);
		}
		public ReturnStmtContext returnStmt() {
			return getRuleContext(ReturnStmtContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPPSubsetParserVisitor ) return ((CPPSubsetParserVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_statement);
		try {
			setState(109);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case KW_VOID:
			case KW_BOOL:
			case KW_CHAR:
			case KW_INT:
			case KW_DOUBLE:
				enterOuterAlt(_localctx, 1);
				{
				setState(103);
				varDecl();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(104);
				assignment();
				}
				break;
			case KW_IF:
				enterOuterAlt(_localctx, 3);
				{
				setState(105);
				ifStmt();
				}
				break;
			case KW_WHILE:
				enterOuterAlt(_localctx, 4);
				{
				setState(106);
				whileStmt();
				}
				break;
			case KW_RETURN:
				enterOuterAlt(_localctx, 5);
				{
				setState(107);
				returnStmt();
				}
				break;
			case LBRACE:
				enterOuterAlt(_localctx, 6);
				{
				setState(108);
				block();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignmentContext extends ParserRuleContext {
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(CPPSubsetParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(CPPSubsetParser.SEMI, 0); }
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPPSubsetParserVisitor ) return ((CPPSubsetParserVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			lvalue();
			setState(112);
			match(ASSIGN);
			setState(113);
			expr();
			setState(114);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LvalueContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(CPPSubsetParser.IDENTIFIER, 0); }
		public TerminalNode LBRACK() { return getToken(CPPSubsetParser.LBRACK, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RBRACK() { return getToken(CPPSubsetParser.RBRACK, 0); }
		public LvalueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lvalue; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPPSubsetParserVisitor ) return ((CPPSubsetParserVisitor<? extends T>)visitor).visitLvalue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LvalueContext lvalue() throws RecognitionException {
		LvalueContext _localctx = new LvalueContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_lvalue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			match(IDENTIFIER);
			setState(121);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACK) {
				{
				setState(117);
				match(LBRACK);
				setState(118);
				expr();
				setState(119);
				match(RBRACK);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfStmtContext extends ParserRuleContext {
		public TerminalNode KW_IF() { return getToken(CPPSubsetParser.KW_IF, 0); }
		public TerminalNode LPAREN() { return getToken(CPPSubsetParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(CPPSubsetParser.RPAREN, 0); }
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public TerminalNode KW_ELSE() { return getToken(CPPSubsetParser.KW_ELSE, 0); }
		public IfStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPPSubsetParserVisitor ) return ((CPPSubsetParserVisitor<? extends T>)visitor).visitIfStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStmtContext ifStmt() throws RecognitionException {
		IfStmtContext _localctx = new IfStmtContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_ifStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			match(KW_IF);
			setState(124);
			match(LPAREN);
			setState(125);
			expr();
			setState(126);
			match(RPAREN);
			setState(127);
			block();
			setState(130);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==KW_ELSE) {
				{
				setState(128);
				match(KW_ELSE);
				setState(129);
				block();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WhileStmtContext extends ParserRuleContext {
		public TerminalNode KW_WHILE() { return getToken(CPPSubsetParser.KW_WHILE, 0); }
		public TerminalNode LPAREN() { return getToken(CPPSubsetParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(CPPSubsetParser.RPAREN, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public WhileStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPPSubsetParserVisitor ) return ((CPPSubsetParserVisitor<? extends T>)visitor).visitWhileStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileStmtContext whileStmt() throws RecognitionException {
		WhileStmtContext _localctx = new WhileStmtContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_whileStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			match(KW_WHILE);
			setState(133);
			match(LPAREN);
			setState(134);
			expr();
			setState(135);
			match(RPAREN);
			setState(136);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReturnStmtContext extends ParserRuleContext {
		public TerminalNode KW_RETURN() { return getToken(CPPSubsetParser.KW_RETURN, 0); }
		public TerminalNode SEMI() { return getToken(CPPSubsetParser.SEMI, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ReturnStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPPSubsetParserVisitor ) return ((CPPSubsetParserVisitor<? extends T>)visitor).visitReturnStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStmtContext returnStmt() throws RecognitionException {
		ReturnStmtContext _localctx = new ReturnStmtContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_returnStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			match(KW_RETURN);
			setState(140);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 3298534883680L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 16920577L) != 0)) {
				{
				setState(139);
				expr();
				}
			}

			setState(142);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeNameContext extends ParserRuleContext {
		public TerminalNode KW_INT() { return getToken(CPPSubsetParser.KW_INT, 0); }
		public TerminalNode KW_DOUBLE() { return getToken(CPPSubsetParser.KW_DOUBLE, 0); }
		public TerminalNode KW_CHAR() { return getToken(CPPSubsetParser.KW_CHAR, 0); }
		public TerminalNode KW_BOOL() { return getToken(CPPSubsetParser.KW_BOOL, 0); }
		public TerminalNode KW_VOID() { return getToken(CPPSubsetParser.KW_VOID, 0); }
		public TypeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPPSubsetParserVisitor ) return ((CPPSubsetParserVisitor<? extends T>)visitor).visitTypeName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeNameContext typeName() throws RecognitionException {
		TypeNameContext _localctx = new TypeNameContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_typeName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 77312L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public OrExprContext orExpr() {
			return getRuleContext(OrExprContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPPSubsetParserVisitor ) return ((CPPSubsetParserVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			orExpr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OrExprContext extends ParserRuleContext {
		public List<AndExprContext> andExpr() {
			return getRuleContexts(AndExprContext.class);
		}
		public AndExprContext andExpr(int i) {
			return getRuleContext(AndExprContext.class,i);
		}
		public List<TerminalNode> OR_OR() { return getTokens(CPPSubsetParser.OR_OR); }
		public TerminalNode OR_OR(int i) {
			return getToken(CPPSubsetParser.OR_OR, i);
		}
		public OrExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orExpr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPPSubsetParserVisitor ) return ((CPPSubsetParserVisitor<? extends T>)visitor).visitOrExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrExprContext orExpr() throws RecognitionException {
		OrExprContext _localctx = new OrExprContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_orExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			andExpr();
			setState(153);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR_OR) {
				{
				{
				setState(149);
				match(OR_OR);
				setState(150);
				andExpr();
				}
				}
				setState(155);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AndExprContext extends ParserRuleContext {
		public List<EqExprContext> eqExpr() {
			return getRuleContexts(EqExprContext.class);
		}
		public EqExprContext eqExpr(int i) {
			return getRuleContext(EqExprContext.class,i);
		}
		public List<TerminalNode> AND_AND() { return getTokens(CPPSubsetParser.AND_AND); }
		public TerminalNode AND_AND(int i) {
			return getToken(CPPSubsetParser.AND_AND, i);
		}
		public AndExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andExpr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPPSubsetParserVisitor ) return ((CPPSubsetParserVisitor<? extends T>)visitor).visitAndExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AndExprContext andExpr() throws RecognitionException {
		AndExprContext _localctx = new AndExprContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_andExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			eqExpr();
			setState(161);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND_AND) {
				{
				{
				setState(157);
				match(AND_AND);
				setState(158);
				eqExpr();
				}
				}
				setState(163);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EqExprContext extends ParserRuleContext {
		public List<RelExprContext> relExpr() {
			return getRuleContexts(RelExprContext.class);
		}
		public RelExprContext relExpr(int i) {
			return getRuleContext(RelExprContext.class,i);
		}
		public List<TerminalNode> EQ() { return getTokens(CPPSubsetParser.EQ); }
		public TerminalNode EQ(int i) {
			return getToken(CPPSubsetParser.EQ, i);
		}
		public List<TerminalNode> NE() { return getTokens(CPPSubsetParser.NE); }
		public TerminalNode NE(int i) {
			return getToken(CPPSubsetParser.NE, i);
		}
		public EqExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eqExpr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPPSubsetParserVisitor ) return ((CPPSubsetParserVisitor<? extends T>)visitor).visitEqExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EqExprContext eqExpr() throws RecognitionException {
		EqExprContext _localctx = new EqExprContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_eqExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			relExpr();
			setState(169);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EQ || _la==NE) {
				{
				{
				setState(165);
				_la = _input.LA(1);
				if ( !(_la==EQ || _la==NE) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(166);
				relExpr();
				}
				}
				setState(171);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RelExprContext extends ParserRuleContext {
		public List<AddExprContext> addExpr() {
			return getRuleContexts(AddExprContext.class);
		}
		public AddExprContext addExpr(int i) {
			return getRuleContext(AddExprContext.class,i);
		}
		public List<TerminalNode> LT() { return getTokens(CPPSubsetParser.LT); }
		public TerminalNode LT(int i) {
			return getToken(CPPSubsetParser.LT, i);
		}
		public List<TerminalNode> GT() { return getTokens(CPPSubsetParser.GT); }
		public TerminalNode GT(int i) {
			return getToken(CPPSubsetParser.GT, i);
		}
		public List<TerminalNode> LE() { return getTokens(CPPSubsetParser.LE); }
		public TerminalNode LE(int i) {
			return getToken(CPPSubsetParser.LE, i);
		}
		public List<TerminalNode> GE() { return getTokens(CPPSubsetParser.GE); }
		public TerminalNode GE(int i) {
			return getToken(CPPSubsetParser.GE, i);
		}
		public RelExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relExpr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPPSubsetParserVisitor ) return ((CPPSubsetParserVisitor<? extends T>)visitor).visitRelExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelExprContext relExpr() throws RecognitionException {
		RelExprContext _localctx = new RelExprContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_relExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			addExpr();
			setState(177);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 50)) & ~0x3f) == 0 && ((1L << (_la - 50)) & 51539607555L) != 0)) {
				{
				{
				setState(173);
				_la = _input.LA(1);
				if ( !(((((_la - 50)) & ~0x3f) == 0 && ((1L << (_la - 50)) & 51539607555L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(174);
				addExpr();
				}
				}
				setState(179);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AddExprContext extends ParserRuleContext {
		public List<MulExprContext> mulExpr() {
			return getRuleContexts(MulExprContext.class);
		}
		public MulExprContext mulExpr(int i) {
			return getRuleContext(MulExprContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(CPPSubsetParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(CPPSubsetParser.PLUS, i);
		}
		public List<TerminalNode> MINUS() { return getTokens(CPPSubsetParser.MINUS); }
		public TerminalNode MINUS(int i) {
			return getToken(CPPSubsetParser.MINUS, i);
		}
		public AddExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addExpr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPPSubsetParserVisitor ) return ((CPPSubsetParserVisitor<? extends T>)visitor).visitAddExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AddExprContext addExpr() throws RecognitionException {
		AddExprContext _localctx = new AddExprContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_addExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			mulExpr();
			setState(185);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PLUS || _la==MINUS) {
				{
				{
				setState(181);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(182);
				mulExpr();
				}
				}
				setState(187);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MulExprContext extends ParserRuleContext {
		public List<UnaryContext> unary() {
			return getRuleContexts(UnaryContext.class);
		}
		public UnaryContext unary(int i) {
			return getRuleContext(UnaryContext.class,i);
		}
		public List<TerminalNode> STAR() { return getTokens(CPPSubsetParser.STAR); }
		public TerminalNode STAR(int i) {
			return getToken(CPPSubsetParser.STAR, i);
		}
		public List<TerminalNode> DIV() { return getTokens(CPPSubsetParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(CPPSubsetParser.DIV, i);
		}
		public List<TerminalNode> MOD() { return getTokens(CPPSubsetParser.MOD); }
		public TerminalNode MOD(int i) {
			return getToken(CPPSubsetParser.MOD, i);
		}
		public MulExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mulExpr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPPSubsetParserVisitor ) return ((CPPSubsetParserVisitor<? extends T>)visitor).visitMulExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MulExprContext mulExpr() throws RecognitionException {
		MulExprContext _localctx = new MulExprContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_mulExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			unary();
			setState(193);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 77)) & ~0x3f) == 0 && ((1L << (_la - 77)) & 25L) != 0)) {
				{
				{
				setState(189);
				_la = _input.LA(1);
				if ( !(((((_la - 77)) & ~0x3f) == 0 && ((1L << (_la - 77)) & 25L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(190);
				unary();
				}
				}
				setState(195);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnaryContext extends ParserRuleContext {
		public PostfixContext postfix() {
			return getRuleContext(PostfixContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(CPPSubsetParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(CPPSubsetParser.MINUS, 0); }
		public TerminalNode NOT() { return getToken(CPPSubsetParser.NOT, 0); }
		public UnaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPPSubsetParserVisitor ) return ((CPPSubsetParserVisitor<? extends T>)visitor).visitUnary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryContext unary() throws RecognitionException {
		UnaryContext _localctx = new UnaryContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_unary);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 78)) & ~0x3f) == 0 && ((1L << (_la - 78)) & 35L) != 0)) {
				{
				setState(196);
				_la = _input.LA(1);
				if ( !(((((_la - 78)) & ~0x3f) == 0 && ((1L << (_la - 78)) & 35L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(199);
			postfix();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PostfixContext extends ParserRuleContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public List<PostfixSuffixContext> postfixSuffix() {
			return getRuleContexts(PostfixSuffixContext.class);
		}
		public PostfixSuffixContext postfixSuffix(int i) {
			return getRuleContext(PostfixSuffixContext.class,i);
		}
		public PostfixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postfix; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPPSubsetParserVisitor ) return ((CPPSubsetParserVisitor<? extends T>)visitor).visitPostfix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PostfixContext postfix() throws RecognitionException {
		PostfixContext _localctx = new PostfixContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_postfix);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			atom();
			setState(205);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LPAREN || _la==LBRACK) {
				{
				{
				setState(202);
				postfixSuffix();
				}
				}
				setState(207);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PostfixSuffixContext extends ParserRuleContext {
		public TerminalNode LBRACK() { return getToken(CPPSubsetParser.LBRACK, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RBRACK() { return getToken(CPPSubsetParser.RBRACK, 0); }
		public TerminalNode LPAREN() { return getToken(CPPSubsetParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(CPPSubsetParser.RPAREN, 0); }
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public PostfixSuffixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postfixSuffix; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPPSubsetParserVisitor ) return ((CPPSubsetParserVisitor<? extends T>)visitor).visitPostfixSuffix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PostfixSuffixContext postfixSuffix() throws RecognitionException {
		PostfixSuffixContext _localctx = new PostfixSuffixContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_postfixSuffix);
		int _la;
		try {
			setState(217);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACK:
				enterOuterAlt(_localctx, 1);
				{
				setState(208);
				match(LBRACK);
				setState(209);
				expr();
				setState(210);
				match(RBRACK);
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(212);
				match(LPAREN);
				setState(214);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 3298534883680L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 16920577L) != 0)) {
					{
					setState(213);
					argList();
					}
				}

				setState(216);
				match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AtomContext extends ParserRuleContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(CPPSubsetParser.IDENTIFIER, 0); }
		public TerminalNode LPAREN() { return getToken(CPPSubsetParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(CPPSubsetParser.RPAREN, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPPSubsetParserVisitor ) return ((CPPSubsetParserVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_atom);
		try {
			setState(225);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FLOAT_LITERAL:
			case INT_LITERAL:
			case CHAR_LITERAL:
			case KW_TRUE:
			case KW_FALSE:
				enterOuterAlt(_localctx, 1);
				{
				setState(219);
				literal();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(220);
				match(IDENTIFIER);
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 3);
				{
				setState(221);
				match(LPAREN);
				setState(222);
				expr();
				setState(223);
				match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgListContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CPPSubsetParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CPPSubsetParser.COMMA, i);
		}
		public ArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPPSubsetParserVisitor ) return ((CPPSubsetParserVisitor<? extends T>)visitor).visitArgList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgListContext argList() throws RecognitionException {
		ArgListContext _localctx = new ArgListContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_argList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(227);
			expr();
			setState(232);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(228);
				match(COMMA);
				setState(229);
				expr();
				}
				}
				setState(234);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode INT_LITERAL() { return getToken(CPPSubsetParser.INT_LITERAL, 0); }
		public TerminalNode FLOAT_LITERAL() { return getToken(CPPSubsetParser.FLOAT_LITERAL, 0); }
		public TerminalNode CHAR_LITERAL() { return getToken(CPPSubsetParser.CHAR_LITERAL, 0); }
		public TerminalNode KW_TRUE() { return getToken(CPPSubsetParser.KW_TRUE, 0); }
		public TerminalNode KW_FALSE() { return getToken(CPPSubsetParser.KW_FALSE, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPPSubsetParserVisitor ) return ((CPPSubsetParserVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 3298534883680L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001[\u00ee\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0001\u0000\u0001\u0000"+
		"\u0005\u00009\b\u0000\n\u0000\f\u0000<\t\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001C\b\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003O\b\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004"+
		"W\b\u0004\n\u0004\f\u0004Z\t\u0004\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0005\u0006a\b\u0006\n\u0006\f\u0006d\t\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0003\u0007n\b\u0007\u0001\b\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\tz\b\t"+
		"\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u0083"+
		"\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\f\u0001\f\u0003\f\u008d\b\f\u0001\f\u0001\f\u0001\r\u0001"+
		"\r\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f"+
		"\u0098\b\u000f\n\u000f\f\u000f\u009b\t\u000f\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0005\u0010\u00a0\b\u0010\n\u0010\f\u0010\u00a3\t\u0010\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0005\u0011\u00a8\b\u0011\n\u0011\f\u0011\u00ab"+
		"\t\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0005\u0012\u00b0\b\u0012"+
		"\n\u0012\f\u0012\u00b3\t\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0005"+
		"\u0013\u00b8\b\u0013\n\u0013\f\u0013\u00bb\t\u0013\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0005\u0014\u00c0\b\u0014\n\u0014\f\u0014\u00c3\t\u0014\u0001"+
		"\u0015\u0003\u0015\u00c6\b\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001"+
		"\u0016\u0005\u0016\u00cc\b\u0016\n\u0016\f\u0016\u00cf\t\u0016\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017"+
		"\u00d7\b\u0017\u0001\u0017\u0003\u0017\u00da\b\u0017\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u00e2"+
		"\b\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0005\u0019\u00e7\b\u0019"+
		"\n\u0019\f\u0019\u00ea\t\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0000"+
		"\u0000\u001b\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016"+
		"\u0018\u001a\u001c\u001e \"$&(*,.024\u0000\u0007\u0003\u0000\t\u000b\r"+
		"\r\u0010\u0010\u0001\u000045\u0002\u000023TU\u0001\u0000NO\u0002\u0000"+
		"MMPQ\u0002\u0000NOSS\u0003\u0000\u0005\u0006\b\b()\u00ed\u0000:\u0001"+
		"\u0000\u0000\u0000\u0002?\u0001\u0000\u0000\u0000\u0004F\u0001\u0000\u0000"+
		"\u0000\u0006J\u0001\u0000\u0000\u0000\bS\u0001\u0000\u0000\u0000\n[\u0001"+
		"\u0000\u0000\u0000\f^\u0001\u0000\u0000\u0000\u000em\u0001\u0000\u0000"+
		"\u0000\u0010o\u0001\u0000\u0000\u0000\u0012t\u0001\u0000\u0000\u0000\u0014"+
		"{\u0001\u0000\u0000\u0000\u0016\u0084\u0001\u0000\u0000\u0000\u0018\u008a"+
		"\u0001\u0000\u0000\u0000\u001a\u0090\u0001\u0000\u0000\u0000\u001c\u0092"+
		"\u0001\u0000\u0000\u0000\u001e\u0094\u0001\u0000\u0000\u0000 \u009c\u0001"+
		"\u0000\u0000\u0000\"\u00a4\u0001\u0000\u0000\u0000$\u00ac\u0001\u0000"+
		"\u0000\u0000&\u00b4\u0001\u0000\u0000\u0000(\u00bc\u0001\u0000\u0000\u0000"+
		"*\u00c5\u0001\u0000\u0000\u0000,\u00c9\u0001\u0000\u0000\u0000.\u00d9"+
		"\u0001\u0000\u0000\u00000\u00e1\u0001\u0000\u0000\u00002\u00e3\u0001\u0000"+
		"\u0000\u00004\u00eb\u0001\u0000\u0000\u000069\u0003\u0002\u0001\u0000"+
		"79\u0003\u0006\u0003\u000086\u0001\u0000\u0000\u000087\u0001\u0000\u0000"+
		"\u00009<\u0001\u0000\u0000\u0000:8\u0001\u0000\u0000\u0000:;\u0001\u0000"+
		"\u0000\u0000;=\u0001\u0000\u0000\u0000<:\u0001\u0000\u0000\u0000=>\u0005"+
		"\u0000\u0000\u0001>\u0001\u0001\u0000\u0000\u0000?@\u0003\u001a\r\u0000"+
		"@B\u0005Z\u0000\u0000AC\u0003\u0004\u0002\u0000BA\u0001\u0000\u0000\u0000"+
		"BC\u0001\u0000\u0000\u0000CD\u0001\u0000\u0000\u0000DE\u0005H\u0000\u0000"+
		"E\u0003\u0001\u0000\u0000\u0000FG\u0005F\u0000\u0000GH\u0005\u0006\u0000"+
		"\u0000HI\u0005G\u0000\u0000I\u0005\u0001\u0000\u0000\u0000JK\u0003\u001a"+
		"\r\u0000KL\u0005Z\u0000\u0000LN\u0005B\u0000\u0000MO\u0003\b\u0004\u0000"+
		"NM\u0001\u0000\u0000\u0000NO\u0001\u0000\u0000\u0000OP\u0001\u0000\u0000"+
		"\u0000PQ\u0005C\u0000\u0000QR\u0003\f\u0006\u0000R\u0007\u0001\u0000\u0000"+
		"\u0000SX\u0003\n\u0005\u0000TU\u0005I\u0000\u0000UW\u0003\n\u0005\u0000"+
		"VT\u0001\u0000\u0000\u0000WZ\u0001\u0000\u0000\u0000XV\u0001\u0000\u0000"+
		"\u0000XY\u0001\u0000\u0000\u0000Y\t\u0001\u0000\u0000\u0000ZX\u0001\u0000"+
		"\u0000\u0000[\\\u0003\u001a\r\u0000\\]\u0005Z\u0000\u0000]\u000b\u0001"+
		"\u0000\u0000\u0000^b\u0005D\u0000\u0000_a\u0003\u000e\u0007\u0000`_\u0001"+
		"\u0000\u0000\u0000ad\u0001\u0000\u0000\u0000b`\u0001\u0000\u0000\u0000"+
		"bc\u0001\u0000\u0000\u0000ce\u0001\u0000\u0000\u0000db\u0001\u0000\u0000"+
		"\u0000ef\u0005E\u0000\u0000f\r\u0001\u0000\u0000\u0000gn\u0003\u0002\u0001"+
		"\u0000hn\u0003\u0010\b\u0000in\u0003\u0014\n\u0000jn\u0003\u0016\u000b"+
		"\u0000kn\u0003\u0018\f\u0000ln\u0003\f\u0006\u0000mg\u0001\u0000\u0000"+
		"\u0000mh\u0001\u0000\u0000\u0000mi\u0001\u0000\u0000\u0000mj\u0001\u0000"+
		"\u0000\u0000mk\u0001\u0000\u0000\u0000ml\u0001\u0000\u0000\u0000n\u000f"+
		"\u0001\u0000\u0000\u0000op\u0003\u0012\t\u0000pq\u0005Y\u0000\u0000qr"+
		"\u0003\u001c\u000e\u0000rs\u0005H\u0000\u0000s\u0011\u0001\u0000\u0000"+
		"\u0000ty\u0005Z\u0000\u0000uv\u0005F\u0000\u0000vw\u0003\u001c\u000e\u0000"+
		"wx\u0005G\u0000\u0000xz\u0001\u0000\u0000\u0000yu\u0001\u0000\u0000\u0000"+
		"yz\u0001\u0000\u0000\u0000z\u0013\u0001\u0000\u0000\u0000{|\u0005\u0016"+
		"\u0000\u0000|}\u0005B\u0000\u0000}~\u0003\u001c\u000e\u0000~\u007f\u0005"+
		"C\u0000\u0000\u007f\u0082\u0003\f\u0006\u0000\u0080\u0081\u0005\u0017"+
		"\u0000\u0000\u0081\u0083\u0003\f\u0006\u0000\u0082\u0080\u0001\u0000\u0000"+
		"\u0000\u0082\u0083\u0001\u0000\u0000\u0000\u0083\u0015\u0001\u0000\u0000"+
		"\u0000\u0084\u0085\u0005\u0018\u0000\u0000\u0085\u0086\u0005B\u0000\u0000"+
		"\u0086\u0087\u0003\u001c\u000e\u0000\u0087\u0088\u0005C\u0000\u0000\u0088"+
		"\u0089\u0003\f\u0006\u0000\u0089\u0017\u0001\u0000\u0000\u0000\u008a\u008c"+
		"\u0005\u001c\u0000\u0000\u008b\u008d\u0003\u001c\u000e\u0000\u008c\u008b"+
		"\u0001\u0000\u0000\u0000\u008c\u008d\u0001\u0000\u0000\u0000\u008d\u008e"+
		"\u0001\u0000\u0000\u0000\u008e\u008f\u0005H\u0000\u0000\u008f\u0019\u0001"+
		"\u0000\u0000\u0000\u0090\u0091\u0007\u0000\u0000\u0000\u0091\u001b\u0001"+
		"\u0000\u0000\u0000\u0092\u0093\u0003\u001e\u000f\u0000\u0093\u001d\u0001"+
		"\u0000\u0000\u0000\u0094\u0099\u0003 \u0010\u0000\u0095\u0096\u00057\u0000"+
		"\u0000\u0096\u0098\u0003 \u0010\u0000\u0097\u0095\u0001\u0000\u0000\u0000"+
		"\u0098\u009b\u0001\u0000\u0000\u0000\u0099\u0097\u0001\u0000\u0000\u0000"+
		"\u0099\u009a\u0001\u0000\u0000\u0000\u009a\u001f\u0001\u0000\u0000\u0000"+
		"\u009b\u0099\u0001\u0000\u0000\u0000\u009c\u00a1\u0003\"\u0011\u0000\u009d"+
		"\u009e\u00056\u0000\u0000\u009e\u00a0\u0003\"\u0011\u0000\u009f\u009d"+
		"\u0001\u0000\u0000\u0000\u00a0\u00a3\u0001\u0000\u0000\u0000\u00a1\u009f"+
		"\u0001\u0000\u0000\u0000\u00a1\u00a2\u0001\u0000\u0000\u0000\u00a2!\u0001"+
		"\u0000\u0000\u0000\u00a3\u00a1\u0001\u0000\u0000\u0000\u00a4\u00a9\u0003"+
		"$\u0012\u0000\u00a5\u00a6\u0007\u0001\u0000\u0000\u00a6\u00a8\u0003$\u0012"+
		"\u0000\u00a7\u00a5\u0001\u0000\u0000\u0000\u00a8\u00ab\u0001\u0000\u0000"+
		"\u0000\u00a9\u00a7\u0001\u0000\u0000\u0000\u00a9\u00aa\u0001\u0000\u0000"+
		"\u0000\u00aa#\u0001\u0000\u0000\u0000\u00ab\u00a9\u0001\u0000\u0000\u0000"+
		"\u00ac\u00b1\u0003&\u0013\u0000\u00ad\u00ae\u0007\u0002\u0000\u0000\u00ae"+
		"\u00b0\u0003&\u0013\u0000\u00af\u00ad\u0001\u0000\u0000\u0000\u00b0\u00b3"+
		"\u0001\u0000\u0000\u0000\u00b1\u00af\u0001\u0000\u0000\u0000\u00b1\u00b2"+
		"\u0001\u0000\u0000\u0000\u00b2%\u0001\u0000\u0000\u0000\u00b3\u00b1\u0001"+
		"\u0000\u0000\u0000\u00b4\u00b9\u0003(\u0014\u0000\u00b5\u00b6\u0007\u0003"+
		"\u0000\u0000\u00b6\u00b8\u0003(\u0014\u0000\u00b7\u00b5\u0001\u0000\u0000"+
		"\u0000\u00b8\u00bb\u0001\u0000\u0000\u0000\u00b9\u00b7\u0001\u0000\u0000"+
		"\u0000\u00b9\u00ba\u0001\u0000\u0000\u0000\u00ba\'\u0001\u0000\u0000\u0000"+
		"\u00bb\u00b9\u0001\u0000\u0000\u0000\u00bc\u00c1\u0003*\u0015\u0000\u00bd"+
		"\u00be\u0007\u0004\u0000\u0000\u00be\u00c0\u0003*\u0015\u0000\u00bf\u00bd"+
		"\u0001\u0000\u0000\u0000\u00c0\u00c3\u0001\u0000\u0000\u0000\u00c1\u00bf"+
		"\u0001\u0000\u0000\u0000\u00c1\u00c2\u0001\u0000\u0000\u0000\u00c2)\u0001"+
		"\u0000\u0000\u0000\u00c3\u00c1\u0001\u0000\u0000\u0000\u00c4\u00c6\u0007"+
		"\u0005\u0000\u0000\u00c5\u00c4\u0001\u0000\u0000\u0000\u00c5\u00c6\u0001"+
		"\u0000\u0000\u0000\u00c6\u00c7\u0001\u0000\u0000\u0000\u00c7\u00c8\u0003"+
		",\u0016\u0000\u00c8+\u0001\u0000\u0000\u0000\u00c9\u00cd\u00030\u0018"+
		"\u0000\u00ca\u00cc\u0003.\u0017\u0000\u00cb\u00ca\u0001\u0000\u0000\u0000"+
		"\u00cc\u00cf\u0001\u0000\u0000\u0000\u00cd\u00cb\u0001\u0000\u0000\u0000"+
		"\u00cd\u00ce\u0001\u0000\u0000\u0000\u00ce-\u0001\u0000\u0000\u0000\u00cf"+
		"\u00cd\u0001\u0000\u0000\u0000\u00d0\u00d1\u0005F\u0000\u0000\u00d1\u00d2"+
		"\u0003\u001c\u000e\u0000\u00d2\u00d3\u0005G\u0000\u0000\u00d3\u00da\u0001"+
		"\u0000\u0000\u0000\u00d4\u00d6\u0005B\u0000\u0000\u00d5\u00d7\u00032\u0019"+
		"\u0000\u00d6\u00d5\u0001\u0000\u0000\u0000\u00d6\u00d7\u0001\u0000\u0000"+
		"\u0000\u00d7\u00d8\u0001\u0000\u0000\u0000\u00d8\u00da\u0005C\u0000\u0000"+
		"\u00d9\u00d0\u0001\u0000\u0000\u0000\u00d9\u00d4\u0001\u0000\u0000\u0000"+
		"\u00da/\u0001\u0000\u0000\u0000\u00db\u00e2\u00034\u001a\u0000\u00dc\u00e2"+
		"\u0005Z\u0000\u0000\u00dd\u00de\u0005B\u0000\u0000\u00de\u00df\u0003\u001c"+
		"\u000e\u0000\u00df\u00e0\u0005C\u0000\u0000\u00e0\u00e2\u0001\u0000\u0000"+
		"\u0000\u00e1\u00db\u0001\u0000\u0000\u0000\u00e1\u00dc\u0001\u0000\u0000"+
		"\u0000\u00e1\u00dd\u0001\u0000\u0000\u0000\u00e21\u0001\u0000\u0000\u0000"+
		"\u00e3\u00e8\u0003\u001c\u000e\u0000\u00e4\u00e5\u0005I\u0000\u0000\u00e5"+
		"\u00e7\u0003\u001c\u000e\u0000\u00e6\u00e4\u0001\u0000\u0000\u0000\u00e7"+
		"\u00ea\u0001\u0000\u0000\u0000\u00e8\u00e6\u0001\u0000\u0000\u0000\u00e8"+
		"\u00e9\u0001\u0000\u0000\u0000\u00e93\u0001\u0000\u0000\u0000\u00ea\u00e8"+
		"\u0001\u0000\u0000\u0000\u00eb\u00ec\u0007\u0006\u0000\u0000\u00ec5\u0001"+
		"\u0000\u0000\u0000\u00168:BNXbmy\u0082\u008c\u0099\u00a1\u00a9\u00b1\u00b9"+
		"\u00c1\u00c5\u00cd\u00d6\u00d9\u00e1\u00e8";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}