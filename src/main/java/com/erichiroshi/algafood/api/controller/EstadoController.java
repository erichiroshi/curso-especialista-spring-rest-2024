package com.erichiroshi.algafood.api.controller;

import com.erichiroshi.algafood.api.model.dtos.EstadoDto;
import com.erichiroshi.algafood.api.model.dtos.inputs.EstadoInputDto;
import com.erichiroshi.algafood.domain.model.Estado;
import com.erichiroshi.algafood.domain.service.EstadoService;
import com.erichiroshi.algafood.mappers.EstadoMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    private final EstadoService service;
    private final EstadoMapper mapper;

    public EstadoController(EstadoService service, EstadoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<EstadoDto>> listar() {
        List<Estado> list = service.findAll();

        return ResponseEntity.ok(list.stream().map(mapper::toDto).toList());
    }

    @GetMapping("/{estadoId}")
    public ResponseEntity<EstadoDto> buscarId(@PathVariable Long estadoId) {
        Estado estado = service.findById(estadoId);

        return ResponseEntity.ok(mapper.toDto(estado));
    }

    @PostMapping
    public ResponseEntity<EstadoDto> adicionar(@Valid @RequestBody EstadoInputDto estadoInputDto) {
        Estado estado = mapper.toEntity(estadoInputDto);
        estado = service.salvar(estado);

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(estado));
    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<EstadoDto> atualizar(@PathVariable Long estadoId, @Valid @RequestBody EstadoInputDto estadoInputDto) {
        Estado update = service.atualizar(estadoId, estadoInputDto);

        return ResponseEntity.ok(mapper.toDto(update));
    }

    @DeleteMapping("/{estadoId}")
    public ResponseEntity<Void> remover(@PathVariable Long estadoId) {
        service.excluir(estadoId);

        return ResponseEntity.noContent().build();
    }
}