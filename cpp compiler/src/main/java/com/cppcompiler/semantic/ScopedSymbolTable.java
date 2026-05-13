package com.cppcompiler.semantic;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Tabla de símbolos con pila de ámbitos: {@code Stack<Map<String, Symbol>>}.
 * Soporta shadowing (el nombre se resuelve desde el ámbito más interno hacia afuera).
 *
 * <p>Mantiene orden de inserción dentro de cada ámbito para emitir diagnósticos deterministas.
 */
public final class ScopedSymbolTable {

    private final Deque<Map<String, Symbol>> scopes = new ArrayDeque<>();

    public void enterScope() {
        scopes.push(new LinkedHashMap<>());
    }

    public void exitScope() {
        if (scopes.isEmpty()) {
            throw new IllegalStateException("exitScope sin enterScope previo");
        }
        scopes.pop();
    }

    /** Sale del ámbito actual y devuelve su tabla de símbolos para inspección (p. ej., warnings de no usados). */
    public Map<String, Symbol> exitScopeAndCollect() {
        if (scopes.isEmpty()) {
            throw new IllegalStateException("exitScopeAndCollect sin enterScope previo");
        }
        return scopes.pop();
    }

    /**
     * Define un símbolo en el ámbito actual. Devuelve {@code false} si ya existía
     * (caso de duplicado en el mismo ámbito); el llamador decide si emitir diagnóstico.
     */
    public boolean define(Symbol sym) {
        if (scopes.isEmpty()) {
            throw new IllegalStateException("define sin ámbito activo");
        }
        Map<String, Symbol> top = scopes.peek();
        if (top.containsKey(sym.name())) {
            return false;
        }
        top.put(sym.name(), sym);
        return true;
    }

    public Optional<Symbol> resolveLocal(String name) {
        if (scopes.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(scopes.peek().get(name));
    }

    public Optional<Symbol> resolve(String name) {
        for (Map<String, Symbol> scope : scopes) {
            Symbol s = scope.get(name);
            if (s != null) {
                return Optional.of(s);
            }
        }
        return Optional.empty();
    }

    /** Resuelve sólo en ámbitos exteriores al actual (útil para detectar shadowing). */
    public Optional<Symbol> resolveOuter(String name) {
        boolean skippedTop = false;
        for (Map<String, Symbol> scope : scopes) {
            if (!skippedTop) {
                skippedTop = true;
                continue;
            }
            Symbol s = scope.get(name);
            if (s != null) {
                return Optional.of(s);
            }
        }
        return Optional.empty();
    }

    public boolean isEmpty() {
        return scopes.isEmpty();
    }
}
