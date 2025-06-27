package br.com.curso.service.repository;

import br.com.curso.service.entity.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, UUID> {
}
