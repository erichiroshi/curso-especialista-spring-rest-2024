package com.erichiroshi.algafood.api.controller;

import com.erichiroshi.algafood.api.model.dtos.GrupoDto;
import com.erichiroshi.algafood.domain.model.Grupo;
import com.erichiroshi.algafood.domain.service.UsuarioService;
import com.erichiroshi.algafood.mappers.GrupoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {

    private final UsuarioService service;
    private final GrupoMapper grupoMapper;

    public UsuarioGrupoController(UsuarioService service, GrupoMapper grupoMapper) {
        this.service = service;
        this.grupoMapper = grupoMapper;
    }

    @GetMapping
    public ResponseEntity<Set<GrupoDto>> listarGrupos(@PathVariable Long usuarioId) {
        Set<Grupo> list = service.listarGrupos(usuarioId);

        return ResponseEntity.ok(list.stream().map(grupoMapper::toDto).collect(Collectors.toSet()));
    }

    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void adicionarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        service.associarGrupo(usuarioId, grupoId);
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        service.desassociarGrupo(usuarioId, grupoId);
    }

}
