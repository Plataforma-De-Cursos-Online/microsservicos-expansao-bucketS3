package br.com.matricula.service.exception;

public class ConflitoException extends RuntimeException{
    public ConflitoException(String e){
        super(e);
    }
}
