// t11_errors.cpp — Casos que deben dar ERROR semántico (rojo). NO debe generar TAC.
// ESPERADOS los siguientes errores (al menos 4):
//   - identificador no declarado: 'noExiste'
//   - tipos incompatibles en asignacion
//   - aridad incorrecta en llamada a 'f'
//   - 'break' fuera de un bucle

int f(int a, int b) {
    return a + b;
}

int main() {
    int x;
    x = noExiste;          // ERR: identificador no declarado

    int t;
    t = true;              // ERR: bool -> int incompatible

    int r;
    r = f(1);              // ERR: aridad incorrecta

    break;                 // ERR: break fuera de bucle

    return x;
}
