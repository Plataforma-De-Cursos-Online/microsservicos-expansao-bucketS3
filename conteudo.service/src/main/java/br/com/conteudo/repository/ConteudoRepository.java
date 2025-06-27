package br.com.conteudo.repository;

import br.com.conteudo.entity.Conteudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ConteudoRepository extends JpaRepository<Conteudo, UUID> {

    @Query(value = "Select * From conteudo Where id_cursos = :idCurso", nativeQuery = true)
    List<Conteudo> findAllConteudo(UUID idCurso);
}
