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
    | returnStmt
    | block
    ;

assignment : lvalue ASSIGN expr SEMI ;

lvalue : IDENTIFIER ( LBRACK expr RBRACK )? ;

ifStmt : KW_IF LPAREN expr RPAREN block ;

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
