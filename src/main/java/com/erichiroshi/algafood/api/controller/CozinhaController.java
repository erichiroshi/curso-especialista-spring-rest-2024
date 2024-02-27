package com.erichiroshi.algafood.api.controller;

import com.erichiroshi.algafood.api.dtos.CozinhaDto;
import com.erichiroshi.algafood.api.dtos.inputs.CozinhaInputDto;
import com.erichiroshi.algafood.domain.model.Cozinha;
import com.erichiroshi.algafood.domain.service.CozinhaService;
import com.erichiroshi.algafood.mappers.CozinhaMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    private final CozinhaService service;
    private final CozinhaMapper mapper;

    public CozinhaController(CozinhaService service, CozinhaMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<CozinhaDto>> listar() {
        List<Cozinha> list = service.findAll();

        return ResponseEntity.ok(list.stream().map(mapper::toDto).toList());
    }

    @GetMapping("/{cozinhaId}")
    public ResponseEntity<CozinhaDto> buscarId(@PathVariable Long cozinhaId) {
        Cozinha cozinha = service.findById(cozinhaId);

        return ResponseEntity.ok(mapper.toDto(cozinha));
    }

    @PostMapping
    public ResponseEntity<CozinhaDto> adicionar(@Valid @RequestBody CozinhaInputDto cozinhaInputDto) {
        Cozinha cozinha = mapper.toEntity(cozinhaInputDto);
        cozinha = service.salvar(cozinha);

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(cozinha));
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<CozinhaDto> atualizar(@PathVariable Long cozinhaId, @Valid @RequestBody CozinhaInputDto cozinhaInputDto) {
        Cozinha cozinhaUpdate = service.atualizar(cozinhaId, cozinhaInputDto);

        return ResponseEntity.ok(mapper.toDto(cozinhaUpdate));
    }

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<Void> remover(@PathVariable Long cozinhaId) {
        service.excluir(cozinhaId);

        return ResponseEntity.noContent().build();
    }
}
