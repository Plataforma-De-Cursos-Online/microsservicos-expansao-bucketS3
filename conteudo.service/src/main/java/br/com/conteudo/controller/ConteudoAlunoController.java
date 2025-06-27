package br.com.conteudo.controller;

import br.com.conteudo.dto.CadastroConteudoDto;
import br.com.conteudo.service.ConteudoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/conteudo-aluno")
public class ConteudoAlunoController {

    @Autowired
    private ConteudoService conteudoService;


    @GetMapping("/{idCurso}")
    public ResponseEntity<List<CadastroConteudoDto>> listarConteudoPorId(@RequestHeader("Authorization") String authorizationHeader, @PathVariable UUID idCurso){
        try {
            var conteudo = conteudoService.listarPorId(authorizationHeader, idCurso);
            return ResponseEntity.status(200).body(conteudo);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }



}
