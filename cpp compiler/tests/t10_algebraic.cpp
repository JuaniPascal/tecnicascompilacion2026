// t10_algebraic.cpp — Identidades algebraicas.
// ESPERADO: y = x*1 -> y = x ; z = y+0 -> z = y ; w = x*0 -> w = 0.

int main() {
    int x;
    int y;
    int z;
    int w;
    x = 7;
    y = x * 1;
    z = y + 0;
    w = x * 0;
    return z + w;
}
