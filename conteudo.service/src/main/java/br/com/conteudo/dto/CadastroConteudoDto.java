package br.com.conteudo.dto;

import java.util.UUID;

public record CadastroConteudoDto(String titulo, String video, String pdf, UUID idCurso) {
}
