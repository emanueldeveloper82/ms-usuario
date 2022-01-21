package br.com.msusuario.service.impl;

import br.com.msusuario.dto.UsuarioDTO;
import br.com.msusuario.entity.Usuario;
import br.com.msusuario.repository.UsuarioRepository;
import br.com.msusuario.service.UsuarioService;
import br.com.msusuario.utils.Constantes;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;
    private final ModelMapper modelMapper;

    /**
     * Método que retorna uma lista de usuários;
     * @return
     */
    @Override
    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    /**
     * Método responsável por buscar um usuario pelo id;
     * @param id
     * @return ResponseEntity
     */
    @Override
    public Optional<Usuario> buscarPorId(Long id) {

        Optional<Usuario> usuarioOptional = Optional.empty();

        if(id != null) {
            usuarioOptional = repository.findById(id);
        }

        return usuarioOptional;
    }

    @Override
    public ResponseEntity<?> buscarPorEmail(String email) {

        if(!email.isBlank()) {
            try {
                Optional<Usuario> usuarioOptional = repository.findByEmail(email);
                if (usuarioOptional.isPresent()) {
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(usuarioOptional);
                }
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body(Constantes.USUARIO_NAO_ENCONTRADO);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(Constantes.ERRO_INTERNO);
            }
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("Campo e-mail vazio ou inválido. ");
    }

    /**
     * Método responsável por buscar um usuario por nome;
     * @param nome
     * @return
     */
    @Override
    public Optional<Usuario> buscarPorNome(String nome) {

        Optional<Usuario> usuarioOptional = Optional.empty();

        if(!nome.isBlank()) {
            usuarioOptional = repository.findByNome(nome);
        }

        return usuarioOptional;

    }

    /**
     * Método que salva ou atualiza um usuario;
     * nem coloca no preUpdate da entity. Ver isso depois!!!!
     * @param usuarioDTO
     * @return
     */
    @Override
    public Optional<Usuario> salvar(UsuarioDTO usuarioDTO) {

        Optional<Usuario> usuarioOptional = Optional.empty();

        if(usuarioDTO.getId() != null) {
            usuarioOptional = repository.findById(usuarioDTO.getId());
        }

        if (usuarioOptional.isEmpty()) {
           return Optional.of(repository.save(modelMapper.map(usuarioDTO, Usuario.class)));
        } else {
            if (usuarioOptional.get().getId().equals(usuarioDTO.getId())) {
                usuarioDTO.setDataCadastro(usuarioOptional.get().getDataCadastro());
                return Optional.of(repository.save(modelMapper.map(usuarioDTO, Usuario.class)));
            }
            return usuarioOptional;
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
            return ResponseEntity.ok().body("Usuário removido com sucesso.");
        } else {
            return ResponseEntity.badRequest().body("Erro ao tentar excluir o usuário.");
        }
    }

}
