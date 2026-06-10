// t02_arith.cpp — Aritmética entera y de doble precisión + operadores % / *.
// ESPERADO: 0 errores. En el optimizado, las constantes deben plegarse.

int main() {
    int a;
    int b;
    int c;
    double d;

    a = 2 + 3 * 4;       // 14, plegado total
    b = (10 - 4) / 2;    // 3,  plegado total
    c = a + b;
    d = 1.5 * 2.0 + 0.5; // 3.5, plegado total
    c = c % 5;

    return c;
}
