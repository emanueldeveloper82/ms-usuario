package br.com.msusuario.service.impl;

import br.com.msusuario.dto.UsuarioDTO;
import br.com.msusuario.entity.Usuario;
import br.com.msusuario.repository.UsuarioRepository;
import br.com.msusuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok()
                .body(repository.findAll().stream()
                        .map(val->modelMapper.map(val, UsuarioDTO.class)));
    }

    /**
     * Método responsável por buscar um usuario pelo id;
     * @param id
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity<?> buscarPorId(Long id) {

        Optional<Usuario> usuarioOptional = Optional.empty();

        if(id != null) {
            usuarioOptional = repository.findById(id);
        }

        if (!usuarioOptional.isEmpty()) {
            return ResponseEntity.ok().body(modelMapper.map(usuarioOptional.get(), UsuarioDTO.class));
        } else {
            return ResponseEntity.badRequest().body("Não foi possível localizar um usuario.");
        }
    }

    @Override
    public ResponseEntity<?> buscarPorEmail(String email) {
        Optional<Usuario> usuarioOptional = Optional.empty();

        if(!email.isBlank()) {
            usuarioOptional = repository.findByEmail(email);
        }

        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Não foi possível localizar usuario.");
        }
        return ResponseEntity.of(usuarioOptional);
    }

    /**
     * Método responsável por buscar um usuario por nome;
     * @param nome
     * @return
     */
    @Override
    public ResponseEntity<?> buscarPorNome(String nome) {

        Optional<Usuario> usuarioOptional = Optional.empty();

        if(!nome.isBlank()) {
            usuarioOptional = repository.findByNome(nome);
        }

        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.ok().body(repository.findByNome(nome));

        } else {
            return ResponseEntity.badRequest().body("Não foi possível localizar usuario.");
        }

    }

    /**
     * Método que salva ou atualiza um usuario;
     * nem coloca no preUpdate da entity. Ver isso depois!!!!
     * @param usuarioDTO
     * @return
     */
    @Override
    public ResponseEntity<?> salvar(UsuarioDTO usuarioDTO) {

        Optional<Usuario> usuarioOptional = Optional.empty();

        if(usuarioDTO.getId() != null) {
            usuarioOptional = repository.findById(usuarioDTO.getId());
        }

        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.ok().body(repository.save(modelMapper.map(usuarioDTO, Usuario.class)));
        } else {
            if (usuarioOptional.get().getId().equals(usuarioDTO.getId())) {
                usuarioDTO.setDataCadastro(usuarioOptional.get().getDataCadastro());
                return ResponseEntity.ok().body(repository.save(modelMapper.map(usuarioDTO, Usuario.class)));
            }
            return ResponseEntity.badRequest().body(UsuarioDTO.class);
        }
    }

    @Override
    public ResponseEntity<?> remover(UsuarioDTO usuarioDTO) {

        Optional<Usuario> usuarioOptional = Optional.empty();

        if(usuarioDTO.getId() != null) {
            usuarioOptional = repository.findById(usuarioDTO.getId());
        }

        if (usuarioOptional.isEmpty()) {
            repository.delete(modelMapper.map(usuarioDTO, Usuario.class));
            return ResponseEntity.ok().body(UsuarioDTO.class);
        } else {
            return ResponseEntity.badRequest().body(UsuarioDTO.class);
        }
    }


}
