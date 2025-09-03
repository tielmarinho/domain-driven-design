package br.com.fiap;

import br.com.fiap.domain.PromocaoDiaSemana;
import org.junit.jupiter.api.Test;
import java.time.DayOfWeek;

import static org.junit.jupiter.api.Assertions.*;

public class PromocaoDiaSemanaTest {

    @Test
    void aplicarPromocaoNoDiaCerto() {
        PromocaoDiaSemana promo = new PromocaoDiaSemana(java.time.LocalDate.now().getDayOfWeek(), 0.2);
        double resultado = promo.aplicar(100.0);
        assertEquals(80.0, resultado, 0.001);
    }

    @Test
    void aplicarPromocaoEmOutroDiaMantemTotal() {
        DayOfWeek outroDia = java.time.LocalDate.now().getDayOfWeek().plus(1);
        PromocaoDiaSemana promo = new PromocaoDiaSemana(outroDia, 0.2);
        double resultado = promo.aplicar(100.0);
        assertEquals(100.0, resultado, 0.001);
    }
}
