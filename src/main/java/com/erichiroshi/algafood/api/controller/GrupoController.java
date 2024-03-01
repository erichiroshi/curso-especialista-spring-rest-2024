package com.erichiroshi.algafood.api.controller;

import com.erichiroshi.algafood.api.model.dtos.GrupoDto;
import com.erichiroshi.algafood.api.model.dtos.inputs.GrupoInputDto;
import com.erichiroshi.algafood.domain.model.Grupo;
import com.erichiroshi.algafood.domain.service.GrupoService;
import com.erichiroshi.algafood.mappers.GrupoMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    private final GrupoService service;
    private final GrupoMapper mapper;

    public GrupoController(GrupoService service, GrupoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<GrupoDto>> listar() {
        List<Grupo> list = service.findAll();

        return ResponseEntity.ok(list.stream().map(mapper::toDto).toList());
    }

    @GetMapping("/{grupoId}")
    public ResponseEntity<GrupoDto> buscarId(@PathVariable Long grupoId) {
        Grupo grupo = service.findById(grupoId);

        return ResponseEntity.ok(mapper.toDto(grupo));
    }

    @PostMapping
    public ResponseEntity<GrupoDto> adicionar(@Valid @RequestBody GrupoInputDto grupoInputDto) {
        Grupo grupo = mapper.toEntity(grupoInputDto);
        grupo = service.salvar(grupo);

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(grupo));
    }

    @PutMapping("/{grupoId}")
    public ResponseEntity<GrupoDto> atualizar(@PathVariable Long grupoId, @Valid @RequestBody GrupoInputDto grupoInputDto) {
        Grupo grupoUpdate = service.atualizar(grupoId, grupoInputDto);

        return ResponseEntity.ok(mapper.toDto(grupoUpdate));
    }

    @DeleteMapping("/{grupoId}")
    public ResponseEntity<Void> remover(@PathVariable Long grupoId) {
        service.excluir(grupoId);

        return ResponseEntity.noContent().build();
    }
}
