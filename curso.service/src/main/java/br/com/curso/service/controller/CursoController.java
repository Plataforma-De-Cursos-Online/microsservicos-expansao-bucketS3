package br.com.curso.service.controller;

import br.com.curso.service.service.CursoService;
import br.com.curso.service.dto.*;
import br.com.curso.service.entity.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/curso-professor")
public class CursoController {

    @Autowired
    CursoService cursoService;

    @PostMapping
    public ResponseEntity<CadastroCursoDTO> postCurso(@RequestBody CadastroCursoDTO dto ){
        return ResponseEntity.status(201).body(cursoService.criar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable UUID id) {
        cursoService.deleteCurso(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarCurso(@PathVariable UUID id, @RequestBody CadastroCursoDTO dto) {
        cursoService.atualizarCurso(id, dto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/disponibilidade")
    public ResponseEntity<Void> atualizarDisponibilidade(
            @PathVariable UUID id,
            @RequestBody AtualizarDisponibilidadeDTO dto
    ) {
        cursoService.atualizarDisponibilidade(id, dto.disponivel());
        return ResponseEntity.noContent().build(); // HTTP 204
    }

    @PatchMapping("/{id}/professor")
    public ResponseEntity<Void> atualizarProfessor(
            @PathVariable UUID id,
            @RequestBody AtualizarProfessorDTO dto
    ) {
        cursoService.atualizarProfessor(id, dto.idUsuario());
        return ResponseEntity.noContent().build(); // HTTP 204
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> findById(@PathVariable UUID id) {
        return ResponseEntity.status(200).body(cursoService.listarUmCurso(id));
    }

    @GetMapping("disponiveis")
    public ResponseEntity<List<ListagemCursoDTO>> listarDisponiveis() {
        List<ListagemCursoDTO> cursos = cursoService.getCursosDisponiveis();
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/indisponiveis")
    public ResponseEntity<List<Curso>> listarIndisponiveis() {
        List<Curso> cursos = cursoService.getCursosIndisponiveis();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/nomes")
    public ResponseEntity<List<NomeCursoDTO>> listarNomesDosCursos() {
        List<NomeCursoDTO> nomes = cursoService.getNomesDosCursos();
        return ResponseEntity.ok(nomes);
    }

    @GetMapping
    public ResponseEntity<List<Curso>> listarTodos() {
        return ResponseEntity.status(200).body(cursoService.listarTodos());
    }
}
