package br.com.matricula.service.dto;

import java.util.Date;
import java.util.UUID;

public record ListagemCursoMatricula  (
        String titulo,
        String descricao,
        Date data

){}