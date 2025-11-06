package br.com.exemplo.equipamentos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
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
      public void preUpdate() { this.atualizadoEm = LocalDateTime.now();
  }
}