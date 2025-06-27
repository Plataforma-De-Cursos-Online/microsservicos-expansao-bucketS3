package br.com.matricula.service.dto;


public record CertificadoDto(
        String nome,
        String curso,
        String data,
        String filename
) {}