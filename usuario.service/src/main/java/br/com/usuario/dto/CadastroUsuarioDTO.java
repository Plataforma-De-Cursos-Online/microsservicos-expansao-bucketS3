package br.com.usuario.dto;

public record CadastroUsuarioDTO(String nome,
                                 String login,
                                 String password,
                                 String tipoUsuario) {
}
