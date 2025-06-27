package br.com.usuario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NaoEncontradoException.class)
    public ResponseEntity<String> naoEncontradoException(NaoEncontradoException e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(EmailExistenteException.class)
    public ResponseEntity<String> emailJaExiste(EmailExistenteException e){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(e.getMessage());
    }

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<Void> noContent(){
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
