param(
    [switch]$RegenerateLexer,
    [switch]$RegenerateParser
)
# Compila lexer + parser (ANTLR) y todas las clases Java del proyecto.
$ErrorActionPreference = "Stop"
$root = Split-Path -Parent $MyInvocation.MyCommand.Path
Set-Location $root

$tools = Join-Path $root "tools"
$antlrJar = Join-Path $tools "antlr-4.13.2-complete.jar"
$runtimeJar = Join-Path $tools "antlr4-runtime-4.13.2.jar"
$lexerDir = Join-Path $root "src\main\java\com\cppcompiler\lexer"
$parserDir = Join-Path $root "src\main\java\com\cppcompiler\parser"
$srcRoot = Join-Path $root "src\main\java"
$outDir = Join-Path $root "target\classes"

if (-not (Test-Path $antlrJar) -or -not (Test-Path $runtimeJar)) {
    Write-Host "Descargando JARs en tools\..."
    New-Item -ItemType Directory -Force -Path $tools | Out-Null
    Invoke-WebRequest -Uri "https://www.antlr.org/download/antlr-4.13.2-complete.jar" -OutFile $antlrJar -UseBasicParsing
    Invoke-WebRequest -Uri "https://repo1.maven.org/maven2/org/antlr/antlr4-runtime/4.13.2/antlr4-runtime-4.13.2.jar" -OutFile $runtimeJar -UseBasicParsing
}

$lexerG4 = Join-Path $root "CPPSubsetLexer.g4"
$parserG4 = Join-Path $root "CPPSubsetParser.g4"

if ($RegenerateLexer -or -not (Test-Path (Join-Path $lexerDir "CPPSubsetLexer.java"))) {
    Write-Host "Generando lexer..."
    & java -jar $antlrJar -Dlanguage=Java -package com.cppcompiler.lexer -no-listener -no-visitor -o $lexerDir $lexerG4
    if (-not (Test-Path (Join-Path $lexerDir "CPPSubsetLexer.java"))) {
        Write-Error "No se genero CPPSubsetLexer.java"
    }
}

if ($RegenerateParser -or -not (Test-Path (Join-Path $parserDir "CPPSubsetParser.java"))) {
    Write-Host "Generando parser..."
    New-Item -ItemType Directory -Force -Path $parserDir | Out-Null
    & java -jar $antlrJar -lib $lexerDir -Dlanguage=Java -package com.cppcompiler.parser -visitor -no-listener -o $parserDir $parserG4
    if (-not (Test-Path (Join-Path $parserDir "CPPSubsetParser.java"))) {
        Write-Error "No se genero CPPSubsetParser.java"
    }
}

New-Item -ItemType Directory -Force -Path $outDir | Out-Null
$sources = @(Get-ChildItem -Path $srcRoot -Recurse -Filter "*.java" | ForEach-Object { $_.FullName })
& javac -encoding UTF-8 -cp $runtimeJar -d $outDir @sources
if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }
Write-Host "OK: clases en $outDir"
Write-Host "Lexer:  java -cp `"$outDir;$runtimeJar`" com.cppcompiler.lexer.RunCppLexer ejemplo_entrada.cpp"
Write-Host "Compilador: java -cp `"$outDir;$runtimeJar`" com.cppcompiler.RunCompiler profesora.cpp"
