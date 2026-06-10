package com.cppcompiler.util;

/**
 * Coloreado ANSI para la salida en consola.
 *
 * <p>Convención del compilador:
 * <ul>
 *   <li>{@link #green(String)}: éxito (compilación OK, fases superadas).
 *   <li>{@link #yellow(String)}: warnings (no detienen la compilación).
 *   <li>{@link #red(String)}: errores (detienen la compilación).
 * </ul>
 *
 * <p>Se desactiva si la variable de entorno {@code NO_COLOR} está definida (estándar
 * de facto, ver <a href="https://no-color.org/">no-color.org</a>) o si la salida no
 * es una terminal interactiva (redirigida a archivo o pipe).
 */
public final class AnsiColors {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BOLD = "\u001B[1m";

    private static final boolean ENABLED = computeEnabled();

    private AnsiColors() {}

    private static boolean computeEnabled() {
        String noColor = System.getenv("NO_COLOR");
        if (noColor != null && !noColor.isEmpty()) {
            return false;
        }
        // System.console() es null cuando se redirige stdout (p.ej. > archivo.txt)
        return System.console() != null
                || "true".equalsIgnoreCase(System.getenv("FORCE_COLOR"));
    }

    public static boolean enabled() {
        return ENABLED;
    }

    public static String red(String s) {
        return ENABLED ? RED + s + RESET : s;
    }

    public static String green(String s) {
        return ENABLED ? GREEN + s + RESET : s;
    }

    public static String yellow(String s) {
        return ENABLED ? YELLOW + s + RESET : s;
    }

    public static String bold(String s) {
        return ENABLED ? BOLD + s + RESET : s;
    }
}
