// =============================================================================
// profesora_all_errors.cpp — copia de profesora.cpp con LOS 5 ERR ACTIVOS a la vez.
// Debe reportar los 5 errores semanticos juntos en una sola corrida.
// =============================================================================

int contadorGlobal;
double valorPi;
char inicial;
bool activo;
int temp;

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

    // [ERR-1] identificador no declarado
    variableQueNuncaSeDeclaro = 10;

    // [ERR-2] tipos incompatibles (bool -> int)
    temp = true;

    // [ERR-3] aridad incorrecta (sumar pide 2 args)
    estado = sumar(temp);

    // [ERR-4] operador logico con tipos no BOOL (int && bool)
    temp = temp && true;

    contadorGlobal = 0;
    valorPi = 3.14;
    inicial = 'M';

    numeros[0] = 10;
    numeros[1] = 20;
    numeros[2] = 30;

    if (estado > 0) {
        int auxiliar;
        auxiliar = estado + 10;
        estado = auxiliar;
    }

    // [ERR-5] return con tipo incompatible (bool en funcion int)
    return true;
}
