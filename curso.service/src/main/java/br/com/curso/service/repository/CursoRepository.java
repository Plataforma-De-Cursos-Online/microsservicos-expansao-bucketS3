package br.com.curso.service.repository;

import br.com.curso.service.dto.ListagemCursoDTO;
import br.com.curso.service.dto.NomeCursoDTO;
import br.com.curso.service.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CursoRepository extends JpaRepository<Curso, UUID> {

    @Query("""
        SELECT new br.com.curso.service.dto.ListagemCursoDTO(
            c.titulo,
            c.descricao,
            c.preco,
            c.idUsuario,
            c.tipoCurso
        )
        FROM Curso c
        WHERE c.disponivel = TRUE
    """)
    List<ListagemCursoDTO> listarCursosDisponiveis();

    List<Curso> findByDisponivel(Boolean disponivel);

    @Query("SELECT new br.com.curso.service.dto.NomeCursoDTO(c.titulo) FROM Curso c")
    List<NomeCursoDTO> buscarNomesDosCursos();
}

