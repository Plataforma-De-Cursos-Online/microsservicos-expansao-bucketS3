package br.com.usuario.exception;

public class EmailExistenteException extends RuntimeException {
    public EmailExistenteException(String message) {
        super(message);
    }
}
