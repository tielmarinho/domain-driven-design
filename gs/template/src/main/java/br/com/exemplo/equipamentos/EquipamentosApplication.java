package br.com.exemplo.equipamentos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EquipamentosApplication {
  public static void main(String[] args) {
      SpringApplication.run(EquipamentosApplication.class, args);

      System.out.println("\n========================================");
      System.out.println("✅ Aplicação iniciada com sucesso!");
      System.out.println("App:          http://localhost:8080/equipamentos");
      System.out.println("H2 Console:   http://localhost:8080/h2-console");
      System.out.println("JDBC URL:     jdbc:h2:mem:equipdb");
      System.out.println("Usuário:      teste");
      System.out.println("Senha:        1234");
      System.out.println("========================================\n");
  }
}