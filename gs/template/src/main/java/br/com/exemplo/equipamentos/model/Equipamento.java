package br.com.exemplo.equipamentos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Equipamento {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotBlank(message = "Descrição é obrigatória")
  @Size(max = 120, message = "Descrição deve ter no máximo 120 caracteres")
  @Column(nullable = false, unique = true, length = 120)
  private String descricao;

  @Column(nullable = false)
  private Boolean ativo = true;

  @Column(nullable = false)
  private LocalDateTime criadoEm = LocalDateTime.now();

  @Column(nullable = false)
  private LocalDateTime atualizadoEm = LocalDateTime.now();

  @PreUpdate
  public void preUpdate() { this.atualizadoEm = LocalDateTime.now(); }

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getDescricao() { return descricao; }
  public void setDescricao(String descricao) { this.descricao = descricao; }

  public Boolean getAtivo() { return ativo; }
  public void setAtivo(Boolean ativo) { this.ativo = ativo; }

  public LocalDateTime getCriadoEm() { return criadoEm; }
  public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }

  public LocalDateTime getAtualizadoEm() { return atualizadoEm; }
  public void setAtualizadoEm(LocalDateTime atualizadoEm) { this.atualizadoEm = atualizadoEm; }
}
