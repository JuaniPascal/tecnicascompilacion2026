// Generated from c:\Users\Juani\Documents\GitHub\tecnicascompilacion2026\cpp compiler\CPPSubsetLexer.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CPPSubsetLexer extends Lexer {
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
		PREPROC_CHANNEL=2;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN", "PREPROC_CHANNEL"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"LINE_COMMENT", "BLOCK_COMMENT", "PREPROCESSOR", "WS", "FLOAT_LITERAL", 
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
			"GT", "AMP", "BIT_OR", "BIT_XOR", "ASSIGN", "IDENTIFIER", "ERROR_CHAR", 
			"LETTER", "DIGIT", "HEX_DIGIT", "OCT_DIGIT", "DIGITS"
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


	public CPPSubsetLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CPPSubsetLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2]\u0297\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
		"\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]\t]\4^\t^\4_\t_\4"+
		"`\t`\4a\ta\3\2\3\2\3\2\3\2\7\2\u00c8\n\2\f\2\16\2\u00cb\13\2\3\2\3\2\3"+
		"\3\3\3\3\3\3\3\7\3\u00d3\n\3\f\3\16\3\u00d6\13\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\4\3\4\7\4\u00df\n\4\f\4\16\4\u00e2\13\4\3\4\3\4\3\5\6\5\u00e7\n\5\r\5"+
		"\16\5\u00e8\3\5\3\5\3\6\3\6\3\6\5\6\u00f0\n\6\3\6\3\6\5\6\u00f4\n\6\3"+
		"\6\5\6\u00f7\n\6\3\6\3\6\3\6\3\6\5\6\u00fd\n\6\3\6\5\6\u0100\n\6\3\6\3"+
		"\6\3\6\5\6\u0105\n\6\3\6\3\6\5\6\u0109\n\6\3\6\5\6\u010c\n\6\3\7\3\7\3"+
		"\7\6\7\u0111\n\7\r\7\16\7\u0112\3\7\3\7\6\7\u0117\n\7\r\7\16\7\u0118\3"+
		"\7\5\7\u011c\n\7\3\b\3\b\3\b\3\b\7\b\u0122\n\b\f\b\16\b\u0125\13\b\3\b"+
		"\3\b\3\t\3\t\3\t\3\t\5\t\u012d\n\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3"+
		"\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3"+
		"\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3"+
		"\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3"+
		"\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3"+
		"\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3"+
		"\31\3\31\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3"+
		"\34\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3"+
		"\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 "+
		"\3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3"+
		"\"\3\"\3\"\3#\3#\3#\3#\3#\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3%\3&\3&\3&\3"+
		"&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3"+
		")\3)\3)\3)\3)\3*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3+\3+\3+\3,\3,\3,\3,\3"+
		"-\3-\3-\3.\3.\3.\3/\3/\3/\3\60\3\60\3\60\3\61\3\61\3\61\3\62\3\62\3\62"+
		"\3\63\3\63\3\63\3\64\3\64\3\64\3\65\3\65\3\65\3\66\3\66\3\66\3\67\3\67"+
		"\3\67\38\38\38\39\39\39\3:\3:\3:\3;\3;\3;\3<\3<\3<\3=\3=\3=\3>\3>\3>\3"+
		">\3?\3?\3?\3?\3@\3@\3@\3A\3A\3A\3B\3B\3B\3C\3C\3D\3D\3E\3E\3F\3F\3G\3"+
		"G\3H\3H\3I\3I\3J\3J\3K\3K\3L\3L\3M\3M\3N\3N\3O\3O\3P\3P\3Q\3Q\3R\3R\3"+
		"S\3S\3T\3T\3U\3U\3V\3V\3W\3W\3X\3X\3Y\3Y\3Z\3Z\3[\3[\3[\7[\u0284\n[\f"+
		"[\16[\u0287\13[\3\\\3\\\3]\3]\3^\3^\3_\3_\3`\3`\3a\6a\u0294\na\ra\16a"+
		"\u0295\3\u00d4\2b\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65"+
		"\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64"+
		"g\65i\66k\67m8o9q:s;u<w=y>{?}@\177A\u0081B\u0083C\u0085D\u0087E\u0089"+
		"F\u008bG\u008dH\u008fI\u0091J\u0093K\u0095L\u0097M\u0099N\u009bO\u009d"+
		"P\u009fQ\u00a1R\u00a3S\u00a5T\u00a7U\u00a9V\u00abW\u00adX\u00afY\u00b1"+
		"Z\u00b3[\u00b5\\\u00b7]\u00b9\2\u00bb\2\u00bd\2\u00bf\2\u00c1\2\3\2\16"+
		"\4\2\f\f\17\17\5\2\13\f\17\17\"\"\4\2GGgg\4\2--//\6\2HHNNhhnn\4\2ZZzz"+
		"\6\2\f\f\17\17$$^^\6\2\f\f\17\17))^^\5\2C\\aac|\3\2\62;\5\2\62;CHch\3"+
		"\2\629\2\u02a8\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3"+
		"\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2"+
		"\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3"+
		"\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2"+
		"\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\2"+
		"9\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3"+
		"\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2"+
		"\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2"+
		"_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3"+
		"\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2"+
		"\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083"+
		"\3\2\2\2\2\u0085\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2"+
		"\2\2\u008d\3\2\2\2\2\u008f\3\2\2\2\2\u0091\3\2\2\2\2\u0093\3\2\2\2\2\u0095"+
		"\3\2\2\2\2\u0097\3\2\2\2\2\u0099\3\2\2\2\2\u009b\3\2\2\2\2\u009d\3\2\2"+
		"\2\2\u009f\3\2\2\2\2\u00a1\3\2\2\2\2\u00a3\3\2\2\2\2\u00a5\3\2\2\2\2\u00a7"+
		"\3\2\2\2\2\u00a9\3\2\2\2\2\u00ab\3\2\2\2\2\u00ad\3\2\2\2\2\u00af\3\2\2"+
		"\2\2\u00b1\3\2\2\2\2\u00b3\3\2\2\2\2\u00b5\3\2\2\2\2\u00b7\3\2\2\2\3\u00c3"+
		"\3\2\2\2\5\u00ce\3\2\2\2\7\u00dc\3\2\2\2\t\u00e6\3\2\2\2\13\u0108\3\2"+
		"\2\2\r\u011b\3\2\2\2\17\u011d\3\2\2\2\21\u0128\3\2\2\2\23\u0130\3\2\2"+
		"\2\25\u0135\3\2\2\2\27\u013a\3\2\2\2\31\u013f\3\2\2\2\33\u0145\3\2\2\2"+
		"\35\u0149\3\2\2\2\37\u014e\3\2\2\2!\u0154\3\2\2\2#\u015b\3\2\2\2%\u0161"+
		"\3\2\2\2\'\u0168\3\2\2\2)\u0171\3\2\2\2+\u0178\3\2\2\2-\u0180\3\2\2\2"+
		"/\u0183\3\2\2\2\61\u0188\3\2\2\2\63\u018e\3\2\2\2\65\u0192\3\2\2\2\67"+
		"\u0198\3\2\2\29\u01a1\3\2\2\2;\u01a8\3\2\2\2=\u01ae\3\2\2\2?\u01b5\3\2"+
		"\2\2A\u01bc\3\2\2\2C\u01c4\3\2\2\2E\u01ce\3\2\2\2G\u01d3\3\2\2\2I\u01d7"+
		"\3\2\2\2K\u01de\3\2\2\2M\u01e5\3\2\2\2O\u01ef\3\2\2\2Q\u01f5\3\2\2\2S"+
		"\u01fa\3\2\2\2U\u0200\3\2\2\2W\u0208\3\2\2\2Y\u020c\3\2\2\2[\u020f\3\2"+
		"\2\2]\u0212\3\2\2\2_\u0215\3\2\2\2a\u0218\3\2\2\2c\u021b\3\2\2\2e\u021e"+
		"\3\2\2\2g\u0221\3\2\2\2i\u0224\3\2\2\2k\u0227\3\2\2\2m\u022a\3\2\2\2o"+
		"\u022d\3\2\2\2q\u0230\3\2\2\2s\u0233\3\2\2\2u\u0236\3\2\2\2w\u0239\3\2"+
		"\2\2y\u023c\3\2\2\2{\u023f\3\2\2\2}\u0243\3\2\2\2\177\u0247\3\2\2\2\u0081"+
		"\u024a\3\2\2\2\u0083\u024d\3\2\2\2\u0085\u0250\3\2\2\2\u0087\u0252\3\2"+
		"\2\2\u0089\u0254\3\2\2\2\u008b\u0256\3\2\2\2\u008d\u0258\3\2\2\2\u008f"+
		"\u025a\3\2\2\2\u0091\u025c\3\2\2\2\u0093\u025e\3\2\2\2\u0095\u0260\3\2"+
		"\2\2\u0097\u0262\3\2\2\2\u0099\u0264\3\2\2\2\u009b\u0266\3\2\2\2\u009d"+
		"\u0268\3\2\2\2\u009f\u026a\3\2\2\2\u00a1\u026c\3\2\2\2\u00a3\u026e\3\2"+
		"\2\2\u00a5\u0270\3\2\2\2\u00a7\u0272\3\2\2\2\u00a9\u0274\3\2\2\2\u00ab"+
		"\u0276\3\2\2\2\u00ad\u0278\3\2\2\2\u00af\u027a\3\2\2\2\u00b1\u027c\3\2"+
		"\2\2\u00b3\u027e\3\2\2\2\u00b5\u0280\3\2\2\2\u00b7\u0288\3\2\2\2\u00b9"+
		"\u028a\3\2\2\2\u00bb\u028c\3\2\2\2\u00bd\u028e\3\2\2\2\u00bf\u0290\3\2"+
		"\2\2\u00c1\u0293\3\2\2\2\u00c3\u00c4\7\61\2\2\u00c4\u00c5\7\61\2\2\u00c5"+
		"\u00c9\3\2\2\2\u00c6\u00c8\n\2\2\2\u00c7\u00c6\3\2\2\2\u00c8\u00cb\3\2"+
		"\2\2\u00c9\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00cc\3\2\2\2\u00cb"+
		"\u00c9\3\2\2\2\u00cc\u00cd\b\2\2\2\u00cd\4\3\2\2\2\u00ce\u00cf\7\61\2"+
		"\2\u00cf\u00d0\7,\2\2\u00d0\u00d4\3\2\2\2\u00d1\u00d3\13\2\2\2\u00d2\u00d1"+
		"\3\2\2\2\u00d3\u00d6\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d5"+
		"\u00d7\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d7\u00d8\7,\2\2\u00d8\u00d9\7\61"+
		"\2\2\u00d9\u00da\3\2\2\2\u00da\u00db\b\3\2\2\u00db\6\3\2\2\2\u00dc\u00e0"+
		"\7%\2\2\u00dd\u00df\n\2\2\2\u00de\u00dd\3\2\2\2\u00df\u00e2\3\2\2\2\u00e0"+
		"\u00de\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00e3\3\2\2\2\u00e2\u00e0\3\2"+
		"\2\2\u00e3\u00e4\b\4\3\2\u00e4\b\3\2\2\2\u00e5\u00e7\t\3\2\2\u00e6\u00e5"+
		"\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9"+
		"\u00ea\3\2\2\2\u00ea\u00eb\b\5\4\2\u00eb\n\3\2\2\2\u00ec\u00ed\5\u00c1"+
		"a\2\u00ed\u00ef\7\60\2\2\u00ee\u00f0\5\u00c1a\2\u00ef\u00ee\3\2\2\2\u00ef"+
		"\u00f0\3\2\2\2\u00f0\u00f6\3\2\2\2\u00f1\u00f3\t\4\2\2\u00f2\u00f4\t\5"+
		"\2\2\u00f3\u00f2\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5"+
		"\u00f7\5\u00c1a\2\u00f6\u00f1\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7\u0109"+
		"\3\2\2\2\u00f8\u00f9\7\60\2\2\u00f9\u00ff\5\u00c1a\2\u00fa\u00fc\t\4\2"+
		"\2\u00fb\u00fd\t\5\2\2\u00fc\u00fb\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fd\u00fe"+
		"\3\2\2\2\u00fe\u0100\5\u00c1a\2\u00ff\u00fa\3\2\2\2\u00ff\u0100\3\2\2"+
		"\2\u0100\u0109\3\2\2\2\u0101\u0102\5\u00c1a\2\u0102\u0104\t\4\2\2\u0103"+
		"\u0105\t\5\2\2\u0104\u0103\3\2\2\2\u0104\u0105\3\2\2\2\u0105\u0106\3\2"+
		"\2\2\u0106\u0107\5\u00c1a\2\u0107\u0109\3\2\2\2\u0108\u00ec\3\2\2\2\u0108"+
		"\u00f8\3\2\2\2\u0108\u0101\3\2\2\2\u0109\u010b\3\2\2\2\u010a\u010c\t\6"+
		"\2\2\u010b\u010a\3\2\2\2\u010b\u010c\3\2\2\2\u010c\f\3\2\2\2\u010d\u010e"+
		"\7\62\2\2\u010e\u0110\t\7\2\2\u010f\u0111\5\u00bd_\2\u0110\u010f\3\2\2"+
		"\2\u0111\u0112\3\2\2\2\u0112\u0110\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u011c"+
		"\3\2\2\2\u0114\u0116\7\62\2\2\u0115\u0117\5\u00bf`\2\u0116\u0115\3\2\2"+
		"\2\u0117\u0118\3\2\2\2\u0118\u0116\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u011c"+
		"\3\2\2\2\u011a\u011c\5\u00c1a\2\u011b\u010d\3\2\2\2\u011b\u0114\3\2\2"+
		"\2\u011b\u011a\3\2\2\2\u011c\16\3\2\2\2\u011d\u0123\7$\2\2\u011e\u0122"+
		"\n\b\2\2\u011f\u0120\7^\2\2\u0120\u0122\13\2\2\2\u0121\u011e\3\2\2\2\u0121"+
		"\u011f\3\2\2\2\u0122\u0125\3\2\2\2\u0123\u0121\3\2\2\2\u0123\u0124\3\2"+
		"\2\2\u0124\u0126\3\2\2\2\u0125\u0123\3\2\2\2\u0126\u0127\7$\2\2\u0127"+
		"\20\3\2\2\2\u0128\u012c\7)\2\2\u0129\u012d\n\t\2\2\u012a\u012b\7^\2\2"+
		"\u012b\u012d\13\2\2\2\u012c\u0129\3\2\2\2\u012c\u012a\3\2\2\2\u012d\u012e"+
		"\3\2\2\2\u012e\u012f\7)\2\2\u012f\22\3\2\2\2\u0130\u0131\7x\2\2\u0131"+
		"\u0132\7q\2\2\u0132\u0133\7k\2\2\u0133\u0134\7f\2\2\u0134\24\3\2\2\2\u0135"+
		"\u0136\7d\2\2\u0136\u0137\7q\2\2\u0137\u0138\7q\2\2\u0138\u0139\7n\2\2"+
		"\u0139\26\3\2\2\2\u013a\u013b\7e\2\2\u013b\u013c\7j\2\2\u013c\u013d\7"+
		"c\2\2\u013d\u013e\7t\2\2\u013e\30\3\2\2\2\u013f\u0140\7u\2\2\u0140\u0141"+
		"\7j\2\2\u0141\u0142\7q\2\2\u0142\u0143\7t\2\2\u0143\u0144\7v\2\2\u0144"+
		"\32\3\2\2\2\u0145\u0146\7k\2\2\u0146\u0147\7p\2\2\u0147\u0148\7v\2\2\u0148"+
		"\34\3\2\2\2\u0149\u014a\7n\2\2\u014a\u014b\7q\2\2\u014b\u014c\7p\2\2\u014c"+
		"\u014d\7i\2\2\u014d\36\3\2\2\2\u014e\u014f\7h\2\2\u014f\u0150\7n\2\2\u0150"+
		"\u0151\7q\2\2\u0151\u0152\7c\2\2\u0152\u0153\7v\2\2\u0153 \3\2\2\2\u0154"+
		"\u0155\7f\2\2\u0155\u0156\7q\2\2\u0156\u0157\7w\2\2\u0157\u0158\7d\2\2"+
		"\u0158\u0159\7n\2\2\u0159\u015a\7g\2\2\u015a\"\3\2\2\2\u015b\u015c\7e"+
		"\2\2\u015c\u015d\7q\2\2\u015d\u015e\7p\2\2\u015e\u015f\7u\2\2\u015f\u0160"+
		"\7v\2\2\u0160$\3\2\2\2\u0161\u0162\7u\2\2\u0162\u0163\7v\2\2\u0163\u0164"+
		"\7c\2\2\u0164\u0165\7v\2\2\u0165\u0166\7k\2\2\u0166\u0167\7e\2\2\u0167"+
		"&\3\2\2\2\u0168\u0169\7w\2\2\u0169\u016a\7p\2\2\u016a\u016b\7u\2\2\u016b"+
		"\u016c\7k\2\2\u016c\u016d\7i\2\2\u016d\u016e\7p\2\2\u016e\u016f\7g\2\2"+
		"\u016f\u0170\7f\2\2\u0170(\3\2\2\2\u0171\u0172\7u\2\2\u0172\u0173\7k\2"+
		"\2\u0173\u0174\7i\2\2\u0174\u0175\7p\2\2\u0175\u0176\7g\2\2\u0176\u0177"+
		"\7f\2\2\u0177*\3\2\2\2\u0178\u0179\7x\2\2\u0179\u017a\7k\2\2\u017a\u017b"+
		"\7t\2\2\u017b\u017c\7v\2\2\u017c\u017d\7w\2\2\u017d\u017e\7c\2\2\u017e"+
		"\u017f\7n\2\2\u017f,\3\2\2\2\u0180\u0181\7k\2\2\u0181\u0182\7h\2\2\u0182"+
		".\3\2\2\2\u0183\u0184\7g\2\2\u0184\u0185\7n\2\2\u0185\u0186\7u\2\2\u0186"+
		"\u0187\7g\2\2\u0187\60\3\2\2\2\u0188\u0189\7y\2\2\u0189\u018a\7j\2\2\u018a"+
		"\u018b\7k\2\2\u018b\u018c\7n\2\2\u018c\u018d\7g\2\2\u018d\62\3\2\2\2\u018e"+
		"\u018f\7h\2\2\u018f\u0190\7q\2\2\u0190\u0191\7t\2\2\u0191\64\3\2\2\2\u0192"+
		"\u0193\7d\2\2\u0193\u0194\7t\2\2\u0194\u0195\7g\2\2\u0195\u0196\7c\2\2"+
		"\u0196\u0197\7m\2\2\u0197\66\3\2\2\2\u0198\u0199\7e\2\2\u0199\u019a\7"+
		"q\2\2\u019a\u019b\7p\2\2\u019b\u019c\7v\2\2\u019c\u019d\7k\2\2\u019d\u019e"+
		"\7p\2\2\u019e\u019f\7w\2\2\u019f\u01a0\7g\2\2\u01a08\3\2\2\2\u01a1\u01a2"+
		"\7t\2\2\u01a2\u01a3\7g\2\2\u01a3\u01a4\7v\2\2\u01a4\u01a5\7w\2\2\u01a5"+
		"\u01a6\7t\2\2\u01a6\u01a7\7p\2\2\u01a7:\3\2\2\2\u01a8\u01a9\7e\2\2\u01a9"+
		"\u01aa\7n\2\2\u01aa\u01ab\7c\2\2\u01ab\u01ac\7u\2\2\u01ac\u01ad\7u\2\2"+
		"\u01ad<\3\2\2\2\u01ae\u01af\7u\2\2\u01af\u01b0\7v\2\2\u01b0\u01b1\7t\2"+
		"\2\u01b1\u01b2\7w\2\2\u01b2\u01b3\7e\2\2\u01b3\u01b4\7v\2\2\u01b4>\3\2"+
		"\2\2\u01b5\u01b6\7r\2\2\u01b6\u01b7\7w\2\2\u01b7\u01b8\7d\2\2\u01b8\u01b9"+
		"\7n\2\2\u01b9\u01ba\7k\2\2\u01ba\u01bb\7e\2\2\u01bb@\3\2\2\2\u01bc\u01bd"+
		"\7r\2\2\u01bd\u01be\7t\2\2\u01be\u01bf\7k\2\2\u01bf\u01c0\7x\2\2\u01c0"+
		"\u01c1\7c\2\2\u01c1\u01c2\7v\2\2\u01c2\u01c3\7g\2\2\u01c3B\3\2\2\2\u01c4"+
		"\u01c5\7r\2\2\u01c5\u01c6\7t\2\2\u01c6\u01c7\7q\2\2\u01c7\u01c8\7v\2\2"+
		"\u01c8\u01c9\7g\2\2\u01c9\u01ca\7e\2\2\u01ca\u01cb\7v\2\2\u01cb\u01cc"+
		"\7g\2\2\u01cc\u01cd\7f\2\2\u01cdD\3\2\2\2\u01ce\u01cf\7v\2\2\u01cf\u01d0"+
		"\7j\2\2\u01d0\u01d1\7k\2\2\u01d1\u01d2\7u\2\2\u01d2F\3\2\2\2\u01d3\u01d4"+
		"\7p\2\2\u01d4\u01d5\7g\2\2\u01d5\u01d6\7y\2\2\u01d6H\3\2\2\2\u01d7\u01d8"+
		"\7f\2\2\u01d8\u01d9\7g\2\2\u01d9\u01da\7n\2\2\u01da\u01db\7g\2\2\u01db"+
		"\u01dc\7v\2\2\u01dc\u01dd\7g\2\2\u01ddJ\3\2\2\2\u01de\u01df\7u\2\2\u01df"+
		"\u01e0\7k\2\2\u01e0\u01e1\7|\2\2\u01e1\u01e2\7g\2\2\u01e2\u01e3\7q\2\2"+
		"\u01e3\u01e4\7h\2\2\u01e4L\3\2\2\2\u01e5\u01e6\7p\2\2\u01e6\u01e7\7c\2"+
		"\2\u01e7\u01e8\7o\2\2\u01e8\u01e9\7g\2\2\u01e9\u01ea\7u\2\2\u01ea\u01eb"+
		"\7r\2\2\u01eb\u01ec\7c\2\2\u01ec\u01ed\7e\2\2\u01ed\u01ee\7g\2\2\u01ee"+
		"N\3\2\2\2\u01ef\u01f0\7w\2\2\u01f0\u01f1\7u\2\2\u01f1\u01f2\7k\2\2\u01f2"+
		"\u01f3\7p\2\2\u01f3\u01f4\7i\2\2\u01f4P\3\2\2\2\u01f5\u01f6\7v\2\2\u01f6"+
		"\u01f7\7t\2\2\u01f7\u01f8\7w\2\2\u01f8\u01f9\7g\2\2\u01f9R\3\2\2\2\u01fa"+
		"\u01fb\7h\2\2\u01fb\u01fc\7c\2\2\u01fc\u01fd\7n\2\2\u01fd\u01fe\7u\2\2"+
		"\u01fe\u01ff\7g\2\2\u01ffT\3\2\2\2\u0200\u0201\7p\2\2\u0201\u0202\7w\2"+
		"\2\u0202\u0203\7n\2\2\u0203\u0204\7n\2\2\u0204\u0205\7r\2\2\u0205\u0206"+
		"\7v\2\2\u0206\u0207\7t\2\2\u0207V\3\2\2\2\u0208\u0209\7\60\2\2\u0209\u020a"+
		"\7\60\2\2\u020a\u020b\7\60\2\2\u020bX\3\2\2\2\u020c\u020d\7<\2\2\u020d"+
		"\u020e\7<\2\2\u020eZ\3\2\2\2\u020f\u0210\7/\2\2\u0210\u0211\7@\2\2\u0211"+
		"\\\3\2\2\2\u0212\u0213\7-\2\2\u0213\u0214\7-\2\2\u0214^\3\2\2\2\u0215"+
		"\u0216\7/\2\2\u0216\u0217\7/\2\2\u0217`\3\2\2\2\u0218\u0219\7>\2\2\u0219"+
		"\u021a\7>\2\2\u021ab\3\2\2\2\u021b\u021c\7@\2\2\u021c\u021d\7@\2\2\u021d"+
		"d\3\2\2\2\u021e\u021f\7>\2\2\u021f\u0220\7?\2\2\u0220f\3\2\2\2\u0221\u0222"+
		"\7@\2\2\u0222\u0223\7?\2\2\u0223h\3\2\2\2\u0224\u0225\7?\2\2\u0225\u0226"+
		"\7?\2\2\u0226j\3\2\2\2\u0227\u0228\7#\2\2\u0228\u0229\7?\2\2\u0229l\3"+
		"\2\2\2\u022a\u022b\7(\2\2\u022b\u022c\7(\2\2\u022cn\3\2\2\2\u022d\u022e"+
		"\7~\2\2\u022e\u022f\7~\2\2\u022fp\3\2\2\2\u0230\u0231\7,\2\2\u0231\u0232"+
		"\7?\2\2\u0232r\3\2\2\2\u0233\u0234\7\61\2\2\u0234\u0235\7?\2\2\u0235t"+
		"\3\2\2\2\u0236\u0237\7\'\2\2\u0237\u0238\7?\2\2\u0238v\3\2\2\2\u0239\u023a"+
		"\7-\2\2\u023a\u023b\7?\2\2\u023bx\3\2\2\2\u023c\u023d\7/\2\2\u023d\u023e"+
		"\7?\2\2\u023ez\3\2\2\2\u023f\u0240\7>\2\2\u0240\u0241\7>\2\2\u0241\u0242"+
		"\7?\2\2\u0242|\3\2\2\2\u0243\u0244\7@\2\2\u0244\u0245\7@\2\2\u0245\u0246"+
		"\7?\2\2\u0246~\3\2\2\2\u0247\u0248\7(\2\2\u0248\u0249\7?\2\2\u0249\u0080"+
		"\3\2\2\2\u024a\u024b\7`\2\2\u024b\u024c\7?\2\2\u024c\u0082\3\2\2\2\u024d"+
		"\u024e\7~\2\2\u024e\u024f\7?\2\2\u024f\u0084\3\2\2\2\u0250\u0251\7*\2"+
		"\2\u0251\u0086\3\2\2\2\u0252\u0253\7+\2\2\u0253\u0088\3\2\2\2\u0254\u0255"+
		"\7}\2\2\u0255\u008a\3\2\2\2\u0256\u0257\7\177\2\2\u0257\u008c\3\2\2\2"+
		"\u0258\u0259\7]\2\2\u0259\u008e\3\2\2\2\u025a\u025b\7_\2\2\u025b\u0090"+
		"\3\2\2\2\u025c\u025d\7=\2\2\u025d\u0092\3\2\2\2\u025e\u025f\7.\2\2\u025f"+
		"\u0094\3\2\2\2\u0260\u0261\7\60\2\2\u0261\u0096\3\2\2\2\u0262\u0263\7"+
		"A\2\2\u0263\u0098\3\2\2\2\u0264\u0265\7<\2\2\u0265\u009a\3\2\2\2\u0266"+
		"\u0267\7,\2\2\u0267\u009c\3\2\2\2\u0268\u0269\7-\2\2\u0269\u009e\3\2\2"+
		"\2\u026a\u026b\7/\2\2\u026b\u00a0\3\2\2\2\u026c\u026d\7\61\2\2\u026d\u00a2"+
		"\3\2\2\2\u026e\u026f\7\'\2\2\u026f\u00a4\3\2\2\2\u0270\u0271\7\u0080\2"+
		"\2\u0271\u00a6\3\2\2\2\u0272\u0273\7#\2\2\u0273\u00a8\3\2\2\2\u0274\u0275"+
		"\7>\2\2\u0275\u00aa\3\2\2\2\u0276\u0277\7@\2\2\u0277\u00ac\3\2\2\2\u0278"+
		"\u0279\7(\2\2\u0279\u00ae\3\2\2\2\u027a\u027b\7~\2\2\u027b\u00b0\3\2\2"+
		"\2\u027c\u027d\7`\2\2\u027d\u00b2\3\2\2\2\u027e\u027f\7?\2\2\u027f\u00b4"+
		"\3\2\2\2\u0280\u0285\5\u00b9]\2\u0281\u0284\5\u00b9]\2\u0282\u0284\5\u00bb"+
		"^\2\u0283\u0281\3\2\2\2\u0283\u0282\3\2\2\2\u0284\u0287\3\2\2\2\u0285"+
		"\u0283\3\2\2\2\u0285\u0286\3\2\2\2\u0286\u00b6\3\2\2\2\u0287\u0285\3\2"+
		"\2\2\u0288\u0289\13\2\2\2\u0289\u00b8\3\2\2\2\u028a\u028b\t\n\2\2\u028b"+
		"\u00ba\3\2\2\2\u028c\u028d\t\13\2\2\u028d\u00bc\3\2\2\2\u028e\u028f\t"+
		"\f\2\2\u028f\u00be\3\2\2\2\u0290\u0291\t\r\2\2\u0291\u00c0\3\2\2\2\u0292"+
		"\u0294\5\u00bb^\2\u0293\u0292\3\2\2\2\u0294\u0295\3\2\2\2\u0295\u0293"+
		"\3\2\2\2\u0295\u0296\3\2\2\2\u0296\u00c2\3\2\2\2\30\2\u00c9\u00d4\u00e0"+
		"\u00e8\u00ef\u00f3\u00f6\u00fc\u00ff\u0104\u0108\u010b\u0112\u0118\u011b"+
		"\u0121\u0123\u012c\u0283\u0285\u0295\5\2\3\2\2\4\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}