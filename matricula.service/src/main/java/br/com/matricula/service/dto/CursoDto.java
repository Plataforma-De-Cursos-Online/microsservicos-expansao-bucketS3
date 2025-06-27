package br.com.matricula.service.dto;

import java.util.UUID;

public record CursoDto (
        String titulo,
        Double preco,
        String descricao,
        UUID id

){}