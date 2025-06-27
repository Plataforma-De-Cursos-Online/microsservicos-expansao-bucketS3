package br.com.curso.service.dto;

import br.com.curso.service.tipos.TipoCurso;

import java.util.UUID;

public record ListagemCursoDTO(
        String titulo,
        String descricao,
        Double preco,
        UUID idUsuario,
        TipoCurso tipoCurso
) {
}
