// t09_const_prop.cpp — Propagación de constantes.
// ESPERADO: en el optimizado, x debería propagarse y el plegado producir y = 7 (5 + 2).

int main() {
    int x;
    int y;
    x = 5;
    y = x + 2;
    return y;
}
