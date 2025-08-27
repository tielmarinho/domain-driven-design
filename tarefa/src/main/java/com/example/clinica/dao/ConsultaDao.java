
package com.example.clinica.dao;

import com.example.clinica.domain.Consulta;
import java.util.List;
import java.util.Optional;

/** Porta de saída (DDD) para persistência */
public interface ConsultaDao {
    Long salvar(Consulta c);
    void atualizar(Consulta c);
    void excluir(Long id);
    Optional<Consulta> porId(Long id);
    List<Consulta> listar();
}