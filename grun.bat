@echo off
:: Simple grun wrapper — busca el JAR en %ANTLR_HOME% (por defecto C:\antlr)
if "%ANTLR_HOME%"=="" set "ANTLR_HOME=C:\antlr"

set "ANTLR_JAR="
for %%f in ("%ANTLR_HOME%\antlr-4.*-complete.jar" "%ANTLR_HOME%\antlr-*.jar") do (
    if exist "%%~f" set "ANTLR_JAR=%%~f"
)

if "%ANTLR_JAR%"=="" (
    echo ERROR: no se encontro antlr-*-complete.jar en %ANTLR_HOME%.
    exit /b 1
)

java -cp "%ANTLR_JAR%;." org.antlr.v4.gui.TestRig %*
