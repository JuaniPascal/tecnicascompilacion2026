// t01_decls.cpp — Declaraciones de variables y funciones de cada tipo del subconjunto.
// ESPERADO: 0 errores, 0 warnings, TAC con DECLARE para cada tipo.

int   gInt;
double gDouble;
char  gChar;
bool  gBool;
int   gArr[5];

int suma(int a, int b) {
    int r;
    r = a + b;
    return r;
}

void saludar(int n) {
    int x;
    x = n + 1;
}

int main() {
    int    x;
    double y;
    char   c;
    bool   ok;
    int    v[3];

    x = 10;
    y = 2.5;
    c = 'A';
    ok = true;
    v[0] = 1;
    v[1] = 2;
    v[2] = 3;

    return x;
}
