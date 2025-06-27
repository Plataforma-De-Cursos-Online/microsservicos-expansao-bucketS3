package br.com.usuario.dto;

import br.com.usuario.tipo.TipoUsuario;

public class EmailCorpoDto {

    private String nome;
    private String login;
    private TipoUsuario tipoUsuario;

    public EmailCorpoDto(String nome, String login, TipoUsuario tipoUsuario) {
        this.nome = nome;
        this.login = login;
        this.tipoUsuario = tipoUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
