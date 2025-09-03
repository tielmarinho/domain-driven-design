package br.com.fiap.domain;

import br.com.fiap.interfaces.IPoliticaPromocao;

import java.time.*;

public class PromocaoDiaSemana implements IPoliticaPromocao {
    private final DayOfWeek diaElegivel;
    private final double descontoPercentual; // 0.10 = 10%
    private final Clock clock;

    public PromocaoDiaSemana(DayOfWeek diaElegivel, double descontoPercentual) {
        this(diaElegivel, descontoPercentual, Clock.systemDefaultZone());
    }

    public PromocaoDiaSemana(DayOfWeek diaElegivel, double descontoPercentual, Clock clock) {
        if (descontoPercentual < 0 || descontoPercentual > 1) {
            throw new IllegalArgumentException("Percentual inválido");
        }
        this.diaElegivel = diaElegivel;
        this.descontoPercentual = descontoPercentual;
        this.clock = clock;
    }

    @Override
    public double aplicar(double total) {
        DayOfWeek hoje = LocalDate.now(clock).getDayOfWeek();
        return (hoje == diaElegivel) ? total * (1 - descontoPercentual) : total;
    }
}