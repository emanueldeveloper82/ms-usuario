package br.com.msusuario.repository;

import br.com.msusuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNome(String nome);

    Optional<Usuario> findByNomeContains(String nome);

    Optional<Usuario> findByEmail(String email);

}
