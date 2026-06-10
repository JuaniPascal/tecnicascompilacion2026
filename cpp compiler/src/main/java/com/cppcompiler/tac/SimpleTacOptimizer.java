package com.cppcompiler.tac;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Optimizador multipasada sobre TAC textual.
 *
 * <p>Implementa cuatro técnicas, requeridas por el TP, hasta alcanzar punto fijo:
 * <ol>
 *   <li><b>Plegado de constantes</b> (constant folding): reduce {@code tN = c1 op c2}
 *       a {@code tN = c}. Soporta operadores aritméticos y relacionales/igualdad sobre
 *       enteros y dobles.</li>
 *   <li><b>Propagación de constantes</b>: cuando una variable o temporal recibe un
 *       literal y no se reasigna en el bloque básico, sus usos siguientes se reemplazan
 *       por el literal.</li>
 *   <li><b>Simplificación algebraica</b>: identidades comunes:
 *       {@code x+0=x, 0+x=x, x-0=x, x*1=x, 1*x=x, x*0=0, 0*x=0, x/1=x, x-x=0}.
 *       También colapso de copias triviales {@code tN = X} (con X variable o literal):
 *       el destino se sustituye por X en sus usos posteriores.</li>
 *   <li><b>Eliminación de código muerto</b>:
 *       <ul>
 *         <li>Instrucciones no alcanzables tras {@code return} o {@code goto}
 *             incondicional, hasta la próxima etiqueta.</li>
 *         <li>Asignaciones a temporales {@code tN = ...} cuyo destino no es leído
 *             posteriormente.</li>
 *         <li>Saltos redundantes: {@code goto L} seguido inmediatamente de {@code L:}.</li>
 *       </ul>
 *   </li>
 * </ol>
 *
 * <p>El optimizador no toca instrucciones de declaración, etiquetas, llamadas,
 * {@code return} ni {@code if_false}/{@code goto} para los flujos de control.
 */
public final class SimpleTacOptimizer {

    /** Patrón {@code dest = a op b} con operadores aritméticos / relacionales / igualdad. */
    private static final Pattern BINOP_LINE = Pattern.compile(
            "^\\s*([A-Za-z_][A-Za-z_0-9]*(?:\\[[^\\]]+\\])?)\\s*=\\s*"
                    + "(\\S+)\\s*(\\+|-|\\*|/|%|==|!=|<=|>=|<|>|&&|\\|\\|)\\s*(\\S+)\\s*$");

    /** Patrón {@code dest = X} (asignación simple, sin operador). */
    private static final Pattern COPY_LINE = Pattern.compile(
            "^\\s*([A-Za-z_][A-Za-z_0-9]*(?:\\[[^\\]]+\\])?)\\s*=\\s*([^=].*?)\\s*$");

    /** Patrón {@code if_false &lt;cond&gt; goto L}. */
    private static final Pattern IF_FALSE_LINE = Pattern.compile(
            "^\\s*if_false\\s+(\\S+)\\s+goto\\s+(\\S+)\\s*$");

    /** Patrón {@code goto L}. */
    private static final Pattern GOTO_LINE = Pattern.compile("^\\s*goto\\s+(\\S+)\\s*$");

    /** Patrón {@code L:} (etiqueta). */
    private static final Pattern LABEL_LINE = Pattern.compile("^\\s*([A-Za-z_][A-Za-z_0-9]*):\\s*$");

    /** Patrón {@code return [expr]}. */
    private static final Pattern RETURN_LINE = Pattern.compile("^\\s*return(\\s+\\S+)?\\s*$");

    /** Identificador puro (variable o temporal). */
    private static final Pattern IDENT = Pattern.compile("[A-Za-z_][A-Za-z_0-9]*");

    public record Result(
            List<String> lines,
            int foldedExpressions,
            int propagatedConstants,
            int algebraicSimplifications,
            int deadCodeRemoved) {}

    private SimpleTacOptimizer() {}

