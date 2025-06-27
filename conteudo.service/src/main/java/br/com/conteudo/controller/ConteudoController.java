package br.com.conteudo.controller;

import br.com.conteudo.dto.CadastroConteudoDto;
import br.com.conteudo.exception.CursoNaoEncontradoException;
import br.com.conteudo.service.ConteudoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/conteudo-professor")
public class ConteudoController {

    @Autowired
    private ConteudoService conteudoService;

    @PostMapping("/{idCurso}")
    public ResponseEntity<CadastroConteudoDto> saveConteudo(@PathVariable UUID idCurso,
                                                            @RequestParam("file") MultipartFile file) throws IOException {
        try {
            var conteudo = conteudoService.saveConteudo(idCurso, file);
            return ResponseEntity.status(201).body(conteudo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirConteudo(@PathVariable UUID id) {
        conteudoService.deletarConteudo(id);
        return ResponseEntity.status(204).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CadastroConteudoDto> atualizarConteudo(@PathVariable UUID id, @RequestBody CadastroConteudoDto dto) {
        var conteudo = conteudoService.atualizarConteudo(id, dto);
        return ResponseEntity.status(200).body(conteudo);
    }

    @GetMapping("/{idCurso}")
    public ResponseEntity<List<CadastroConteudoDto>> listarConteudoPorId(@RequestHeader("Authorization") String authorizationHeader, @PathVariable UUID idCurso) {
        try {
            var conteudo = conteudoService.listarPorIdProfessor(authorizationHeader, idCurso);
            return ResponseEntity.status(200).body(conteudo);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
