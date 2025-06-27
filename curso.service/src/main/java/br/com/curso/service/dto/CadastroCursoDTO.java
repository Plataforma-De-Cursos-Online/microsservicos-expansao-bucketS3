package br.com.curso.service.dto;

import br.com.curso.service.tipos.TipoCurso;

import java.util.UUID;

public record CadastroCursoDTO(
        String titulo,
        String descricao,
        Double preco,
        Boolean disponivel,
        UUID idUsuario,
        TipoCurso tipoCurso
) {
}
