
package com.example.clinica.domain;

/** Entidade de domínio */
public class Paciente {
    private Long id;
    private String nome;
    private String email;

    public Paciente() {}
    public Paciente(Long id, String nome, String email) {
        this.id = id; this.nome = nome; this.email = email;
    }
    public Paciente(String nome, String email) { this(null, nome, email); }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override public String toString() {
        return "Paciente{id=%d, nome='%s', email='%s'}".formatted(id, nome, email);
    }
}
