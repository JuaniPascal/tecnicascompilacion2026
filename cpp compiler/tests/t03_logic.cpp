// t03_logic.cpp — Operadores lógicos, relacionales y de igualdad.
// ESPERADO: 0 errores. Tipos: && y || requieren BOOL; <,>,==,!= producen BOOL.

int main() {
    int  a;
    int  b;
    bool ok;

    a = 5;
    b = 10;

    ok = (a < b) && (a != 0);
    ok = (a > 0) || (b == 10);
    ok = !ok;

    return a;
}
