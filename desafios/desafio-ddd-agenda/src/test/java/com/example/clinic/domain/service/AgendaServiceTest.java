package com.example.clinic.domain.service;

import com.example.clinic.domain.model.Consulta;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class AgendaServiceTest {

    @Test
    void deveAgendarConsultaComRegrasValidas() {
        AgendaService.ConsultaRepository repo = Mockito.mock(AgendaService.ConsultaRepository.class);
        AgendaService service = new AgendaService(repo);

        LocalDateTime inicio = LocalDateTime.now().plusHours(2).withHour(10).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime fim = inicio.plusMinutes(30);
        Consulta c = new Consulta(null, 1L, 10L, inicio, fim);

        when(repo.listarPorMedicoNoIntervalo(10L, inicio, fim)).thenReturn(Collections.emptyList());
        when(repo.salvar(c)).thenReturn(42L);

        Long id = service.agendar(c);
        assertEquals(42L, id);
    }

    @Test
    void naoDeveAgendarComConflitoDeHorario() {
        AgendaService.ConsultaRepository repo = Mockito.mock(AgendaService.ConsultaRepository.class);
        AgendaService service = new AgendaService(repo);

        LocalDateTime inicio = LocalDateTime.now().plusHours(2).withHour(10).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime fim = inicio.plusMinutes(30);
        Consulta nova = new Consulta(null, 1L, 10L, inicio, fim);

        Consulta existente = new Consulta(99L, 2L, 10L, inicio.minusMinutes(10), fim.plusMinutes(10));
        when(repo.listarPorMedicoNoIntervalo(10L, inicio, fim)).thenReturn(List.of(existente));

        assertThrows(IllegalStateException.class, () -> service.agendar(nova));
    }

    @Test
    void naoDeveAgendarSemAntecedenciaMinima() {
        AgendaService.ConsultaRepository repo = Mockito.mock(AgendaService.ConsultaRepository.class);
        AgendaService service = new AgendaService(repo);

        LocalDateTime inicio = LocalDateTime.now().plusMinutes(30);
        LocalDateTime fim = inicio.plusMinutes(20);
        Consulta c = new Consulta(null, 1L, 10L, inicio, fim);

        assertThrows(IllegalArgumentException.class, () -> service.agendar(c));
    }
}
