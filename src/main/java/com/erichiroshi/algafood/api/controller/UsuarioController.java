package com.erichiroshi.algafood.api.controller;

import com.erichiroshi.algafood.api.model.dtos.UsuarioDto;
import com.erichiroshi.algafood.api.model.dtos.inputs.UsuarioInputDto;
import com.erichiroshi.algafood.api.model.dtos.inputs.UsuarioSenhaUpdateDto;
import com.erichiroshi.algafood.api.model.dtos.inputs.UsuarioUpdateDto;
import com.erichiroshi.algafood.domain.model.Usuario;
import com.erichiroshi.algafood.domain.service.UsuarioService;
import com.erichiroshi.algafood.mappers.UsuarioMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;
    private final UsuarioMapper mapper;

    public UsuarioController(UsuarioService service, UsuarioMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> listar() {
        List<Usuario> list = service.findAll();

        return ResponseEntity.ok(list.stream().map(mapper::toDto).toList());
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<UsuarioDto> buscarId(@PathVariable Long usuarioId) {
        Usuario usuario = service.findById(usuarioId);

        return ResponseEntity.ok(mapper.toDto(usuario));
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> adicionar(@Valid @RequestBody UsuarioInputDto usuarioInputDto) {
        Usuario usuario = mapper.toEntity(usuarioInputDto);
        usuario = service.salvar(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(usuario));
    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity<UsuarioDto> atualizar(@PathVariable Long usuarioId, @Valid @RequestBody UsuarioUpdateDto usuarioUpdateDto) {
        Usuario usuarioUpdate = service.atualizar(usuarioId, usuarioUpdateDto);

        return ResponseEntity.ok(mapper.toDto(usuarioUpdate));
    }

    @PutMapping("/{usuarioId}/senha")
    public ResponseEntity<Void> atualizarSenha(@PathVariable Long usuarioId, @Valid @RequestBody UsuarioSenhaUpdateDto usuarioSenhaUpdateDto) {
        service.atualizarSenha(usuarioId, usuarioSenhaUpdateDto);

        return ResponseEntity.noContent().build();
    }

}
