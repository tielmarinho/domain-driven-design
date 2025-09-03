
package com.example.clinica.service;

import com.example.clinica.dao.MedicoDao;
import com.example.clinica.domain.Medico;

import java.util.List;
import java.util.Optional;

public class MedicoService {
    private final MedicoDao dao;
    public MedicoService(MedicoDao dao) { this.dao = dao; }

    public Long cadastrar(String nome, String crm) { return dao.salvar(new Medico(nome, crm)); }
    public void atualizar(Medico m) { dao.atualizar(m); }
    public void excluir(Long id) { dao.excluir(id); }
    public Optional<Medico> porId(Long id) { return dao.porId(id); }
    public List<Medico> listar() { return dao.listar(); }
}