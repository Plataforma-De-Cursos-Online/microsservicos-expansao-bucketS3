package br.com.matricula.service.dto;

import br.com.matricula.service.tipos.StatusMatricula;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;
import java.util.UUID;

public record CadastroDto(
        UUID idUsuario,
        UUID idCurso
    )
{ }
