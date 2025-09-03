package br.com.fiap.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EstoqueTest {

    @Test
    void reservarDebitaSaldo() {
        Estoque e = new Estoque();
        e.definirSaldo(1L, 5);
        e.reservar(1L, 2);
        assertEquals(3, e.saldo(1L));
    }

    @Test
    void reservarSemSaldoLancaExcecao() {
        Estoque e = new Estoque();
        e.definirSaldo(1L, 1);
        assertThrows(IllegalStateException.class, () -> e.reservar(1L, 2));
    }
}
