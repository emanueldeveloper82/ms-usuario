package br.com.msusuario.controller;

import br.com.msusuario.dto.UsuarioDTO;
import br.com.msusuario.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/usuario/")
@Api(value = "API de Cadastro de Usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);
    private final UsuarioService service;
    private final ModelMapper modelMapper;

    @GetMapping("/listar")
    @ApiOperation(value = "Retorna uma lista de eleições")
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok().body(service.listarTodos());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Retorna um usuario.")
    public ResponseEntity<?> buscarPorId(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok().body(service.buscarPorId(id));
    }

    @PostMapping("/salvar")
    @ApiOperation(value = "Salvar um usuario")
    public ResponseEntity<?> salvar(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        log.info("Salvar usuario");
        return ResponseEntity.ok().body(service.salvar(usuarioDTO));
    }

    @PutMapping("/atualizar")
    @ApiOperation(value = "Atualizar um usuario")
    public ResponseEntity<?> atualizar(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        log.info("Atualizar usuario");
        return ResponseEntity.ok(service.salvar(usuarioDTO));
    }

    @DeleteMapping("/excluir")
    @ApiOperation(value = "Excluir um usuario")
    public ResponseEntity<?> remover(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(service.remover(usuarioDTO));
    }

}
