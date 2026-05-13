package com.cppcompiler.tac;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/** Secuencia de instrucciones TAC (texto por línea, sin numeración). */
public final class TacProgram {

    private final List<String> lines = new ArrayList<>();

    public void emit(String line) {
        lines.add(line);
    }

    public void emitComment(String text) {
        lines.add("// " + text);
    }

    public List<String> getLines() {
        return Collections.unmodifiableList(lines);
    }

    public void clear() {
        lines.clear();
    }

    public static String formatNumbered(List<String> body, String headerComment, String fileComment) {
        StringBuilder sb = new StringBuilder();
        if (fileComment != null && !fileComment.isEmpty()) {
            sb.append("// ").append(fileComment).append('\n');
        }
        if (headerComment != null && !headerComment.isEmpty()) {
            sb.append("// ").append(headerComment).append('\n');
        }
        sb.append("// Total de instrucciones: ").append(body.size()).append("\n\n");
        for (int i = 0; i < body.size(); i++) {
            sb.append(String.format(Locale.ROOT, "%3d: %s%n", i, body.get(i)));
        }
        return sb.toString();
    }
}
