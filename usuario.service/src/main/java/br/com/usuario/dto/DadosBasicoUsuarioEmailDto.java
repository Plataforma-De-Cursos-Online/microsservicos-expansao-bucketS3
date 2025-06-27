package br.com.usuario.dto;

import java.util.UUID;

public record DadosBasicoUsuarioEmailDto(
        UUID id,
        String login,
        String nome
) {
}
