/**
 * Lexer para un subconjunto educativo de C++ (Fase 1 — solo análisis léxico).
 *
 * Subconjunto cubierto (tokens):
 * - Tipos y calificadores: void, bool, char, short, int, long, float, double,
 *   const, static, unsigned, signed, virtual
 * - Control: if, else, while, for, break, continue, return
 * - Estructuras / OOP: class, struct, public, private, protected, this,
 *   new, delete, sizeof
 * - Espacio de nombres: namespace, using
 * - Literales booleanos y puntero nulo: true, false, nullptr
 * - Literales numéricos (decimal, hex 0x, octal 0), flotantes, cadenas y caracteres
 * - Operadores y delimitadores habituales (incl. ::, ->, <<, >>, compuestos +=, etc.)
 * - Directivas de preprocesador (#...) y comentarios: canal oculto (no en lista principal)
 * - Espacios: omitidos
 *
 * Orden de las reglas: comentarios y literales más específicos antes que reglas genéricas;
 * palabras clave antes que IDENTIFIER.
 */
lexer grammar CPPSubsetLexer;

channels { PREPROC_CHANNEL }

// --- Comentarios y preprocesador (canal dedicado / oculto en la salida principal) ---
LINE_COMMENT   : '//' ~[\r\n]*                -> channel(HIDDEN);
BLOCK_COMMENT  : '/*' .*? '*/'               -> channel(HIDDEN);
PREPROCESSOR   : '#' ~[\r\n]*                -> channel(PREPROC_CHANNEL);

// --- Espacio en blanco ---
WS             : [ \t\r\n]+                  -> skip;

// --- Literales numéricos (orden: flotantes / enteros con sufijos ambiguos antes que enteros simples) ---
FLOAT_LITERAL
    : ( DIGITS '.' DIGITS? ([eE] [+-]? DIGITS)?
      | '.' DIGITS ([eE] [+-]? DIGITS)?
      | DIGITS [eE] [+-]? DIGITS
      ) [fFlL]?
    ;

INT_LITERAL
    : '0' [xX] HEX_DIGIT+
    | '0' OCT_DIGIT+
    | DIGITS
    ;

// --- Cadenas y caracteres (con secuencias de escape simples) --- Esto cambiar de orden con lo de abajo
STRING_LITERAL : '"' ( ~["\\\r\n] | '\\' . )* '"';
CHAR_LITERAL   : '\'' ( ~['\\\r\n] | '\\' . ) '\'';

// --- Palabras clave (antes de IDENTIFIER) ---
KW_VOID       : 'void';
KW_BOOL       : 'bool';
KW_CHAR       : 'char';
KW_SHORT      : 'short';
KW_INT        : 'int';
KW_LONG       : 'long';
KW_FLOAT      : 'float';
KW_DOUBLE     : 'double';
KW_CONST      : 'const';
KW_STATIC     : 'static';
KW_UNSIGNED   : 'unsigned';
KW_SIGNED     : 'signed';
KW_VIRTUAL    : 'virtual';

KW_IF         : 'if';
KW_ELSE       : 'else';
KW_WHILE      : 'while';
KW_FOR        : 'for';
KW_BREAK      : 'break';
KW_CONTINUE   : 'continue';
KW_RETURN     : 'return';

KW_CLASS      : 'class';
KW_STRUCT     : 'struct';
KW_PUBLIC     : 'public';
KW_PRIVATE    : 'private';
KW_PROTECTED  : 'protected';
KW_THIS       : 'this';
KW_NEW        : 'new';
KW_DELETE     : 'delete';
KW_SIZEOF     : 'sizeof';

KW_NAMESPACE  : 'namespace';
KW_USING      : 'using';

KW_TRUE       : 'true';
KW_FALSE      : 'false';
KW_NULLPTR    : 'nullptr';

// --- Operadores y puntuación de varios caracteres (antes de los de un carácter) ---
ELLIPSIS      : '...';
SCOPE         : '::';
ARROW         : '->';
INC           : '++';
DEC           : '--';
SHL           : '<<';
SHR           : '>>';
LE            : '<=';
GE            : '>=';
EQ            : '==';
NE            : '!=';
AND_AND       : '&&';
OR_OR         : '||';
MUL_ASSIGN    : '*=';
DIV_ASSIGN    : '/=';
MOD_ASSIGN    : '%=';
ADD_ASSIGN    : '+=';
SUB_ASSIGN    : '-=';
SHL_ASSIGN    : '<<=';
SHR_ASSIGN    : '>>=';
AND_ASSIGN    : '&=';
XOR_ASSIGN    : '^=';
OR_ASSIGN     : '|=';

// --- Un carácter ---
LPAREN        : '(';
RPAREN        : ')';
LBRACE        : '{';
RBRACE        : '}';
LBRACK        : '[';
RBRACK        : ']';
SEMI          : ';';
COMMA         : ',';
DOT           : '.';
QUEST         : '?';
COLON         : ':';
STAR          : '*';
PLUS          : '+';
MINUS         : '-';
DIV           : '/';
MOD           : '%';
TILDE         : '~';
NOT           : '!';
LT            : '<';
GT            : '>';
AMP           : '&';
BIT_OR        : '|';
BIT_XOR       : '^';
ASSIGN        : '=';

// --- Identificadores ---
IDENTIFIER    : LETTER (LETTER | DIGIT)*;

// --- Carácter no reconocido (mensaje explícito vía listener) ---
ERROR_CHAR    : . ;

fragment LETTER    : [a-zA-Z_];
fragment DIGIT     : [0-9];
fragment HEX_DIGIT : [0-9a-fA-F];
fragment OCT_DIGIT : [0-7];
fragment DIGITS    : DIGIT+;
