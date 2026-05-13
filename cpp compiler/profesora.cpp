// =============================================================================
// profesora.cpp — Prueba del ANALIZADOR SEMÁNTICO (sintaxis válida para la gramática)
// =============================================================================
// El compilador se DETIENE en el PRIMER error semántico. Para ver otro caso:
//   comenta la línea marcada [ACTIVO] y descomenta UNA de las líneas [OPCIONAL].
// =============================================================================

// Variables globales (solo declaración: typeName IDENTIFIER arrayDim? SEMI)
int contadorGlobal;
double valorPi;
char inicial;
bool activo;
int temp;

// Función simple que retorna valor
int sumar(int a, int b) {
    int resultado;
    resultado = a + b;
    contadorGlobal = contadorGlobal + 1;
    return resultado;
}

int main() {
    int estado;
    int temp;
    int numeros[3];

    // -------------------------------------------------------------------------
    // BLOQUE DE ERRORES SEMÁNTICOS (marcados) — gramática OK, tipos / símbolos NO
    // -------------------------------------------------------------------------

    // [ERR-1] IDENTIFICADOR NO DECLARADO ...................................... [ACTIVO]
    variableQueNuncaSeDeclaro = 10;

    // [ERR-2] TIPOS INCOMPATIBLES EN ASIGNACIÓN (bool → int) ................... [OPCIONAL]
    // temp = true;

    // [ERR-3] ARIDAD INCORRECTA EN LLAMADA A FUNCIÓN .......................... [OPCIONAL]
    // estado = sumar(temp);

    // [ERR-4] OPERADOR LÓGICO CON TIPOS NO BOOL (int && bool) .................. [OPCIONAL]
    // temp = temp && true;

    // [ERR-5] RETURN CON TIPO INCOMPATIBLE (bool en función int) .............. [OPCIONAL]
    // Sustituye la línea "return estado;" final por: return true;

    // -------------------------------------------------------------------------
    // Resto del programa (no se alcanza mientras falle antes un error [ACTIVO])
    // -------------------------------------------------------------------------

    contadorGlobal = 0;
    valorPi = 3.14;
    inicial = 'M';

    numeros[0] = 10;
    numeros[1] = 20;
    numeros[2] = 30;

    temp = numeros[0] + numeros[1];
    temp = temp * 2;
    temp = temp / 3;
    temp = temp % 5;

    estado = sumar(temp, 5);

    contadorGlobal = estado;
    valorPi = temp;
    inicial = 'X';

    if (estado > 0) {
        int auxiliar;
        auxiliar = estado + 10;
        estado = auxiliar;
    }

    return estado; // [ERR-5 OPCIONAL] reemplazar por: return true; (y comentar ERR-1..4)
}
