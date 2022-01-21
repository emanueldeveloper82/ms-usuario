package br.com.msusuario.service;

import br.com.msusuario.dto.UsuarioDTO;
import br.com.msusuario.entity.Usuario;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> listarTodos();
    Optional<Usuario> buscarPorId(Long id);
    Optional<Usuario> buscarPorNome(String nome);
    ResponseEntity<?> buscarPorEmail(String email);
    Optional<Usuario> salvar(UsuarioDTO usuarioDTO);
    ResponseEntity<?> remover(UsuarioDTO usuarioDTO);

}
