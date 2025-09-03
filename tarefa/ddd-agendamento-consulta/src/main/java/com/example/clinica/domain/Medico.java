
package com.example.clinica.domain;

/** Entidade de domínio */
public class Medico {
    private Long id;
    private String nome;
    private String crm;

    public Medico() {}
    public Medico(Long id, String nome, String crm) {
        this.id = id; this.nome = nome; this.crm = crm;
    }
    public Medico(String nome, String crm) { this(null, nome, crm); }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCrm() { return crm; }
    public void setCrm(String crm) { this.crm = crm; }

    @Override public String toString() {
        return "Medico{id=%d, nome='%s', crm='%s'}".formatted(id, nome, crm);
    }
}