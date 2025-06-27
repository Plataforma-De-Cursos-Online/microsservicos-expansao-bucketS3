package br.com.matricula.service.exception;

public class NaoEncontradoException extends RuntimeException{
    public NaoEncontradoException(String e){
        super(e);
    }
}
