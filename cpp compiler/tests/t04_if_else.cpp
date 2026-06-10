// t04_if_else.cpp — if/else anidados con returns garantizados en ambas ramas.
// ESPERADO: 0 errores. TAC con etiquetas L_else_N y L_endif_N.

int absoluto(int x) {
    if (x < 0) {
        return -x;
    } else {
        if (x == 0) {
            return 0;
        } else {
            return x;
        }
    }
}

int main() {
    int v;
    v = absoluto(-7);
    return v;
}
