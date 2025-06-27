package br.com.conteudo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(br.com.conteudo.exception.CursoNaoEncontradoException.class)
    public ResponseEntity<String> handleCursoNaoExiste(br.com.conteudo.exception.CursoNaoEncontradoException e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(br.com.conteudo.exception.ConteudoNaoEncontradoException.class)
    public ResponseEntity<String> handleConteudoNaoExiste(br.com.conteudo.exception.ConteudoNaoEncontradoException e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(br.com.conteudo.exception.NenhumConteudoCadastradoException.class)
    public ResponseEntity<String> handleNenhumConteudoCadastrado(br.com.conteudo.exception.NenhumConteudoCadastradoException e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }


}
