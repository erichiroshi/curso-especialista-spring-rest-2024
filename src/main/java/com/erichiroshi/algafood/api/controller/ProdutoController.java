package com.erichiroshi.algafood.api.controller;

import com.erichiroshi.algafood.api.dtos.ProdutoDto;
import com.erichiroshi.algafood.api.dtos.inputs.ProdutoInputDto;
import com.erichiroshi.algafood.domain.model.Produto;
import com.erichiroshi.algafood.domain.service.ProdutoService;
import com.erichiroshi.algafood.mappers.ProdutoMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class ProdutoController {

    private final ProdutoService service;
    private final ProdutoMapper mapper;

    public ProdutoController(ProdutoService service, ProdutoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDto>> listar(@PathVariable Long restauranteId) {
        List<Produto> list = service.findAll(restauranteId);

        return ResponseEntity.ok(list.stream().map(mapper::toDto).toList());
    }

    @GetMapping("/{produtoId}")
    public ResponseEntity<ProdutoDto> buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        Produto produto = service.findById(restauranteId, produtoId);

        return ResponseEntity.ok(mapper.toDto(produto));
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> adicionar(@PathVariable Long restauranteId, @Valid @RequestBody ProdutoInputDto produtoInputDto) {
        Produto produto = mapper.toEntity(produtoInputDto);
        produto = service.salvar(restauranteId, produto);

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(produto));
    }

    @PutMapping("/{produtoId}")
    public ResponseEntity<ProdutoDto> atualizar(
            @PathVariable Long restauranteId, @PathVariable Long produtoId, @Valid @RequestBody ProdutoInputDto produtoInputDto) {

        Produto produtoUpdate = service.atualizar(restauranteId, produtoId, produtoInputDto);

        return ResponseEntity.ok(mapper.toDto(produtoUpdate));
    }

}
