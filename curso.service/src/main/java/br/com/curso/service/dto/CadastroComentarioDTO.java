package br.com.curso.service.dto;

import java.util.UUID;

public record CadastroComentarioDTO(String comentario,
                                    UUID idCurso) {
}
