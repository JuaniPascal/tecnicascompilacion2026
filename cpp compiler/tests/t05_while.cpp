// t05_while.cpp — while con break.
// ESPERADO: 0 errores. TAC: L_while_N, if_false ... goto L_endwhile_N, goto L_while_N, break -> goto L_endwhile_N.

int main() {
    int i;
    int suma;
    i = 0;
    suma = 0;
    while (i < 100) {
        if (suma > 50) {
            break;
        }
        suma = suma + i;
        i = i + 1;
    }
    return suma;
}