    public static Result optimize(List<String> input) {
        List<String> lines = new ArrayList<>(input);

        int folded = 0;
        int propagated = 0;
        int algebraic = 0;
        int dead = 0;

        // Iteramos hasta punto fijo: una optimización puede habilitar otras.
        for (int iter = 0; iter < 10; iter++) {
            int prevFolded = folded;
            int prevProp = propagated;
            int prevAlg = algebraic;
            int prevDead = dead;

            FoldStats fs = constantFolding(lines);
            folded += fs.folds;

            algebraic += algebraicSimplify(lines);

            PropStats ps = constantPropagation(lines);
            propagated += ps.propagations;
            algebraic += ps.copyPropagations;

            dead += deadCodeElimination(lines);

            int totalThisRound =
                    (folded - prevFolded)
                            + (propagated - prevProp)
                            + (algebraic - prevAlg)
                            + (dead - prevDead);
            if (totalThisRound == 0) {
                break;
            }
        }

        return new Result(lines, folded, propagated, algebraic, dead);
    }

    // -------------------------------------------------------------------------
    // 1) PLEGADO DE CONSTANTES
    // -------------------------------------------------------------------------

    private record FoldStats(int folds) {}

    private static FoldStats constantFolding(List<String> lines) {
        int folds = 0;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (skip(line)) {
                continue;
            }
            Matcher m = BINOP_LINE.matcher(line);
            if (!m.matches()) {
                continue;
            }
            String dest = m.group(1);
            String a = m.group(2);
            String op = m.group(3);
            String b = m.group(4);
            if (!isLiteral(a) || !isLiteral(b)) {
                continue;
            }
            String folded = ConstFoldUtil.fold(a, op, b);
            if (folded != null) {
                String newLine = dest + " = " + folded;
                if (!newLine.equals(line.trim())) {
                    folds++;
                }
                lines.set(i, newLine);
            }
        }
        return new FoldStats(folds);
    }

    // -------------------------------------------------------------------------
    // 2) PROPAGACIÓN DE CONSTANTES (y de copias triviales)
    // -------------------------------------------------------------------------

    private record PropStats(int propagations, int copyPropagations) {}

    /**
     * Recorre los bloques básicos (delimitados por etiquetas / saltos / returns).
     * Para cada bloque, mantiene un mapa {@code variable -> valor literal} y reemplaza
     * los usos posteriores. Cuando una variable se reasigna, se invalida.
     *
     * <p>También propaga copias del estilo {@code tN = X} donde X es un identificador:
     * sustituye {@code tN} por {@code X} en los usos del bloque.
     */
    private static PropStats constantPropagation(List<String> lines) {
        int propagations = 0;
        int copyProps = 0;

        Map<String, String> constEnv = new HashMap<>();
        Map<String, String> copyEnv = new HashMap<>();

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.startsWith("//")) {
                continue;
            }

            // Cualquier etiqueta o salto rompe el bloque básico simple.
            if (LABEL_LINE.matcher(line).matches()
                    || GOTO_LINE.matcher(line).matches()
                    || RETURN_LINE.matcher(line).matches()
                    || IF_FALSE_LINE.matcher(line).matches()
                    || line.startsWith("PARAM ") || line.startsWith("DECLARE ")
                    || line.startsWith("PROGRAMA_") || line.contains("CALL func_")) {
                // Pero antes de cortar el bloque, intentamos reemplazar usos en la línea
                // (ej. PARAM x, return x, if_false x goto L, t = CALL...).
                ReplaceResult rr = replaceUses(line, constEnv, copyEnv);
                if (rr.changed) {
                    lines.set(i, rr.line);
                    propagations += rr.constSubs;
                    copyProps += rr.copySubs;
                }
                if (line.contains("CALL func_")) {
                    // El call puede modificar el destino; invalidamos solo el destino si lo hay.
                    Matcher cm = COPY_LINE.matcher(rr.line);
                    if (cm.matches()) {
                        invalidate(cm.group(1), constEnv, copyEnv);
                    }
                    continue;
                }
                if (LABEL_LINE.matcher(line).matches()
                        || GOTO_LINE.matcher(line).matches()
                        || RETURN_LINE.matcher(line).matches()) {
                    constEnv.clear();
                    copyEnv.clear();
                }
                continue;
            }

            Matcher binM = BINOP_LINE.matcher(line);
            Matcher copyM = COPY_LINE.matcher(line);

            if (binM.matches()) {
                String dest = binM.group(1);
                String a = binM.group(2);
                String op = binM.group(3);
                String b = binM.group(4);
                String aRepl = lookupConst(a, constEnv);
                String bRepl = lookupConst(b, constEnv);
                if (aRepl == null) aRepl = lookupCopy(a, copyEnv);
                if (bRepl == null) bRepl = lookupCopy(b, copyEnv);
                String aFinal = aRepl != null ? aRepl : a;
                String bFinal = bRepl != null ? bRepl : b;
                int subs = 0;
                int copySubs = 0;
                if (!aFinal.equals(a) || !bFinal.equals(b)) {
                    if (isLiteral(aFinal) && !isLiteral(a)) propagations++; else if (!aFinal.equals(a)) copySubs++;
                    if (isLiteral(bFinal) && !isLiteral(b)) propagations++; else if (!bFinal.equals(b)) copySubs++;
                    line = dest + " = " + aFinal + " " + op + " " + bFinal;
                    lines.set(i, line);
                }
                propagations += subs;
                copyProps += copySubs;
                // dest se reasigna, invalidamos.
                invalidate(dest, constEnv, copyEnv);
                continue;
            }

            if (copyM.matches()) {
                String dest = copyM.group(1);
                String rhsExpr = copyM.group(2).trim();
                String repl = lookupConst(rhsExpr, constEnv);
                if (repl == null) repl = lookupCopy(rhsExpr, copyEnv);
                String rhsFinal = repl != null ? repl : rhsExpr;
                if (!rhsFinal.equals(rhsExpr)) {
                    if (isLiteral(rhsFinal) && !isLiteral(rhsExpr)) propagations++;
                    else copyProps++;
                    line = dest + " = " + rhsFinal;
                    lines.set(i, line);
                }
                invalidate(dest, constEnv, copyEnv);
                if (isLiteral(rhsFinal)) {
                    constEnv.put(dest, rhsFinal);
                } else if (IDENT.matcher(rhsFinal).matches() && !rhsFinal.equals(dest)) {
                    copyEnv.put(dest, rhsFinal);
                }
                continue;
            }
        }

        return new PropStats(propagations, copyProps);
    }

    private static String lookupConst(String token, Map<String, String> env) {
        if (token == null) return null;
        return env.get(token);
    }

    private static String lookupCopy(String token, Map<String, String> env) {
        if (token == null) return null;
        return env.get(token);
    }

    private static void invalidate(String name, Map<String, String> a, Map<String, String> b) {
        a.remove(name);
        b.remove(name);
        // También invalidamos cualquier copia que apuntaba a 'name' (porque ya no es válido).
        a.entrySet().removeIf(e -> e.getValue().equals(name));
        b.entrySet().removeIf(e -> e.getValue().equals(name));
    }

    private record ReplaceResult(String line, boolean changed, int constSubs, int copySubs) {}

    /** Reemplaza identificadores libres en una línea de uso (sin destino LHS) usando los env. */
    private static ReplaceResult replaceUses(
            String line, Map<String, String> constEnv, Map<String, String> copyEnv) {
        // No reemplazamos en etiquetas ni en goto puro.
        if (LABEL_LINE.matcher(line).matches() || GOTO_LINE.matcher(line).matches()) {
            return new ReplaceResult(line, false, 0, 0);
        }
        // Para PARAM x / return x / if_false x goto L / lhs = CALL func..., args usan constEnv/copyEnv.
        // Recorremos token a token y reemplazamos identificadores que no formen parte del lhs.
        // Para simplificar: separamos en parte "antes de '='" (LHS) y "después" (RHS) si hay '=';
        // sino, todo es parte de uso.
        int eq = line.indexOf('=');
        String prefix;
        String body;
        if (eq >= 0 && eq + 1 < line.length() && line.charAt(eq + 1) != '=') {
            prefix = line.substring(0, eq + 1);
            body = line.substring(eq + 1);
        } else {
            prefix = "";
            body = line;
        }

        StringBuilder sb = new StringBuilder();
        int i = 0;
        int constSubs = 0;
        int copySubs = 0;
        boolean changed = false;
        while (i < body.length()) {
            char c = body.charAt(i);
            if (Character.isJavaIdentifierStart(c)) {
                int j = i + 1;
                while (j < body.length() && Character.isJavaIdentifierPart(body.charAt(j))) j++;
                String tok = body.substring(i, j);
                String repl = constEnv.get(tok);
                boolean isConst = repl != null;
                if (repl == null) {
                    repl = copyEnv.get(tok);
                }
                if (repl != null && !isReservedKeyword(tok)) {
                    sb.append(repl);
                    if (isConst) constSubs++; else copySubs++;
                    changed = true;
                } else {
                    sb.append(tok);
                }
                i = j;
            } else {
                sb.append(c);
                i++;
            }
        }
        return new ReplaceResult(prefix + sb, changed, constSubs, copySubs);
    }

    private static boolean isReservedKeyword(String tok) {
        return switch (tok) {
            case "goto", "if_false", "return", "PARAM", "CALL", "DECLARE",
                    "PROGRAMA_INICIO", "PROGRAMA_FIN", "true", "false", "func",
                    "int", "double", "char", "bool", "void" -> true;
            default -> false;
        };
    }

    // -------------------------------------------------------------------------
    // 3) SIMPLIFICACIÓN ALGEBRAICA
    // -------------------------------------------------------------------------

    private static int algebraicSimplify(List<String> lines) {
        int simplifications = 0;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (skip(line)) {
                continue;
            }
            Matcher m = BINOP_LINE.matcher(line);
            if (!m.matches()) {
                continue;
            }
            String dest = m.group(1);
            String a = m.group(2);
            String op = m.group(3);
            String b = m.group(4);
            String simplified = applyAlgebraicIdentity(a, op, b);
            if (simplified != null) {
                lines.set(i, dest + " = " + simplified);
                simplifications++;
            }
        }
        return simplifications;
    }

    /** Devuelve la simplificación del RHS o null si no aplica ninguna identidad. */
    private static String applyAlgebraicIdentity(String a, String op, String b) {
        boolean aIsZero = isLiteralValue(a, 0);
        boolean bIsZero = isLiteralValue(b, 0);
        boolean aIsOne = isLiteralValue(a, 1);
        boolean bIsOne = isLiteralValue(b, 1);

        switch (op) {
            case "+":
                if (aIsZero) return b;        // 0 + x -> x
                if (bIsZero) return a;        // x + 0 -> x
                break;
            case "-":
                if (bIsZero) return a;        // x - 0 -> x
                if (a.equals(b) && IDENT.matcher(a).matches()) return "0"; // x - x -> 0
                break;
            case "*":
                if (aIsZero || bIsZero) return "0"; // 0*x = x*0 = 0
                if (aIsOne) return b;          // 1 * x -> x
                if (bIsOne) return a;          // x * 1 -> x
                break;
            case "/":
                if (bIsOne) return a;          // x / 1 -> x
                if (a.equals(b) && IDENT.matcher(a).matches()) return "1"; // x / x -> 1 (asumiendo b != 0)
                break;
            case "&&":
                if ("false".equals(a) || "false".equals(b)) return "false";
                if ("true".equals(a)) return b;
                if ("true".equals(b)) return a;
                break;
            case "||":
                if ("true".equals(a) || "true".equals(b)) return "true";
                if ("false".equals(a)) return b;
                if ("false".equals(b)) return a;
                break;
            default:
                return null;
        }
        return null;
    }

    private static boolean isLiteralValue(String tok, long val) {
        if (tok == null) return false;
        try {
            if (tok.contains(".")) {
                return Double.parseDouble(tok) == (double) val;
            }
            return Long.parseLong(tok) == val;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // -------------------------------------------------------------------------
    // 4) ELIMINACIÓN DE CÓDIGO MUERTO
    // -------------------------------------------------------------------------

    /**
     * Eliminamos:
     * <ul>
     *   <li>Líneas inalcanzables tras un return o goto incondicional, hasta la próxima etiqueta.</li>
     *   <li>Asignaciones a temporal {@code tN = ...} cuyo destino no aparece más adelante.</li>
     *   <li>Pares {@code goto L} seguidos inmediatamente de {@code L:}.</li>
     * </ul>
     */
    private static int deadCodeElimination(List<String> lines) {
        int removed = 0;
        // 4.1 Inalcanzables tras return/goto
        removed += removeUnreachable(lines);
        // 4.2 Pares goto-L / L:
        removed += removeRedundantGotos(lines);
        // 4.3 Temporales no usados
        removed += removeUnusedTemps(lines);
        return removed;
    }

    private static int removeUnreachable(List<String> lines) {
        int removed = 0;
        boolean unreachable = false;
        for (int i = 0; i < lines.size(); ) {
            String line = lines.get(i);
            if (line.startsWith("//")) {
                i++;
                continue;
            }
            if (LABEL_LINE.matcher(line).matches()) {
                unreachable = false;
                i++;
                continue;
            }
            if (unreachable) {
                lines.remove(i);
                removed++;
                continue;
            }
            if (RETURN_LINE.matcher(line).matches() || GOTO_LINE.matcher(line).matches()) {
                unreachable = true;
            }
            i++;
        }
        return removed;
    }

    private static int removeRedundantGotos(List<String> lines) {
        int removed = 0;
        for (int i = 0; i < lines.size() - 1; ) {
            String a = lines.get(i);
            String b = lines.get(i + 1);
            Matcher gm = GOTO_LINE.matcher(a);
            Matcher lm = LABEL_LINE.matcher(b);
            if (gm.matches() && lm.matches() && gm.group(1).equals(lm.group(1))) {
                lines.remove(i);
                removed++;
                // No avanzamos para chequear la nueva i contra i+1.
            } else {
                i++;
            }
        }
        return removed;
    }

    private static int removeUnusedTemps(List<String> lines) {
        int removed = 0;
        // Calculamos qué temporales son leídos en alguna línea.
        Set<String> usedNames = collectUsages(lines);
        for (int i = 0; i < lines.size(); ) {
            String line = lines.get(i);
            if (skip(line)) {
                i++;
                continue;
            }
            String dest = extractAssignDestTemp(line);
            if (dest != null) {
                // Cuenta el destino como "usado" solo si aparece como uso en otra línea.
                // Recalculamos los usos rápido buscando 'dest' como token en el resto.
                if (!isTempName(dest)) {
                    i++;
                    continue;
                }
                if (!usedNames.contains(dest) && !lineHasSideEffect(line)) {
                    lines.remove(i);
                    removed++;
                    // Recalcular usos porque al eliminar se podría haber liberado otro temporal.
                    usedNames = collectUsages(lines);
                    continue;
                }
            }
            i++;
        }
        return removed;
    }

    /** Recolecta todos los identificadores que aparecen como USO (no como destino LHS). */
    private static Set<String> collectUsages(List<String> lines) {
        Set<String> used = new HashSet<>();
        for (String line : lines) {
            if (skip(line)) continue;
            // Saltamos LHS si lo hay (parte antes del '=').
            int eq = line.indexOf('=');
            String body;
            if (eq >= 0 && eq + 1 < line.length() && line.charAt(eq + 1) != '='
                    && !line.startsWith("if_false")) {
                body = line.substring(eq + 1);
                // Pero el LHS puede ser un subscript var[i] donde "i" SÍ es uso.
                String lhs = line.substring(0, eq);
                int lb = lhs.indexOf('[');
                int rb = lhs.indexOf(']');
                if (lb >= 0 && rb > lb) {
                    String idx = lhs.substring(lb + 1, rb);
                    addIdentTokens(used, idx);
                }
            } else {
                body = line;
            }
            addIdentTokens(used, body);
        }
        return used;
    }

    private static void addIdentTokens(Set<String> set, String s) {
        Matcher m = IDENT.matcher(s);
        while (m.find()) {
            String tok = m.group();
            if (!isReservedKeyword(tok)) {
                set.add(tok);
            }
        }
    }

    private static String extractAssignDestTemp(String line) {
        Matcher m = COPY_LINE.matcher(line);
        if (m.matches()) {
            String dest = m.group(1);
            return dest;
        }
        return null;
    }

    private static boolean isTempName(String name) {
        return name != null && name.matches("t\\d+");
    }

    /**
     * Una línea {@code dest = RHS} tiene side-effect (no se puede eliminar) si el RHS
     * es una llamada {@code CALL func_...}. (Otras simples sólo computan.)
     */
    private static boolean lineHasSideEffect(String line) {
        return line.contains("CALL func_");
    }

    // -------------------------------------------------------------------------
    // Auxiliares comunes
    // -------------------------------------------------------------------------

    private static boolean skip(String line) {
        if (line == null) return true;
        String t = line.trim();
        if (t.isEmpty()) return true;
        if (t.startsWith("//")) return true;
        if (t.startsWith("DECLARE ")) return true;
        if (t.startsWith("PARAM ")) return true;
        if (t.startsWith("PROGRAMA_")) return true;
        if (t.startsWith("func_")) return true;
        if (LABEL_LINE.matcher(t).matches()) return true;
        return false;
    }

    private static boolean isLiteral(String s) {
        if (s == null) return false;
        if ("true".equals(s) || "false".equals(s)) return true;
        try {
            if (s.contains(".")) {
                Double.parseDouble(s);
            } else {
                Long.parseLong(s);
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String formatOptimizedHeader(int originalLines, int optimizedLines, Result result) {
        int totalChanges =
                result.foldedExpressions()
                        + result.propagatedConstants()
                        + result.algebraicSimplifications()
                        + result.deadCodeRemoved();
        double pct = originalLines == 0 ? 0.0 : (100.0 * totalChanges / originalLines);
        StringBuilder sb = new StringBuilder();
        sb.append("\n// Código de tres direcciones OPTIMIZADO\n");
        sb.append("// Archivo: ejemplo_codigo_optimizado.txt\n");
        sb.append("// Optimizaciones aplicadas:\n");
        sb.append("//   1) Plegado de constantes (constant folding): tN = c1 op c2 -> tN = c\n");
        sb.append("//   2) Propagación de constantes: si tN = c y luego se usa tN -> reemplazar por c\n");
        sb.append("//   3) Simplificación algebraica: x+0, x*1, x-x, x*0, x/1, etc.\n");
        sb.append("//   4) Eliminación de código muerto: instrucciones inalcanzables tras return/goto\n");
        sb.append("//      y temporales calculados pero nunca usados.\n");
        sb.append("//\n");
        sb.append("// Estadísticas de optimización:\n");
        sb.append("//   Instrucciones originales : ").append(originalLines).append('\n');
        sb.append("//   Instrucciones optimizadas: ").append(optimizedLines).append('\n');
        sb.append("//   - Plegado de constantes        : ").append(result.foldedExpressions()).append('\n');
        sb.append("//   - Propagación de constantes    : ").append(result.propagatedConstants()).append('\n');
        sb.append("//   - Simplificaciones algebraicas : ").append(result.algebraicSimplifications()).append('\n');
        sb.append("//   - Código muerto eliminado      : ").append(result.deadCodeRemoved()).append('\n');
        sb.append("//   Cambios totales / líneas originales: ")
                .append(String.format(Locale.ROOT, "%.2f", pct))
                .append("%\n\n");
        return sb.toString();
    }

    static final class ConstFoldUtil {
        private ConstFoldUtil() {}

        static String fold(String left, String op, String right) {
            try {
                if (left.contains(".") || right.contains(".")) {
                    double a = Double.parseDouble(left);
                    double b = Double.parseDouble(right);
                    if (("/".equals(op) || "%".equals(op)) && b == 0.0) {
                        return null;
                    }
                    Double r =
                            switch (op) {
                                case "+" -> a + b;
                                case "-" -> a - b;
                                case "*" -> a * b;
                                case "/" -> a / b;
                                case "%" -> a % b;
                                case ">" -> a > b ? 1.0 : 0.0;
                                case "<" -> a < b ? 1.0 : 0.0;
                                case ">=" -> a >= b ? 1.0 : 0.0;
                                case "<=" -> a <= b ? 1.0 : 0.0;
                                case "==" -> a == b ? 1.0 : 0.0;
                                case "!=" -> a != b ? 1.0 : 0.0;
                                default -> null;
                            };
                    if (r == null || Double.isNaN(r)) {
                        return null;
                    }
                    if (op.matches("[<>=!]+")) {
                        return r != 0.0 ? "1" : "0";
                    }
                    if (r == (long) (double) r) {
                        return String.valueOf((long) (double) r);
                    }
                    return String.valueOf((double) r);
                }
                long a = Long.parseLong(left);
                long b = Long.parseLong(right);
                if ("/".equals(op) && b == 0) {
                    return null;
                }
                if ("%".equals(op) && b == 0) {
                    return null;
                }
                Long r =
                        switch (op) {
                            case "+" -> a + b;
                            case "-" -> a - b;
                            case "*" -> a * b;
                            case "/" -> a / b;
                            case "%" -> a % b;
                            case ">" -> (long) (a > b ? 1 : 0);
                            case "<" -> (long) (a < b ? 1 : 0);
                            case ">=" -> (long) (a >= b ? 1 : 0);
                            case "<=" -> (long) (a <= b ? 1 : 0);
                            case "==" -> (long) (a == b ? 1 : 0);
                            case "!=" -> (long) (a != b ? 1 : 0);
                            default -> null;
                        };
                if (r == null) {
                    return null;
                }
                if (op.matches("[<>=!]+")) {
                    return r != 0 ? "1" : "0";
                }
                return String.valueOf(r);
            } catch (NumberFormatException e) {
                return null;
            }
        }
    }
}
