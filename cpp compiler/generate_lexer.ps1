# Regenera CPPSubsetLexer.java desde CPPSubsetLexer.g4 (Java, paquete com.cppcompiler.lexer).
# Requiere Java en PATH y tools\antlr-4.13.2-complete.jar (o ejecutar compile_java.ps1 sin -RegenerateLexer para descargar JARs).
$ErrorActionPreference = "Stop"
$root = Split-Path -Parent $MyInvocation.MyCommand.Path
$jar = Join-Path $root "tools\antlr-4.13.2-complete.jar"
$grammar = Join-Path $root "CPPSubsetLexer.g4"
$pkgDir = Join-Path $root "src\main\java\com\cppcompiler\lexer"

if (-not (Test-Path $jar)) {
    Write-Error "No se encontro $jar. Ejecuta primero compile_java.ps1 para descargar dependencias."
}
& java -jar $jar -Dlanguage=Java -package com.cppcompiler.lexer -no-listener -no-visitor -o $pkgDir $grammar
Write-Host "Generado en $pkgDir"
