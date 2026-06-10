// t06_for.cpp — for(int i = 0; i < N; i = i + 1) con continue.
// ESPERADO: 0 errores. TAC con L_for_N, L_forupd_N, L_endfor_N. continue -> goto L_forupd_N.

int main() {
    int total;
    total = 0;
    for (int i = 0; i < 10; i = i + 1) {
        if (i == 5) {
            continue;
        }
        total = total + i;
    }
    return total;
}
