// t07_func_call.cpp — Llamadas con argumentos y retorno de valores.
// ESPERADO: 0 errores. TAC: PARAM x ; PARAM y ; tN = CALL func_f, 2.

int sumar(int a, int b) {
    return a + b;
}

int duplicar(int x) {
    return x * 2;
}

int main() {
    int r1;
    int r2;
    r1 = sumar(3, 4);
    r2 = duplicar(r1);
    return sumar(r1, r2);
}
