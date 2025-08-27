
package com.example.clinica.service;

import com.example.clinica.dao.PacienteDao;
import com.example.clinica.domain.Paciente;

import java.util.List;
import java.util.Optional;

/** Serviço de aplicação para Paciente */
public class PacienteService {
    private final PacienteDao dao;
    public PacienteService(PacienteDao dao) { this.dao = dao; }

    public Long cadastrar(String nome, String email) { return dao.salvar(new Paciente(nome, email)); }
    public void atualizar(Paciente p) { dao.atualizar(p); }
    public void excluir(Long id) { dao.excluir(id); }
    public Optional<Paciente> porId(Long id) { return dao.porId(id); }
    public List<Paciente> listar() { return dao.listar(); }
}
