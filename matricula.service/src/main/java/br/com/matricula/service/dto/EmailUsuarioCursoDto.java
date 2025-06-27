package br.com.matricula.service.dto;

public class EmailUsuarioCursoDto{
    private String login;
    private String nome;
    private String titulo;
    private String descricao;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public EmailUsuarioCursoDto(String login, String nome, String titulo, String descricao) {
        this.login = login;
        this.nome = nome;
        this.titulo = titulo;
        this.descricao = descricao;
    }
}