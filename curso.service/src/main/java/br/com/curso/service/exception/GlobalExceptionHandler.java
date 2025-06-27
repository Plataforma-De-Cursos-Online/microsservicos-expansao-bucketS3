package br.com.curso.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NaoEncontradoException.class)
    public ResponseEntity<String> handlerNaoEncontrado(NaoEncontradoException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<String> handlerNoContent(NoContentException ex) {
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(ex.getMessage());
    }

}
