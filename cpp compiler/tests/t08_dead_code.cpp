// t08_dead_code.cpp — Código tras un return: el optimizador debe eliminarlo.
// ESPERADO: el TAC optimizado NO contiene "x = 999" ni "x = x + 1" (líneas 4 y 5 del cuerpo).

int demo() {
    int x;
    x = 1;
    return x;       // <-- todo lo de abajo es inalcanzable
    x = 999;
    x = x + 1;
}

int main() {
    int v;
    v = demo();
    return v;
}
