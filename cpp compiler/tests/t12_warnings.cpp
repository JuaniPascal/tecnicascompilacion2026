// t12_warnings.cpp — Genera warnings (amarillo) pero compila OK.
// ESPERADO: warnings de variable no usada y shadowing; el TAC se genera.

int x;   // global no usada explicitamente desde el codigo (warning permitido)

int main() {
    int variableNoUsada;     // WARNING: declarada y nunca usada
    int x;                    // WARNING: oculta una declaración externa (shadowing)
    x = 5;
    return x;
}
