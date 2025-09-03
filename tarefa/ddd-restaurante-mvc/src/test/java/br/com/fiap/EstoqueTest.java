package br.com.fiap;

import br.com.fiap.domain.Estoque;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EstoqueTest {

    @Test
    void reservarReduceSaldo() {
        Estoque e = new Estoque();
        e.definirSaldo(1L, 5);
        e.reservar(1L, 3);
        assertEquals(2, e.saldo(1L));
    }

    @Test
    void reservarExcedeSaldoLancaExcecao() {
        Estoque e = new Estoque();
        e.definirSaldo(1L, 2);
        assertThrows(IllegalStateException.class, () -> e.reservar(1L, 5));
    }
}
