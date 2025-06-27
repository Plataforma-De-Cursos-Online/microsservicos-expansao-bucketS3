package br.com.curso.service.entity;

import br.com.curso.service.tipos.TipoCurso;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String titulo;

    private String descricao;

    private Double preco;

    private Boolean disponivel;

    private UUID idUsuario;

    private TipoCurso tipoCurso;

}
