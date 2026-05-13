package com.cppcompiler.semantic;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Tabla de símbolos con pila de ámbitos: {@code Stack<Map<String, Symbol>>}.
 * Soporta shadowing (el nombre se resuelve desde el ámbito más interno hacia afuera).
 */
public final class ScopedSymbolTable {

    private final Deque<Map<String, Symbol>> scopes = new ArrayDeque<>();

    public void enterScope() {
        scopes.push(new HashMap<>());
    }

    public void exitScope() {
        if (scopes.isEmpty()) {
            throw new IllegalStateException("exitScope sin enterScope previo");
        }
        scopes.pop();
    }

    public void define(Symbol sym) {
        if (scopes.isEmpty()) {
            throw new IllegalStateException("define sin ámbito activo");
        }
        Map<String, Symbol> top = scopes.peek();
        if (top.containsKey(sym.name())) {
            throw new RuntimeException("símbolo duplicado en el mismo ámbito: " + sym.name());
        }
        top.put(sym.name(), sym);
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

    public boolean isEmpty() {
        return scopes.isEmpty();
    }
}
