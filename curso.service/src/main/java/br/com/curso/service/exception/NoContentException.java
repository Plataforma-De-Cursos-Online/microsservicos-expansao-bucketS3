package br.com.curso.service.exception;

public class NoContentException extends RuntimeException {
    public NoContentException(String message) {
        super(message);
    }
}
