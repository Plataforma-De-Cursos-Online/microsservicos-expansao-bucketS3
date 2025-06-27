package br.com.matricula.service.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NaoEncontradoException.class)
    public ResponseEntity<String> handleUsuarioNaoExiste(NaoEncontradoException e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(ConflitoException.class)
    public ResponseEntity<String> handleConflitoException(ConflitoException e){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(e.getMessage());
    }



}
