package com.example.clinic.domain.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Consulta {
    private Long id;
    private Long pacienteId;
    private Long medicoId;
    private LocalDateTime inicio;
    private LocalDateTime fim;

    public Consulta(Long id, Long pacienteId, Long medicoId, LocalDateTime inicio, LocalDateTime fim) {
        this.id = id;
        this.pacienteId = Objects.requireNonNull(pacienteId);
        this.medicoId = Objects.requireNonNull(medicoId);
        this.inicio = Objects.requireNonNull(inicio);
        this.fim = Objects.requireNonNull(fim);
        if (!fim.isAfter(inicio)) {
            throw new IllegalArgumentException("Fim deve ser após início");
        }
    }

    public Long getId() { return id; }
    public Long getPacienteId() { return pacienteId; }
    public Long getMedicoId() { return medicoId; }
    public LocalDateTime getInicio() { return inicio; }
    public LocalDateTime getFim() { return fim; }

    // Regra de negócio: duração mínima de 15 minutos
    public void validarDuracaoMinima() {
        long min = Duration.between(inicio, fim).toMinutes();
        if (min < 15) throw new IllegalArgumentException("Consulta deve ter no mínimo 15 minutos");
    }

    // Regra: horário comercial (08:00 - 18:00)
    public void validarHorarioComercial() {
        int hIni = inicio.getHour();
        int hFim = fim.getHour();
        if (hIni < 8 || hFim >= 18) {
            throw new IllegalArgumentException("Fora do horário comercial (08:00-18:00)");
        }
    }
}
