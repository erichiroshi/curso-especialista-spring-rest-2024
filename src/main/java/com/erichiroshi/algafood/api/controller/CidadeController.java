package com.erichiroshi.algafood.api.controller;

import com.erichiroshi.algafood.api.model.dtos.CidadeDto;
import com.erichiroshi.algafood.api.model.dtos.inputs.CidadeInputDto;
import com.erichiroshi.algafood.domain.model.Cidade;
import com.erichiroshi.algafood.domain.service.CidadeService;
import com.erichiroshi.algafood.mappers.CidadeMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    private final CidadeService service;
    private final CidadeMapper mapper;

    public CidadeController(CidadeService service, CidadeMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<CidadeDto>> listar() {
        List<Cidade> list = service.findAll();

        return ResponseEntity.ok(list.stream().map(mapper::toDto).toList());
    }

    @GetMapping("/{cidadeId}")
    public ResponseEntity<CidadeDto> buscarId(@PathVariable Long cidadeId) {
        Cidade cidade = service.findById(cidadeId);

        return ResponseEntity.ok(mapper.toDto(cidade));
    }

    @PostMapping
    public ResponseEntity<CidadeDto> adicionar(@Valid @RequestBody CidadeInputDto cidadeInputDto) {
        Cidade cidade = mapper.toEntity(cidadeInputDto);
        cidade = service.salvar(cidade);

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(cidade));
    }

    @PutMapping("/{cidadeId}")
    public ResponseEntity<CidadeDto> atualizar(@PathVariable Long cidadeId, @Valid @RequestBody CidadeInputDto cidadeInputDto) {
        Cidade cidadeUpdate = service.atualizar(cidadeId, cidadeInputDto);

        return ResponseEntity.ok(mapper.toDto(cidadeUpdate));
    }

    @DeleteMapping("/{cidadeId}")
    public ResponseEntity<Void> remover(@PathVariable Long cidadeId) {
        service.excluir(cidadeId);

        return ResponseEntity.noContent().build();
    }
}