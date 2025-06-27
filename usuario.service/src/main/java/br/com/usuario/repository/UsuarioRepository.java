package br.com.usuario.repository;

import br.com.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    boolean existsByLogin(String login);

    UserDetails findByLogin(String login);

    @Query(value = "SELECT id FROM usuario WHERE login = :login", nativeQuery = true)
    UUID findByLoginUUID(String login);

}
