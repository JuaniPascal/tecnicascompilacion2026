// =============================================================================
// profesora_warnings.cpp — Casos PUROS de WARNING para el analizador semántico.
// =============================================================================
// Debe compilar con 0 errores y N warnings; el TAC se genera igual.
//
// Warnings esperados:
//   W-1: variable global 'x' declarada y nunca usada.
//   W-2: parámetro 'a' de 'foo' declarado y nunca usado.
//   W-3: función 'bar' (no void) no garantiza un return alcanzable.
//   W-4: variable global 'comun' shadowed por una local con el mismo nombre en main.
//   W-5: variable local 'temporalSinUso' declarada y nunca usada.
// =============================================================================

int x;          // [W-1] declarada y nunca usada
int comun;      // sera shadow-eado en main [W-4]

int foo(int a) {            // [W-2] 'a' nunca se usa
    return 0;
}

int bar() {                 // [W-3] funcion no-void sin return alcanzable
    int z;
    z = 5;
    z = z + 1;
}

int main() {
    int comun;              // [W-4] oculta a la global 'comun'
    int temporalSinUso;     // [W-5] declarada y nunca usada

    comun = 10;
    comun = comun + 1;
    return comun;
}
