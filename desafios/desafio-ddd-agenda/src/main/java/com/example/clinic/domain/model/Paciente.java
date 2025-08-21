package com.example.clinic.domain.model;

import java.util.Objects;

public class Paciente {
    private Long id;
    private String nome;
    private String email;

    public Paciente(Long id, String nome, String email) {
        this.id = id;
        this.nome = Objects.requireNonNull(nome, "nome é obrigatório");
        this.email = Objects.requireNonNull(email, "email é obrigatório");
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }

    public void alterarEmail(String novoEmail) {
        if (novoEmail == null || !novoEmail.contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
        this.email = novoEmail;
    }
}
