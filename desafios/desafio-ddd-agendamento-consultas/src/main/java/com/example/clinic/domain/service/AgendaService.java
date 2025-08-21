package com.example.clinic.domain.service;

import com.example.clinic.domain.model.Consulta;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class AgendaService {

    public interface ConsultaRepository {
        List<Consulta> listarPorMedicoNoIntervalo(long medicoId, LocalDateTime inicio, LocalDateTime fim);
        Long salvar(Consulta c);
    }

    private final ConsultaRepository repository;

    public AgendaService(ConsultaRepository repository) {
        this.repository = repository;
    }

    // Caso de uso: agendar consulta com regras
    public Long agendar(Consulta consulta) {
        consulta.validarDuracaoMinima();
        consulta.validarHorarioComercial();
        validarAntecedencia(consulta.getInicio());
        validarChoqueDeHorario(consulta);
        return repository.salvar(consulta);
    }

    private void validarAntecedencia(LocalDateTime inicio) {
        long minutos = Duration.between(LocalDateTime.now(), inicio).toMinutes();
        if (minutos < 60) {
            throw new IllegalArgumentException("Consulta deve ser marcada com antecedência mínima de 60 minutos");
        }
    }

    private void validarChoqueDeHorario(Consulta nova) {
        List<Consulta> existentes = repository.listarPorMedicoNoIntervalo(
                nova.getMedicoId(), nova.getInicio(), nova.getFim());
        boolean conflita = existentes.stream().anyMatch(c ->
                c.getInicio().isBefore(nova.getFim()) && nova.getInicio().isBefore(c.getFim())
        );
        if (conflita) throw new IllegalStateException("Médico já possui consulta no horário");
    }
}
