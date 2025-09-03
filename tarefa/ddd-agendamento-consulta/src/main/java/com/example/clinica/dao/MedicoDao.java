
package com.example.clinica.dao;

import com.example.clinica.domain.Medico;
import java.util.List;
import java.util.Optional;

public interface MedicoDao {
    Long salvar(Medico m);
    void atualizar(Medico m);
    void excluir(Long id);
    Optional<Medico> porId(Long id);
    List<Medico> listar();
}