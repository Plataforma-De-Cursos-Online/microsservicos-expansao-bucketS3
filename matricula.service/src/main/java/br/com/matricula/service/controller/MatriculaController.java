package br.com.matricula.service.controller;

import br.com.matricula.service.dto.*;
import br.com.matricula.service.entity.Matricula;
import br.com.matricula.service.service.MatriculaService;
import br.com.matricula.service.tipos.StatusMatricula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/matricula")
public class MatriculaController {

    @Autowired
    MatriculaService service;

    @PostMapping
    public ResponseEntity<Matricula> cadastrar(@RequestBody CadastroDto dados){
        return ResponseEntity.status(201).body(service.cadastrar(dados));
    }

    @GetMapping
    public ResponseEntity<List<Matricula>> listarTodos(){
        return ResponseEntity.status(200).body(service.listarTodos());
    }

    @GetMapping("/cursos-por-usuario/{idUsuario}")
    public ResponseEntity<List<ListagemCursoMatricula>> listarCursosPorUsuario(@PathVariable UUID idUsuario){
        return ResponseEntity.status(200).body(service.listarCursosPorUsuario(idUsuario));
    }

    @GetMapping("/usuarios-por-curso/{idCurso}")
    public ResponseEntity<List<ListagemUsuarioMatricula>> listarUsuariosPorCurso(@PathVariable UUID idCurso){
        return ResponseEntity.status(200).body(service.listarUsuariosPorCurso(idCurso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Matricula> atualizar(@RequestBody AtualizarDto dados, @PathVariable UUID id){
        return ResponseEntity.status(200).body(service.atualizar(id, dados));
    }

    @PatchMapping("/{id}/{status}")
    public ResponseEntity<Void> atualizarStatus(@PathVariable UUID id, @PathVariable String status) {

        StatusMatricula novoStatus;
        try {
            novoStatus = StatusMatricula.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

        service.atualizarStatus(id, novoStatus);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/verificar-matricula/{idCurso}")
    public ResponseEntity<Boolean> verificarMatricula(@PathVariable UUID idCurso, @RequestHeader("Authorization")  String authorizationHeader){
        return ResponseEntity.status(200).body(service.verificarMatricula(idCurso, authorizationHeader));
    }

}
