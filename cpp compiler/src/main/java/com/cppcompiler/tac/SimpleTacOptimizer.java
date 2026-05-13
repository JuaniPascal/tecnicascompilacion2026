package com.cppcompiler.tac;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Optimización ligera sobre TAC textual: plegado de constantes en asignaciones {@code tN = lit op lit}.
 */
public final class SimpleTacOptimizer {

    private static final Pattern BINOP =
            Pattern.compile("^(t\\d+)\\s*=\\s*(-?\\d+(?:\\.\\d+)?(?:[eE][+-]?\\d+)?)\\s*([+\\-*/%])\\s*(-?\\d+(?:\\.\\d+)?(?:[eE][+-]?\\d+)?)$");

    public record Result(List<String> lines, int foldedExpressions) {}

    private SimpleTacOptimizer() {}

    public static Result optimize(List<String> lines) {
        List<String> out = new ArrayList<>(lines);
        int folds = 0;
        for (int i = 0; i < out.size(); i++) {
            String line = out.get(i);
            if (!line.contains("=") || line.startsWith("//")) {
                continue;
            }
            Matcher m = BINOP.matcher(line.trim());
            if (!m.matches()) {
                continue;
            }
            String dest = m.group(1);
            String a = m.group(2);
            String op = m.group(3);
            String b = m.group(4);
            String folded = ConstFoldUtil.fold(a, op, b);
            if (folded != null) {
                String newLine = dest + " = " + folded;
                if (!newLine.equals(line.trim())) {
                    folds++;
                }
                out.set(i, newLine);
            }
        }
        return new Result(out, folds);
    }

    public static String formatOptimizedHeader(int originalLines, int optimizedLines, int foldsApplied) {
        double pct = originalLines == 0 ? 0.0 : (100.0 * foldsApplied / originalLines);
        StringBuilder sb = new StringBuilder();
        sb.append("\n// Código de tres direcciones OPTIMIZADO\n");
        sb.append("// Archivo: ejemplo_codigo_optimizado.txt\n");
        sb.append("// Optimizaciones aplicadas:\n");
        sb.append("//   - Plegado de constantes en temporales (tN = c1 op c2)\n");
        sb.append("//\n");
        sb.append("// Estadísticas de optimización:\n");
        sb.append("//   Instrucciones originales: ").append(originalLines).append('\n');
        sb.append("//   Instrucciones optimizadas: ").append(optimizedLines).append('\n');
        sb.append("//   Expresiones plegadas: ").append(foldsApplied).append('\n');
        sb.append("//   Cambios sobre líneas totales: ")
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
                    double r =
                            switch (op) {
                                case "+" -> a + b;
                                case "-" -> a - b;
                                case "*" -> a * b;
                                case "/" -> a / b;
                                case "%" -> a % b;
                                default -> Double.NaN;
                            };
                    if (Double.isNaN(r)) {
                        return null;
                    }
                    if (r == (long) r) {
                        return String.valueOf((long) r);
                    }
                    return String.valueOf(r);
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
                            default -> null;
                        };
                if (r == null) {
                    return null;
                }
                return String.valueOf(r);
            } catch (NumberFormatException e) {
                return null;
            }
        }
    }
}
