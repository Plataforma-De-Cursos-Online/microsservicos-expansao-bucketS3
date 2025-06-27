package br.com.matricula.service.entity;

import br.com.matricula.service.tipos.StatusMatricula;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private StatusMatricula status;

    private UUID idUsuario;

    private UUID idCurso;


}


