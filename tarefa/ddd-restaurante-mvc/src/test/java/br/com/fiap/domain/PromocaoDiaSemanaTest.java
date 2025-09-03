package br.com.fiap.domain;

import org.junit.jupiter.api.Test;
import java.time.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PromocaoDiaSemanaTest {

    @Test
    void aplicaDescontoQuandoDiaElegivel() {
        Clock clock = Clock.fixed(LocalDate.of(2025, 9, 1).atStartOfDay(ZoneId.systemDefault()).toInstant(),
                                  ZoneId.systemDefault());
        PromocaoDiaSemana promo = new PromocaoDiaSemana(DayOfWeek.MONDAY, 0.10, clock);
        assertEquals(90.0, promo.aplicar(100.0), 0.001);
    }

    @Test
    void naoAplicaForaDoDiaElegivel() {
        Clock clock = Clock.fixed(LocalDate.of(2025, 9, 2).atStartOfDay(ZoneId.systemDefault()).toInstant(),
                                  ZoneId.systemDefault());
        PromocaoDiaSemana promo = new PromocaoDiaSemana(DayOfWeek.MONDAY, 0.10, clock);
        assertEquals(100.0, promo.aplicar(100.0), 0.001);
    }
}
