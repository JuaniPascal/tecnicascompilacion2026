// Generated from c:\Users\Juani\Documents\GitHub\tecnicascompilacion2026\cpp compiler\CPPSubsetParser.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CPPSubsetParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

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
		RULE_forStmt = 12, RULE_forInit = 13, RULE_forUpdate = 14, RULE_breakStmt = 15, 
		RULE_continueStmt = 16, RULE_returnStmt = 17, RULE_typeName = 18, RULE_expr = 19, 
		RULE_orExpr = 20, RULE_andExpr = 21, RULE_eqExpr = 22, RULE_relExpr = 23, 
		RULE_addExpr = 24, RULE_mulExpr = 25, RULE_unary = 26, RULE_postfix = 27, 
		RULE_postfixSuffix = 28, RULE_atom = 29, RULE_argList = 30, RULE_literal = 31;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "varDecl", "arrayDim", "funcDecl", "paramList", "param", "block", 
			"statement", "assignment", "lvalue", "ifStmt", "whileStmt", "forStmt", 
			"forInit", "forUpdate", "breakStmt", "continueStmt", "returnStmt", "typeName", 
			"expr", "orExpr", "andExpr", "eqExpr", "relExpr", "addExpr", "mulExpr", 
			"unary", "postfix", "postfixSuffix", "atom", "argList", "literal"
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << KW_VOID) | (1L << KW_BOOL) | (1L << KW_CHAR) | (1L << KW_INT) | (1L << KW_DOUBLE))) != 0)) {
				{
				setState(66);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(64);
					varDecl();
					}
					break;
				case 2:
					{
					setState(65);
					funcDecl();
					}
					break;
				}
				}
				setState(70);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(71);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).enterVarDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).exitVarDecl(this);
		}
	}

	public final VarDeclContext varDecl() throws RecognitionException {
		VarDeclContext _localctx = new VarDeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_varDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			typeName();
			setState(74);
			match(IDENTIFIER);
			setState(76);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACK) {
				{
				setState(75);
				arrayDim();
				}
			}

			setState(78);
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

	public static class ArrayDimContext extends ParserRuleContext {
		public TerminalNode LBRACK() { return getToken(CPPSubsetParser.LBRACK, 0); }
		public TerminalNode INT_LITERAL() { return getToken(CPPSubsetParser.INT_LITERAL, 0); }
		public TerminalNode RBRACK() { return getToken(CPPSubsetParser.RBRACK, 0); }
		public ArrayDimContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayDim; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).enterArrayDim(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).exitArrayDim(this);
		}
	}

	public final ArrayDimContext arrayDim() throws RecognitionException {
		ArrayDimContext _localctx = new ArrayDimContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_arrayDim);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(LBRACK);
			setState(81);
			match(INT_LITERAL);
			setState(82);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).enterFuncDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).exitFuncDecl(this);
		}
	}

	public final FuncDeclContext funcDecl() throws RecognitionException {
		FuncDeclContext _localctx = new FuncDeclContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_funcDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			typeName();
			setState(85);
			match(IDENTIFIER);
			setState(86);
			match(LPAREN);
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << KW_VOID) | (1L << KW_BOOL) | (1L << KW_CHAR) | (1L << KW_INT) | (1L << KW_DOUBLE))) != 0)) {
				{
				setState(87);
				paramList();
				}
			}

			setState(90);
			match(RPAREN);
			setState(91);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).enterParamList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).exitParamList(this);
		}
	}

	public final ParamListContext paramList() throws RecognitionException {
		ParamListContext _localctx = new ParamListContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_paramList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			param();
			setState(98);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(94);
				match(COMMA);
				setState(95);
				param();
				}
				}
				setState(100);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).exitParam(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			typeName();
			setState(102);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(LBRACE);
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << KW_VOID) | (1L << KW_BOOL) | (1L << KW_CHAR) | (1L << KW_INT) | (1L << KW_DOUBLE) | (1L << KW_IF) | (1L << KW_WHILE) | (1L << KW_FOR) | (1L << KW_BREAK) | (1L << KW_CONTINUE) | (1L << KW_RETURN))) != 0) || _la==LBRACE || _la==IDENTIFIER) {
				{
				{
				setState(105);
				statement();
				}
				}
				setState(110);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(111);
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
		public ForStmtContext forStmt() {
			return getRuleContext(ForStmtContext.class,0);
		}
		public BreakStmtContext breakStmt() {
			return getRuleContext(BreakStmtContext.class,0);
		}
		public ContinueStmtContext continueStmt() {
			return getRuleContext(ContinueStmtContext.class,0);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_statement);
		try {
			setState(122);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case KW_VOID:
			case KW_BOOL:
			case KW_CHAR:
			case KW_INT:
			case KW_DOUBLE:
				enterOuterAlt(_localctx, 1);
				{
				setState(113);
				varDecl();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(114);
				assignment();
				}
				break;
			case KW_IF:
				enterOuterAlt(_localctx, 3);
				{
				setState(115);
				ifStmt();
				}
				break;
			case KW_WHILE:
				enterOuterAlt(_localctx, 4);
				{
				setState(116);
				whileStmt();
				}
				break;
			case KW_FOR:
				enterOuterAlt(_localctx, 5);
				{
				setState(117);
				forStmt();
				}
				break;
			case KW_BREAK:
				enterOuterAlt(_localctx, 6);
				{
				setState(118);
				breakStmt();
				}
				break;
			case KW_CONTINUE:
				enterOuterAlt(_localctx, 7);
				{
				setState(119);
				continueStmt();
				}
				break;
			case KW_RETURN:
				enterOuterAlt(_localctx, 8);
				{
				setState(120);
				returnStmt();
				}
				break;
			case LBRACE:
				enterOuterAlt(_localctx, 9);
				{
				setState(121);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).exitAssignment(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			lvalue();
			setState(125);
			match(ASSIGN);
			setState(126);
			expr();
			setState(127);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).enterLvalue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).exitLvalue(this);
		}
	}

	public final LvalueContext lvalue() throws RecognitionException {
		LvalueContext _localctx = new LvalueContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_lvalue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129);
			match(IDENTIFIER);
			setState(134);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACK) {
				{
				setState(130);
				match(LBRACK);
				setState(131);
				expr();
				setState(132);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).enterIfStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).exitIfStmt(this);
		}
	}

	public final IfStmtContext ifStmt() throws RecognitionException {
		IfStmtContext _localctx = new IfStmtContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_ifStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(KW_IF);
			setState(137);
			match(LPAREN);
			setState(138);
			expr();
			setState(139);
			match(RPAREN);
			setState(140);
			block();
			setState(143);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==KW_ELSE) {
				{
				setState(141);
				match(KW_ELSE);
				setState(142);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).enterWhileStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).exitWhileStmt(this);
		}
	}

	public final WhileStmtContext whileStmt() throws RecognitionException {
		WhileStmtContext _localctx = new WhileStmtContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_whileStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			match(KW_WHILE);
			setState(146);
			match(LPAREN);
			setState(147);
			expr();
			setState(148);
			match(RPAREN);
			setState(149);
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

	public static class ForStmtContext extends ParserRuleContext {
		public TerminalNode KW_FOR() { return getToken(CPPSubsetParser.KW_FOR, 0); }
		public TerminalNode LPAREN() { return getToken(CPPSubsetParser.LPAREN, 0); }
		public List<TerminalNode> SEMI() { return getTokens(CPPSubsetParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(CPPSubsetParser.SEMI, i);
		}
		public TerminalNode RPAREN() { return getToken(CPPSubsetParser.RPAREN, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ForInitContext forInit() {
			return getRuleContext(ForInitContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ForUpdateContext forUpdate() {
			return getRuleContext(ForUpdateContext.class,0);
		}
		public ForStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).enterForStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).exitForStmt(this);
		}
	}

	public final ForStmtContext forStmt() throws RecognitionException {
		ForStmtContext _localctx = new ForStmtContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_forStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			match(KW_FOR);
			setState(152);
			match(LPAREN);
			setState(154);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << KW_VOID) | (1L << KW_BOOL) | (1L << KW_CHAR) | (1L << KW_INT) | (1L << KW_DOUBLE))) != 0) || _la==IDENTIFIER) {
				{
				setState(153);
				forInit();
				}
			}

			setState(156);
			match(SEMI);
			setState(158);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FLOAT_LITERAL) | (1L << INT_LITERAL) | (1L << CHAR_LITERAL) | (1L << KW_TRUE) | (1L << KW_FALSE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (LPAREN - 66)) | (1L << (PLUS - 66)) | (1L << (MINUS - 66)) | (1L << (NOT - 66)) | (1L << (IDENTIFIER - 66)))) != 0)) {
				{
				setState(157);
				expr();
				}
			}

			setState(160);
			match(SEMI);
			setState(162);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(161);
				forUpdate();
				}
			}

			setState(164);
			match(RPAREN);
			setState(165);
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

	public static class ForInitContext extends ParserRuleContext {
		public ForInitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forInit; }
	 
		public ForInitContext() { }
		public void copyFrom(ForInitContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ForInitAssignContext extends ForInitContext {
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(CPPSubsetParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ForInitAssignContext(ForInitContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).enterForInitAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).exitForInitAssign(this);
		}
	}
	public static class ForInitDeclContext extends ForInitContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(CPPSubsetParser.IDENTIFIER, 0); }
		public TerminalNode ASSIGN() { return getToken(CPPSubsetParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ForInitDeclContext(ForInitContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).enterForInitDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).exitForInitDecl(this);
		}
	}

	public final ForInitContext forInit() throws RecognitionException {
		ForInitContext _localctx = new ForInitContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_forInit);
		try {
			setState(176);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case KW_VOID:
			case KW_BOOL:
			case KW_CHAR:
			case KW_INT:
			case KW_DOUBLE:
				_localctx = new ForInitDeclContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(167);
				typeName();
				setState(168);
				match(IDENTIFIER);
				setState(169);
				match(ASSIGN);
				setState(170);
				expr();
				}
				break;
			case IDENTIFIER:
				_localctx = new ForInitAssignContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(172);
				lvalue();
				setState(173);
				match(ASSIGN);
				setState(174);
				expr();
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

	public static class ForUpdateContext extends ParserRuleContext {
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(CPPSubsetParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ForUpdateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forUpdate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).enterForUpdate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).exitForUpdate(this);
		}
	}

	public final ForUpdateContext forUpdate() throws RecognitionException {
		ForUpdateContext _localctx = new ForUpdateContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_forUpdate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			lvalue();
			setState(179);
			match(ASSIGN);
			setState(180);
			expr();
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

	public static class BreakStmtContext extends ParserRuleContext {
		public TerminalNode KW_BREAK() { return getToken(CPPSubsetParser.KW_BREAK, 0); }
		public TerminalNode SEMI() { return getToken(CPPSubsetParser.SEMI, 0); }
		public BreakStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).enterBreakStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).exitBreakStmt(this);
		}
	}

	public final BreakStmtContext breakStmt() throws RecognitionException {
		BreakStmtContext _localctx = new BreakStmtContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_breakStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182);
			match(KW_BREAK);
			setState(183);
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

	public static class ContinueStmtContext extends ParserRuleContext {
		public TerminalNode KW_CONTINUE() { return getToken(CPPSubsetParser.KW_CONTINUE, 0); }
		public TerminalNode SEMI() { return getToken(CPPSubsetParser.SEMI, 0); }
		public ContinueStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continueStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).enterContinueStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).exitContinueStmt(this);
		}
	}

	public final ContinueStmtContext continueStmt() throws RecognitionException {
		ContinueStmtContext _localctx = new ContinueStmtContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_continueStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			match(KW_CONTINUE);
			setState(186);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).enterReturnStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).exitReturnStmt(this);
		}
	}

	public final ReturnStmtContext returnStmt() throws RecognitionException {
		ReturnStmtContext _localctx = new ReturnStmtContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_returnStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			match(KW_RETURN);
			setState(190);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FLOAT_LITERAL) | (1L << INT_LITERAL) | (1L << CHAR_LITERAL) | (1L << KW_TRUE) | (1L << KW_FALSE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (LPAREN - 66)) | (1L << (PLUS - 66)) | (1L << (MINUS - 66)) | (1L << (NOT - 66)) | (1L << (IDENTIFIER - 66)))) != 0)) {
				{
				setState(189);
				expr();
				}
			}

			setState(192);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).enterTypeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).exitTypeName(this);
		}
	}

	public final TypeNameContext typeName() throws RecognitionException {
		TypeNameContext _localctx = new TypeNameContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_typeName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << KW_VOID) | (1L << KW_BOOL) | (1L << KW_CHAR) | (1L << KW_INT) | (1L << KW_DOUBLE))) != 0)) ) {
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

	public static class ExprContext extends ParserRuleContext {
		public OrExprContext orExpr() {
			return getRuleContext(OrExprContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).enterOrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).exitOrExpr(this);
		}
	}

	public final OrExprContext orExpr() throws RecognitionException {
		OrExprContext _localctx = new OrExprContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_orExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			andExpr();
			setState(203);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR_OR) {
				{
				{
				setState(199);
				match(OR_OR);
				setState(200);
				andExpr();
				}
				}
				setState(205);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).enterAndExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).exitAndExpr(this);
		}
	}

	public final AndExprContext andExpr() throws RecognitionException {
		AndExprContext _localctx = new AndExprContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_andExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			eqExpr();
			setState(211);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND_AND) {
				{
				{
				setState(207);
				match(AND_AND);
				setState(208);
				eqExpr();
				}
				}
				setState(213);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).enterEqExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).exitEqExpr(this);
		}
	}

	public final EqExprContext eqExpr() throws RecognitionException {
		EqExprContext _localctx = new EqExprContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_eqExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			relExpr();
			setState(219);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EQ || _la==NE) {
				{
				{
				setState(215);
				_la = _input.LA(1);
				if ( !(_la==EQ || _la==NE) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(216);
				relExpr();
				}
				}
				setState(221);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).enterRelExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).exitRelExpr(this);
		}
	}

	public final RelExprContext relExpr() throws RecognitionException {
		RelExprContext _localctx = new RelExprContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_relExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			addExpr();
			setState(227);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 50)) & ~0x3f) == 0 && ((1L << (_la - 50)) & ((1L << (LE - 50)) | (1L << (GE - 50)) | (1L << (LT - 50)) | (1L << (GT - 50)))) != 0)) {
				{
				{
				setState(223);
				_la = _input.LA(1);
				if ( !(((((_la - 50)) & ~0x3f) == 0 && ((1L << (_la - 50)) & ((1L << (LE - 50)) | (1L << (GE - 50)) | (1L << (LT - 50)) | (1L << (GT - 50)))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(224);
				addExpr();
				}
				}
				setState(229);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).enterAddExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).exitAddExpr(this);
		}
	}

	public final AddExprContext addExpr() throws RecognitionException {
		AddExprContext _localctx = new AddExprContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_addExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			mulExpr();
			setState(235);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PLUS || _la==MINUS) {
				{
				{
				setState(231);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(232);
				mulExpr();
				}
				}
				setState(237);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).enterMulExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).exitMulExpr(this);
		}
	}

	public final MulExprContext mulExpr() throws RecognitionException {
		MulExprContext _localctx = new MulExprContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_mulExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			unary();
			setState(243);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 77)) & ~0x3f) == 0 && ((1L << (_la - 77)) & ((1L << (STAR - 77)) | (1L << (DIV - 77)) | (1L << (MOD - 77)))) != 0)) {
				{
				{
				setState(239);
				_la = _input.LA(1);
				if ( !(((((_la - 77)) & ~0x3f) == 0 && ((1L << (_la - 77)) & ((1L << (STAR - 77)) | (1L << (DIV - 77)) | (1L << (MOD - 77)))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(240);
				unary();
				}
				}
				setState(245);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).enterUnary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).exitUnary(this);
		}
	}

	public final UnaryContext unary() throws RecognitionException {
		UnaryContext _localctx = new UnaryContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_unary);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 78)) & ~0x3f) == 0 && ((1L << (_la - 78)) & ((1L << (PLUS - 78)) | (1L << (MINUS - 78)) | (1L << (NOT - 78)))) != 0)) {
				{
				setState(246);
				_la = _input.LA(1);
				if ( !(((((_la - 78)) & ~0x3f) == 0 && ((1L << (_la - 78)) & ((1L << (PLUS - 78)) | (1L << (MINUS - 78)) | (1L << (NOT - 78)))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(249);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).enterPostfix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).exitPostfix(this);
		}
	}

	public final PostfixContext postfix() throws RecognitionException {
		PostfixContext _localctx = new PostfixContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_postfix);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251);
			atom();
			setState(255);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LPAREN || _la==LBRACK) {
				{
				{
				setState(252);
				postfixSuffix();
				}
				}
				setState(257);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).enterPostfixSuffix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).exitPostfixSuffix(this);
		}
	}

	public final PostfixSuffixContext postfixSuffix() throws RecognitionException {
		PostfixSuffixContext _localctx = new PostfixSuffixContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_postfixSuffix);
		int _la;
		try {
			setState(267);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACK:
				enterOuterAlt(_localctx, 1);
				{
				setState(258);
				match(LBRACK);
				setState(259);
				expr();
				setState(260);
				match(RBRACK);
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(262);
				match(LPAREN);
				setState(264);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FLOAT_LITERAL) | (1L << INT_LITERAL) | (1L << CHAR_LITERAL) | (1L << KW_TRUE) | (1L << KW_FALSE))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (LPAREN - 66)) | (1L << (PLUS - 66)) | (1L << (MINUS - 66)) | (1L << (NOT - 66)) | (1L << (IDENTIFIER - 66)))) != 0)) {
					{
					setState(263);
					argList();
					}
				}

				setState(266);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).exitAtom(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_atom);
		try {
			setState(275);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FLOAT_LITERAL:
			case INT_LITERAL:
			case CHAR_LITERAL:
			case KW_TRUE:
			case KW_FALSE:
				enterOuterAlt(_localctx, 1);
				{
				setState(269);
				literal();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(270);
				match(IDENTIFIER);
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 3);
				{
				setState(271);
				match(LPAREN);
				setState(272);
				expr();
				setState(273);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).enterArgList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).exitArgList(this);
		}
	}

	public final ArgListContext argList() throws RecognitionException {
		ArgListContext _localctx = new ArgListContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_argList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			expr();
			setState(282);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(278);
				match(COMMA);
				setState(279);
				expr();
				}
				}
				setState(284);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPPSubsetParserListener ) ((CPPSubsetParserListener)listener).exitLiteral(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FLOAT_LITERAL) | (1L << INT_LITERAL) | (1L << CHAR_LITERAL) | (1L << KW_TRUE) | (1L << KW_FALSE))) != 0)) ) {
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3]\u0122\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\3\2\3\2\7\2E\n\2\f\2\16\2H\13\2\3\2\3\2\3\3\3\3\3\3\5\3O\n\3\3\3\3"+
		"\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\5\5[\n\5\3\5\3\5\3\5\3\6\3\6\3\6\7"+
		"\6c\n\6\f\6\16\6f\13\6\3\7\3\7\3\7\3\b\3\b\7\bm\n\b\f\b\16\bp\13\b\3\b"+
		"\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t}\n\t\3\n\3\n\3\n\3\n\3\n"+
		"\3\13\3\13\3\13\3\13\3\13\5\13\u0089\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\5\f\u0092\n\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\5\16\u009d\n\16"+
		"\3\16\3\16\5\16\u00a1\n\16\3\16\3\16\5\16\u00a5\n\16\3\16\3\16\3\16\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00b3\n\17\3\20\3\20"+
		"\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\5\23\u00c1\n\23\3\23"+
		"\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\26\7\26\u00cc\n\26\f\26\16\26\u00cf"+
		"\13\26\3\27\3\27\3\27\7\27\u00d4\n\27\f\27\16\27\u00d7\13\27\3\30\3\30"+
		"\3\30\7\30\u00dc\n\30\f\30\16\30\u00df\13\30\3\31\3\31\3\31\7\31\u00e4"+
		"\n\31\f\31\16\31\u00e7\13\31\3\32\3\32\3\32\7\32\u00ec\n\32\f\32\16\32"+
		"\u00ef\13\32\3\33\3\33\3\33\7\33\u00f4\n\33\f\33\16\33\u00f7\13\33\3\34"+
		"\5\34\u00fa\n\34\3\34\3\34\3\35\3\35\7\35\u0100\n\35\f\35\16\35\u0103"+
		"\13\35\3\36\3\36\3\36\3\36\3\36\3\36\5\36\u010b\n\36\3\36\5\36\u010e\n"+
		"\36\3\37\3\37\3\37\3\37\3\37\3\37\5\37\u0116\n\37\3 \3 \3 \7 \u011b\n"+
		" \f \16 \u011e\13 \3!\3!\3!\2\2\"\2\4\6\b\n\f\16\20\22\24\26\30\32\34"+
		"\36 \"$&(*,.\60\62\64\668:<>@\2\t\5\2\13\r\17\17\22\22\3\2\66\67\4\2\64"+
		"\65VW\3\2PQ\4\2OORS\4\2PQUU\5\2\7\b\n\n*+\2\u0123\2F\3\2\2\2\4K\3\2\2"+
		"\2\6R\3\2\2\2\bV\3\2\2\2\n_\3\2\2\2\fg\3\2\2\2\16j\3\2\2\2\20|\3\2\2\2"+
		"\22~\3\2\2\2\24\u0083\3\2\2\2\26\u008a\3\2\2\2\30\u0093\3\2\2\2\32\u0099"+
		"\3\2\2\2\34\u00b2\3\2\2\2\36\u00b4\3\2\2\2 \u00b8\3\2\2\2\"\u00bb\3\2"+
		"\2\2$\u00be\3\2\2\2&\u00c4\3\2\2\2(\u00c6\3\2\2\2*\u00c8\3\2\2\2,\u00d0"+
		"\3\2\2\2.\u00d8\3\2\2\2\60\u00e0\3\2\2\2\62\u00e8\3\2\2\2\64\u00f0\3\2"+
		"\2\2\66\u00f9\3\2\2\28\u00fd\3\2\2\2:\u010d\3\2\2\2<\u0115\3\2\2\2>\u0117"+
		"\3\2\2\2@\u011f\3\2\2\2BE\5\4\3\2CE\5\b\5\2DB\3\2\2\2DC\3\2\2\2EH\3\2"+
		"\2\2FD\3\2\2\2FG\3\2\2\2GI\3\2\2\2HF\3\2\2\2IJ\7\2\2\3J\3\3\2\2\2KL\5"+
		"&\24\2LN\7\\\2\2MO\5\6\4\2NM\3\2\2\2NO\3\2\2\2OP\3\2\2\2PQ\7J\2\2Q\5\3"+
		"\2\2\2RS\7H\2\2ST\7\b\2\2TU\7I\2\2U\7\3\2\2\2VW\5&\24\2WX\7\\\2\2XZ\7"+
		"D\2\2Y[\5\n\6\2ZY\3\2\2\2Z[\3\2\2\2[\\\3\2\2\2\\]\7E\2\2]^\5\16\b\2^\t"+
		"\3\2\2\2_d\5\f\7\2`a\7K\2\2ac\5\f\7\2b`\3\2\2\2cf\3\2\2\2db\3\2\2\2de"+
		"\3\2\2\2e\13\3\2\2\2fd\3\2\2\2gh\5&\24\2hi\7\\\2\2i\r\3\2\2\2jn\7F\2\2"+
		"km\5\20\t\2lk\3\2\2\2mp\3\2\2\2nl\3\2\2\2no\3\2\2\2oq\3\2\2\2pn\3\2\2"+
		"\2qr\7G\2\2r\17\3\2\2\2s}\5\4\3\2t}\5\22\n\2u}\5\26\f\2v}\5\30\r\2w}\5"+
		"\32\16\2x}\5 \21\2y}\5\"\22\2z}\5$\23\2{}\5\16\b\2|s\3\2\2\2|t\3\2\2\2"+
		"|u\3\2\2\2|v\3\2\2\2|w\3\2\2\2|x\3\2\2\2|y\3\2\2\2|z\3\2\2\2|{\3\2\2\2"+
		"}\21\3\2\2\2~\177\5\24\13\2\177\u0080\7[\2\2\u0080\u0081\5(\25\2\u0081"+
		"\u0082\7J\2\2\u0082\23\3\2\2\2\u0083\u0088\7\\\2\2\u0084\u0085\7H\2\2"+
		"\u0085\u0086\5(\25\2\u0086\u0087\7I\2\2\u0087\u0089\3\2\2\2\u0088\u0084"+
		"\3\2\2\2\u0088\u0089\3\2\2\2\u0089\25\3\2\2\2\u008a\u008b\7\30\2\2\u008b"+
		"\u008c\7D\2\2\u008c\u008d\5(\25\2\u008d\u008e\7E\2\2\u008e\u0091\5\16"+
		"\b\2\u008f\u0090\7\31\2\2\u0090\u0092\5\16\b\2\u0091\u008f\3\2\2\2\u0091"+
		"\u0092\3\2\2\2\u0092\27\3\2\2\2\u0093\u0094\7\32\2\2\u0094\u0095\7D\2"+
		"\2\u0095\u0096\5(\25\2\u0096\u0097\7E\2\2\u0097\u0098\5\16\b\2\u0098\31"+
		"\3\2\2\2\u0099\u009a\7\33\2\2\u009a\u009c\7D\2\2\u009b\u009d\5\34\17\2"+
		"\u009c\u009b\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u00a0"+
		"\7J\2\2\u009f\u00a1\5(\25\2\u00a0\u009f\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1"+
		"\u00a2\3\2\2\2\u00a2\u00a4\7J\2\2\u00a3\u00a5\5\36\20\2\u00a4\u00a3\3"+
		"\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a7\7E\2\2\u00a7"+
		"\u00a8\5\16\b\2\u00a8\33\3\2\2\2\u00a9\u00aa\5&\24\2\u00aa\u00ab\7\\\2"+
		"\2\u00ab\u00ac\7[\2\2\u00ac\u00ad\5(\25\2\u00ad\u00b3\3\2\2\2\u00ae\u00af"+
		"\5\24\13\2\u00af\u00b0\7[\2\2\u00b0\u00b1\5(\25\2\u00b1\u00b3\3\2\2\2"+
		"\u00b2\u00a9\3\2\2\2\u00b2\u00ae\3\2\2\2\u00b3\35\3\2\2\2\u00b4\u00b5"+
		"\5\24\13\2\u00b5\u00b6\7[\2\2\u00b6\u00b7\5(\25\2\u00b7\37\3\2\2\2\u00b8"+
		"\u00b9\7\34\2\2\u00b9\u00ba\7J\2\2\u00ba!\3\2\2\2\u00bb\u00bc\7\35\2\2"+
		"\u00bc\u00bd\7J\2\2\u00bd#\3\2\2\2\u00be\u00c0\7\36\2\2\u00bf\u00c1\5"+
		"(\25\2\u00c0\u00bf\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2"+
		"\u00c3\7J\2\2\u00c3%\3\2\2\2\u00c4\u00c5\t\2\2\2\u00c5\'\3\2\2\2\u00c6"+
		"\u00c7\5*\26\2\u00c7)\3\2\2\2\u00c8\u00cd\5,\27\2\u00c9\u00ca\79\2\2\u00ca"+
		"\u00cc\5,\27\2\u00cb\u00c9\3\2\2\2\u00cc\u00cf\3\2\2\2\u00cd\u00cb\3\2"+
		"\2\2\u00cd\u00ce\3\2\2\2\u00ce+\3\2\2\2\u00cf\u00cd\3\2\2\2\u00d0\u00d5"+
		"\5.\30\2\u00d1\u00d2\78\2\2\u00d2\u00d4\5.\30\2\u00d3\u00d1\3\2\2\2\u00d4"+
		"\u00d7\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6-\3\2\2\2"+
		"\u00d7\u00d5\3\2\2\2\u00d8\u00dd\5\60\31\2\u00d9\u00da\t\3\2\2\u00da\u00dc"+
		"\5\60\31\2\u00db\u00d9\3\2\2\2\u00dc\u00df\3\2\2\2\u00dd\u00db\3\2\2\2"+
		"\u00dd\u00de\3\2\2\2\u00de/\3\2\2\2\u00df\u00dd\3\2\2\2\u00e0\u00e5\5"+
		"\62\32\2\u00e1\u00e2\t\4\2\2\u00e2\u00e4\5\62\32\2\u00e3\u00e1\3\2\2\2"+
		"\u00e4\u00e7\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\61"+
		"\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e8\u00ed\5\64\33\2\u00e9\u00ea\t\5\2\2"+
		"\u00ea\u00ec\5\64\33\2\u00eb\u00e9\3\2\2\2\u00ec\u00ef\3\2\2\2\u00ed\u00eb"+
		"\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\63\3\2\2\2\u00ef\u00ed\3\2\2\2\u00f0"+
		"\u00f5\5\66\34\2\u00f1\u00f2\t\6\2\2\u00f2\u00f4\5\66\34\2\u00f3\u00f1"+
		"\3\2\2\2\u00f4\u00f7\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6"+
		"\65\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f8\u00fa\t\7\2\2\u00f9\u00f8\3\2\2"+
		"\2\u00f9\u00fa\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00fc\58\35\2\u00fc\67"+
		"\3\2\2\2\u00fd\u0101\5<\37\2\u00fe\u0100\5:\36\2\u00ff\u00fe\3\2\2\2\u0100"+
		"\u0103\3\2\2\2\u0101\u00ff\3\2\2\2\u0101\u0102\3\2\2\2\u01029\3\2\2\2"+
		"\u0103\u0101\3\2\2\2\u0104\u0105\7H\2\2\u0105\u0106\5(\25\2\u0106\u0107"+
		"\7I\2\2\u0107\u010e\3\2\2\2\u0108\u010a\7D\2\2\u0109\u010b\5> \2\u010a"+
		"\u0109\3\2\2\2\u010a\u010b\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u010e\7E"+
		"\2\2\u010d\u0104\3\2\2\2\u010d\u0108\3\2\2\2\u010e;\3\2\2\2\u010f\u0116"+
		"\5@!\2\u0110\u0116\7\\\2\2\u0111\u0112\7D\2\2\u0112\u0113\5(\25\2\u0113"+
		"\u0114\7E\2\2\u0114\u0116\3\2\2\2\u0115\u010f\3\2\2\2\u0115\u0110\3\2"+
		"\2\2\u0115\u0111\3\2\2\2\u0116=\3\2\2\2\u0117\u011c\5(\25\2\u0118\u0119"+
		"\7K\2\2\u0119\u011b\5(\25\2\u011a\u0118\3\2\2\2\u011b\u011e\3\2\2\2\u011c"+
		"\u011a\3\2\2\2\u011c\u011d\3\2\2\2\u011d?\3\2\2\2\u011e\u011c\3\2\2\2"+
		"\u011f\u0120\t\b\2\2\u0120A\3\2\2\2\34DFNZdn|\u0088\u0091\u009c\u00a0"+
		"\u00a4\u00b2\u00c0\u00cd\u00d5\u00dd\u00e5\u00ed\u00f5\u00f9\u0101\u010a"+
		"\u010d\u0115\u011c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}