package br.com.fiap.service;

import br.com.fiap.dao.memory.PratoMemoryDAO;
import br.com.fiap.domain.PromocaoDiaSemana;
import br.com.fiap.interfaces.IPrato;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PedidoServiceTest {

    @Test
    void calculaTotalComPromocao() {
        IPrato dao = new PratoMemoryDAO();
        PedidoService service = new PedidoService(dao);

        // Segunda-feira fixa para aplicar 10% de desconto
        Clock clock = Clock.fixed(LocalDate.of(2025, 9, 1).atStartOfDay(ZoneId.systemDefault()).toInstant(),
                                  ZoneId.systemDefault());
        service.setPromocao(new PromocaoDiaSemana(DayOfWeek.MONDAY, 0.10, clock));

        List<Long> ids = Arrays.asList(1L, 2L); // Executivo + Vegano = 81.90
        var itens = service.montarPedidoPorIds(ids);
        double total = service.calcularTotal(itens);
        double comDesconto = service.aplicarPromocao(total);

        assertTrue(total > comDesconto);
        assertEquals(total * 0.9, comDesconto, 0.001);
    }
}
