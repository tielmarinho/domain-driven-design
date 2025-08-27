
package com.example.clinica.service;

import com.example.clinica.dao.ConsultaDao;
import com.example.clinica.domain.Consulta;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Serviço de aplicação (DDD): orquestra casos de uso sobre o agregado Consulta.
 */
public class ConsultaService {
    private final ConsultaDao dao;

    public ConsultaService(ConsultaDao dao) {
        this.dao = dao;
    }

    public Long agendar(Long pacienteId, Long medicoId, LocalDateTime inicio, LocalDateTime fim) {
        if (inicio.isAfter(fim) || inicio.equals(fim)) {
            throw new IllegalArgumentException("Horário inválido: início deve ser antes do fim.");
        }
        // Aqui poderiam entrar regras (ex.: checar conflitos de agenda).
        return dao.salvar(new Consulta(pacienteId, medicoId, inicio, fim));
    }

    public void alterar(Consulta c) { dao.atualizar(c); }

    public void excluir(Long id) { dao.excluir(id); }

    public Optional<Consulta> porId(Long id) { return dao.porId(id); }

    public List<Consulta> listar() { return dao.listar(); }
}
