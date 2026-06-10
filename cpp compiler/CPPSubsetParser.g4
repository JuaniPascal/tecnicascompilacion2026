/**
 * Parser para un subconjunto mínimo de C++ (declaraciones, asignación, if, return, llamadas).
 * Tokens: tokenVocab = CPPSubsetLexer.
 */
parser grammar CPPSubsetParser;

options { tokenVocab = CPPSubsetLexer; }

program : ( varDecl | funcDecl )* EOF ;

varDecl : typeName IDENTIFIER arrayDim? SEMI ;

arrayDim : LBRACK INT_LITERAL RBRACK ;

funcDecl : typeName IDENTIFIER LPAREN paramList? RPAREN block ;

paramList : param ( COMMA param )* ;
param : typeName IDENTIFIER ;

block : LBRACE statement* RBRACE ;

statement
    : varDecl
    | assignment
    | ifStmt
    | whileStmt
    | forStmt
    | breakStmt
    | continueStmt
    | returnStmt
    | block
    ;

assignment : lvalue ASSIGN expr SEMI ;

lvalue : IDENTIFIER ( LBRACK expr RBRACK )? ;

ifStmt : KW_IF LPAREN expr RPAREN block ( KW_ELSE block )? ;

whileStmt : KW_WHILE LPAREN expr RPAREN block ;

forStmt
    : KW_FOR LPAREN forInit? SEMI expr? SEMI forUpdate? RPAREN block
    ;

// Init del for: declaración con inicialización OBLIGATORIA, o asignación a lvalue.
forInit
    : typeName IDENTIFIER ASSIGN expr     # ForInitDecl
    | lvalue ASSIGN expr                  # ForInitAssign
    ;

// Update del for: solo asignación a lvalue.
forUpdate
    : lvalue ASSIGN expr
    ;

breakStmt    : KW_BREAK SEMI ;
continueStmt : KW_CONTINUE SEMI ;

returnStmt : KW_RETURN expr? SEMI ;

typeName
    : KW_INT
    | KW_DOUBLE
    | KW_CHAR
    | KW_BOOL
    | KW_VOID
    ;

expr : orExpr ;

orExpr : andExpr ( OR_OR andExpr )* ;

andExpr : eqExpr ( AND_AND eqExpr )* ;

eqExpr : relExpr ( ( EQ | NE ) relExpr )* ;

relExpr : addExpr ( ( LT | GT | LE | GE ) addExpr )* ;

addExpr : mulExpr ( ( PLUS | MINUS ) mulExpr )* ;

mulExpr : unary ( ( STAR | DIV | MOD ) unary )* ;

unary : ( PLUS | MINUS | NOT )? postfix ;

postfix : atom postfixSuffix* ;

postfixSuffix
    : LBRACK expr RBRACK
    | LPAREN argList? RPAREN
    ;

atom
    : literal
    | IDENTIFIER
    | LPAREN expr RPAREN
    ;

argList : expr ( COMMA expr )* ;

literal
    : INT_LITERAL
    | FLOAT_LITERAL
    | CHAR_LITERAL
    | KW_TRUE
    | KW_FALSE
    ;
