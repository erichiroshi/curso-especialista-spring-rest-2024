package com.erichiroshi.algafood.api.controller;

import com.erichiroshi.algafood.api.dtos.FormaPagamentoDto;
import com.erichiroshi.algafood.api.dtos.inputs.FormaPagamentoInputDto;
import com.erichiroshi.algafood.domain.model.FormaPagamento;
import com.erichiroshi.algafood.domain.repository.FormaPagamentoRepository;
import com.erichiroshi.algafood.domain.service.FormaPagamentoService;
import com.erichiroshi.algafood.mappers.FormaPagamentoMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

    private final FormaPagamentoRepository repository;

    private final FormaPagamentoService service;

    private final FormaPagamentoMapper mapper;

    public FormaPagamentoController(FormaPagamentoRepository repository, FormaPagamentoService service, FormaPagamentoMapper mapper) {
        this.repository = repository;
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<FormaPagamentoDto>> listar() {
        List<FormaPagamento> list = repository.findAll();

        return ResponseEntity.ok(list.stream().map(mapper::toDto).toList());
    }

    @GetMapping("/{formaPagamentoId}")
    public ResponseEntity<FormaPagamentoDto> buscar(@PathVariable Long formaPagamentoId) {
        FormaPagamento formaPagamento = service.findById(formaPagamentoId);

        return ResponseEntity.ok(mapper.toDto(formaPagamento));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FormaPagamentoDto> adicionar(@RequestBody @Valid FormaPagamentoInputDto formaPagamentoInputDto) {
        FormaPagamento formaPagamento = mapper.toEntity(formaPagamentoInputDto);
        formaPagamento = service.salvar(formaPagamento);

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(formaPagamento));
    }

    @PutMapping("/{formaPagamentoId}")
    public ResponseEntity<FormaPagamentoDto> atualizar(@PathVariable Long formaPagamentoId, @RequestBody @Valid FormaPagamentoInputDto formaPagamentoInputDto) {
        FormaPagamento formaPagamentoAtual = service.atualizar(formaPagamentoId, formaPagamentoInputDto);

        return ResponseEntity.ok(mapper.toDto(formaPagamentoAtual));
    }

    @DeleteMapping("/{formaPagamentoId}")
    public ResponseEntity<Void> remover(@PathVariable Long formaPagamentoId) {
        service.excluir(formaPagamentoId);
        return ResponseEntity.noContent().build();
    }
}