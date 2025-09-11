package com.fiap.restapi.controller;

import com.fiap.restapi.model.Aluno;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
    private List<Aluno> alunos = new ArrayList<>(
            Arrays.asList(new Aluno(1L, "Gabriel", "Engenharia de Software"),
                          new Aluno(2L, "Sofia", "Nutrição"))
    );

    //GET - Buscar todos(as) alunos(as) - GET http://localhost:8080/api/alunos
    @GetMapping
    public List<Aluno> listar(){
        return alunos;
    }

    //GET - Buscar por id - GET http://localhost:8080/api/alunos/{id}
    @GetMapping("/{id}")
    public Aluno buscar(@PathVariable Long id){
        return alunos.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    //POST - Adicionar - POST http://localhost:8080/api/alunos + Json Aluno
    @PostMapping
    public Aluno adicionar(@RequestBody Aluno aluno){
        // setar um valor para o id?
        // Tamanho da lista de alunos(as) atual + 1
        // Tamanho atual da lista de alunos(as) = 2
        // Próxima inclusão id = 3
        aluno.setId((long) (alunos.size() + 1));
        alunos.add(aluno);
        return aluno;
    }

    //PUT - atualizar - PUT http://localhost:8080/api/alunos/{id} + Json Aluno
    @PutMapping("/{id}")
    public Aluno atualizar(@PathVariable Long id, @RequestBody Aluno alunoAtualizado){
        Aluno aluno = buscar(id);
        //se id for igual a 2 nesse momento
        //irá trazer as informações da Sofia para atualização
        if (aluno != null){
            aluno.setNome(alunoAtualizado.getNome());
            aluno.setCurso(alunoAtualizado.getCurso());
        }
        return aluno;
    }

    //DELETE - excluir - DELETE - http://localhost:8080/api/alunos/{id}
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        alunos.removeIf(a -> a.getId().equals(id));
    }
}