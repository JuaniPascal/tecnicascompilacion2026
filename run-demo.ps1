<#
run-demo.ps1
Genera/compila y (opcional) ejecuta una prueba rápida para una gramática ANTLR.

Usage:
  .\run-demo.ps1                 # usa Expr.g4 en el directorio actual
  .\run-demo.ps1 -Grammar My.g4 # usa My.g4
  .\run-demo.ps1 -RunExample    # además ejecuta una prueba corta (3+4)

Requisitos: java y javac en PATH. ANTLR jar en %ANTLR_HOME% (o C:\antlr).
#>

[CmdletBinding()]
Param(
    [string]$Grammar = 'Expr.g4',
    [switch]$RunExample,
    [switch]$RunGui
)

Set-StrictMode -Version Latest

Write-Output "run-demo: usando carpeta $(Get-Location)"

$grammarPath = Join-Path (Get-Location) $Grammar
if (-not (Test-Path $grammarPath)) {
    Write-Error "Gramática no encontrada: $grammarPath"
    exit 1
}

# Buscar jar de ANTLR (primero en el directorio actual, luego en ANTLR_HOME)
$jar = Get-ChildItem -Path . -Filter 'antlr-*-complete.jar' -File -ErrorAction SilentlyContinue | Select-Object -First 1
if (-not $jar) {
    $antlrHome = $env:ANTLR_HOME
    if ([string]::IsNullOrEmpty($antlrHome)) { $antlrHome = 'C:\antlr' }
    $jar = Get-ChildItem -Path $antlrHome -Filter 'antlr-*-complete.jar' -File -ErrorAction SilentlyContinue | Select-Object -First 1
}
if (-not $jar) {
    Write-Error "No se encontró ANTLR jar en el directorio actual ni en $antlrHome. Coloca antlr-4.X-complete.jar en el directorio actual o define ANTLR_HOME."
    exit 1
}
$jarPath = $jar.FullName
Write-Output "Encontrado ANTLR jar: $jarPath"

# Invocar ANTLR (preferimos comando antlr4 si está disponible)
if (Get-Command antlr4 -ErrorAction SilentlyContinue) {
    Write-Output "Usando comando 'antlr4' para generar..."
    & antlr4 $Grammar
} else {
    Write-Output "Invocando jar con java -jar para generar..."
    & java -jar $jarPath $Grammar
}

# Compilar fuentes Java generadas (si existen)
$javaFiles = Get-ChildItem -Path . -Filter '*.java' -File -ErrorAction SilentlyContinue
if ($javaFiles) {
    if (-not (Get-Command javac -ErrorAction SilentlyContinue)) {
        Write-Error "javac no encontrado en PATH. Instala JDK para compilar."; exit 1
    }
    Write-Output "Compilando archivos Java con el classpath que incluye el JAR de ANTLR..."
    & javac -cp ".;$jarPath" *.java
    Write-Output "Compilación finalizada. Archivos .class creados:"
    Get-ChildItem -Filter '*.class' -File | Select-Object Name, Length
} else {
    Write-Output "No se generaron archivos .java (revisa la generación)."
}

if ($RunExample) {
    Write-Output "Ejecutando prueba rápida: parsea \"3+4\" y muestra el árbol en texto..."
    "3+4" | java -cp ".;$jarPath" org.antlr.v4.gui.TestRig Expr prog -tree
}

Write-Output "Ejecutando TestRig GUI: abre una ventana de árbol de análisis..."
Start-Process -FilePath java -ArgumentList "-cp", ".;$jarPath", "org.antlr.v4.gui.TestRig", "Expr", "prog", "-gui", "input.txt" -Wait

Write-Output "run-demo: terminado. Puedes agregar tus gramáticas a esta carpeta y volver a ejecutar el script."
