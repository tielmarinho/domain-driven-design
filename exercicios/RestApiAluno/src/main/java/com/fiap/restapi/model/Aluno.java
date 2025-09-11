package com.fiap.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

//Data: possibilita que a pessoa não precise informar getters/setters
//AllArgsConstructor: possibilita que a pessoa não precise informar o construtor
@Data
@AllArgsConstructor
public class Aluno {
    private Long id;
    private String nome;
    private String curso;
}