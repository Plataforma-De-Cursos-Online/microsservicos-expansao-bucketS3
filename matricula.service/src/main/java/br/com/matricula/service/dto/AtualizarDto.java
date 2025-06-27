package br.com.matricula.service.dto;

import java.util.UUID;

public record AtualizarDto(
        UUID idUsuario,
        UUID idCurso
) {
}
