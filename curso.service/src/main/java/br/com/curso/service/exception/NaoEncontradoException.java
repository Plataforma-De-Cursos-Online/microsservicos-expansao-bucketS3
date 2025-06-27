package br.com.curso.service.exception;

public class NaoEncontradoException extends RuntimeException {

    public NaoEncontradoException(String message) {
        super(message);
    }
}
