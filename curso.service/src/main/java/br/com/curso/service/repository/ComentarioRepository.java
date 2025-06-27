package br.com.curso.service.repository;

import br.com.curso.service.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ComentarioRepository extends JpaRepository<Comentario, UUID> {
}
