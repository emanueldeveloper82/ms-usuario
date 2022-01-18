package br.com.msusuario.service;

import br.com.msusuario.dto.UsuarioDTO;
import org.springframework.http.ResponseEntity;

public interface UsuarioService {

    ResponseEntity<?>  listarTodos();
    ResponseEntity<?> buscarPorId(Long id);
    ResponseEntity<?>  buscarPorNome(String nome);
    ResponseEntity<?>  buscarPorEmail(String email);
    ResponseEntity<?> salvar(UsuarioDTO usuarioDTO);
    ResponseEntity<?> remover(UsuarioDTO usuarioDTO);

}
