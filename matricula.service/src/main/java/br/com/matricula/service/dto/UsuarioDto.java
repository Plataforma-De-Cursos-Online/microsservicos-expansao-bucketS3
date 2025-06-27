package br.com.matricula.service.dto;

import java.util.UUID;

public record UsuarioDto (
        UUID id,
        String nome,
        String login
){}
