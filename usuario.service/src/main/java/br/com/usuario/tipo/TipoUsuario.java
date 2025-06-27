package br.com.usuario.tipo;

import br.com.usuario.exception.NaoEncontradoException;

public enum TipoUsuario {

    PROFESSOR("Professor"),
    ALUNO("Aluno");

    private String tipoUsuario;

    TipoUsuario(String tipo) {
        this.tipoUsuario = tipo;
    }

    public static TipoUsuario fromString(String text) {
        for (TipoUsuario tipoUsuario1 : TipoUsuario.values()) {
            if (tipoUsuario1.tipoUsuario.equalsIgnoreCase(text)) {
                return tipoUsuario1;
            }
        }
        throw new NaoEncontradoException("Categoria de Usuário não encontrada");
    }
}
