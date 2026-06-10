# run_all_tests.ps1
# Compila el proyecto y ejecuta todos los .cpp de la carpeta tests\, mostrando
# el resumen del compilador (errores/warnings/optimizaciones) por cada uno.
#
# Uso:
#   .\run_all_tests.ps1              # corre todos los tests
#   .\run_all_tests.ps1 -Only t05    # filtra por nombre (sustring)
#   .\run_all_tests.ps1 -Verbose     # imprime salida completa de cada test
#   .\run_all_tests.ps1 -NoCompile   # asume que target\classes ya está compilado

[CmdletBinding()]
param(
    [string]$Only = "",
    [switch]$NoCompile
)

$ErrorActionPreference = "Stop"
$root = Split-Path -Parent $MyInvocation.MyCommand.Path
Set-Location $root

# 1) Compilar el proyecto (a menos que se pida saltearlo).
if (-not $NoCompile) {
    Write-Host "==============================" -ForegroundColor Cyan
    Write-Host " Compilando el proyecto Java" -ForegroundColor Cyan
    Write-Host "==============================" -ForegroundColor Cyan
    & powershell -ExecutionPolicy Bypass -File (Join-Path $root "compile_java.ps1") | Out-Null
    if ($LASTEXITCODE -ne 0) {
        Write-Host "Error compilando el proyecto." -ForegroundColor Red
        exit 1
    }
}

# 2) Configurar classpath y forzar colores en la JVM hija.
$cp = "target\classes;tools\antlr-4.13.2-complete.jar"
$env:FORCE_COLOR = "true"

# 3) Recolectar tests.
$tests = Get-ChildItem -Path (Join-Path $root "tests") -Filter *.cpp | Sort-Object Name
if ($Only -ne "") {
    $tests = $tests | Where-Object { $_.Name -like "*$Only*" }
}
if ($tests.Count -eq 0) {
    Write-Host "No se encontraron tests" -ForegroundColor Yellow
    exit 0
}

# 4) Ejecutar cada test.
$totalOk = 0
$totalFail = 0

foreach ($t in $tests) {
    Write-Host ""
    Write-Host "================================================================" -ForegroundColor Cyan
    Write-Host " TEST: $($t.Name)" -ForegroundColor Cyan
    Write-Host "================================================================" -ForegroundColor Cyan

    $output = & java -cp "$cp" com.cppcompiler.RunCompiler $t.FullName --no-gui 2>&1
    $exit = $LASTEXITCODE

    if ($VerbosePreference -eq "Continue") {
        $output | ForEach-Object { Write-Host $_ }
    } else {
        # Modo compacto: muestra solo el resumen y las estadísticas de optimización.
        $resumen = $output | Where-Object {
            $_ -match "Resumen semantico" -or
            $_ -match "Compilación finalizada" -or
            $_ -match "Plegado de constantes" -or
            $_ -match "Propagación de constantes" -or
            $_ -match "Simplificaciones algebraicas" -or
            $_ -match "Código muerto eliminado" -or
            $_ -match "Cambios totales" -or
            $_ -match "Instrucciones (originales|optimizadas)" -or
            $_ -match "TAC (intermedio|optimizado) escrito" -or
            $_ -match "\[ERROR\]" -or
            $_ -match "\[WARNING\]"
        }
        $resumen | ForEach-Object { Write-Host $_ }
    }

    # Reglas de éxito/fallo según convención del nombre del test:
    #   t11_errors.cpp     -> debe terminar con exit code != 0 (errores en rojo)
    #   resto              -> debe terminar con exit code 0
    $expectError = $t.Name -like "t11_errors*"
    $passed = if ($expectError) { $exit -ne 0 } else { $exit -eq 0 }

    if ($passed) {
        Write-Host "[OK] $($t.Name)" -ForegroundColor Green
        $totalOk++
    } else {
        Write-Host "[FAIL] $($t.Name) (exit=$exit)" -ForegroundColor Red
        $totalFail++
    }
}

Write-Host ""
Write-Host "================================================================" -ForegroundColor Cyan
Write-Host " RESUMEN" -ForegroundColor Cyan
Write-Host "================================================================" -ForegroundColor Cyan
Write-Host (" OK   : {0}" -f $totalOk) -ForegroundColor Green
if ($totalFail -gt 0) {
    Write-Host (" FAIL : {0}" -f $totalFail) -ForegroundColor Red
} else {
    Write-Host " FAIL : 0" -ForegroundColor Green
}
exit $totalFail
