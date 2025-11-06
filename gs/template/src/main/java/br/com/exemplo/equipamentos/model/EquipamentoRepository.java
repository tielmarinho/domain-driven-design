package br.com.exemplo.equipamentos.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
  Optional<Equipamento> findByDescricaoIgnoreCase(String descricao);
  List<Equipamento> findByDescricaoContainingIgnoreCase(String termo);
}
