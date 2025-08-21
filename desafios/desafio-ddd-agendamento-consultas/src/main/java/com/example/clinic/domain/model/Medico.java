package com.example.clinic.domain.model;

import java.util.Objects;

public class Medico {
    private Long id;
    private String nome;
    private String crm;

    public Medico(Long id, String nome, String crm) {
        this.id = id;
        this.nome = Objects.requireNonNull(nome);
        this.crm = Objects.requireNonNull(crm);
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getCrm() { return crm; }
}
