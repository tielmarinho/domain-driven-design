
package com.example.clinica.dao;

import com.example.clinica.domain.Paciente;
import java.util.List;
import java.util.Optional;

/** Porta de saída (DDD) para persistência */
public interface PacienteDao {
    Long salvar(Paciente p);
    void atualizar(Paciente p);
    void excluir(Long id);
    Optional<Paciente> porId(Long id);
    List<Paciente> listar();
}
