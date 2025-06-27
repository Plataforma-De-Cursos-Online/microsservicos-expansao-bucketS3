package br.com.matricula.service.repository;

import br.com.matricula.service.dto.CursoDto;
import br.com.matricula.service.dto.IdDto;
import br.com.matricula.service.dto.ListagemCursoMatricula;
import br.com.matricula.service.dto.ListagemUsuarioMatricula;
import br.com.matricula.service.entity.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MatriculaRepository extends JpaRepository<Matricula, UUID> {

    @Query(value = """
    SELECT
      c.titulo,
      c.descricao,
      m.data
    FROM
      matricula m
    JOIN
      curso c ON m.id_curso = c.id
    WHERE
      m.id_usuario = :id
    """, nativeQuery = true)
    List<ListagemCursoMatricula> listarCursosPorUsuario(@Param("id") UUID id);

    @Query(value = """
            SELECT
              u.nome,
              u.login
            FROM
              matricula m
            JOIN
              usuario u ON m.id_usuario = u.id
            WHERE
              m.id_curso = :idCurso
            """, nativeQuery = true)
    List<ListagemUsuarioMatricula> listarUsuariosPorCurso(UUID idCurso);

    @Query(value = """
            SELECT
              *
            FROM
              matricula m
            WHERE
              m.id_curso = :idCurso AND m.id_usuario = :idUsuario LIMIT 1
            """, nativeQuery = true)
    Optional<Matricula> validarMatricula(UUID idCurso, UUID idUsuario);


    @Query(value = "SELECT EXISTS (SELECT 1\n" +
            "           FROM matricula\n" +
            "            WHERE id_usuario = :idUsuario\n" +
            "            AND id_curso = :idCurso\n" +
            "            ) AS existe;", nativeQuery = true)
    Boolean verificarMaatricula(UUID idCurso, UUID idUsuario);
}
