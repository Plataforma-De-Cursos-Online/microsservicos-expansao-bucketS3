package br.com.curso.service.dto;

import java.util.UUID;

public record CadastroAvaliacaoDTO(double avaliacao,
                                   UUID idCurso) {
}
