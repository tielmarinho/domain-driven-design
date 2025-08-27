
package com.example.clinica.domain;

import java.time.LocalDateTime;

/** Entidade de domínio (Agregado simples) */
public class Consulta {
    private Long id;
    private Long pacienteId;
    private Long medicoId;
    private LocalDateTime inicio;
    private LocalDateTime fim;

    public Consulta() {}
    public Consulta(Long id, Long pacienteId, Long medicoId, LocalDateTime inicio, LocalDateTime fim) {
        this.id = id; this.pacienteId = pacienteId; this.medicoId = medicoId; this.inicio = inicio; this.fim = fim;
    }
    public Consulta(Long pacienteId, Long medicoId, LocalDateTime inicio, LocalDateTime fim) {
        this(null, pacienteId, medicoId, inicio, fim);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getPacienteId() { return pacienteId; }
    public void setPacienteId(Long pacienteId) { this.pacienteId = pacienteId; }
    public Long getMedicoId() { return medicoId; }
    public void setMedicoId(Long medicoId) { this.medicoId = medicoId; }
    public LocalDateTime getInicio() { return inicio; }
    public void setInicio(LocalDateTime inicio) { this.inicio = inicio; }
    public LocalDateTime getFim() { return fim; }
    public void setFim(LocalDateTime fim) { this.fim = fim; }

    @Override public String toString() {
        return "Consulta{id=%d, pacienteId=%d, medicoId=%d, inicio=%s, fim=%s}"
                .formatted(id, pacienteId, medicoId, inicio, fim);
    }
}
