package br.com.usuario.exception;

public class NaoEncontradoException extends RuntimeException {
    public NaoEncontradoException(String message) {
        super(message);
    }
}
