package br.com.fiap.domain;

import java.util.HashMap;
import java.util.Map;

public class Estoque {
    private final Map<Long, Integer> saldoPorPrato = new HashMap<>();

    public void definirSaldo(Long pratoId, int qtd) {
        if (qtd < 0) throw new IllegalArgumentException("Quantidade negativa");
        saldoPorPrato.put(pratoId, qtd);
    }

    public int saldo(Long pratoId) {
        return saldoPorPrato.getOrDefault(pratoId, 0);
    }

    public boolean possui(Long pratoId, int qtd) {
        return saldo(pratoId) >= qtd;
    }

    public void reservar(Long pratoId, int qtd) {
        if (!possui(pratoId, qtd)) {
            throw new IllegalStateException("Estoque insuficiente para prato " + pratoId);
        }
        saldoPorPrato.put(pratoId, saldo(pratoId) - qtd);
    }
}