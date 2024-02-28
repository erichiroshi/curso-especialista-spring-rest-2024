package com.erichiroshi.algafood.api.controller;

import com.erichiroshi.algafood.api.dtos.FormaPagamentoDto;
import com.erichiroshi.algafood.domain.model.FormaPagamento;
import com.erichiroshi.algafood.domain.service.RestauranteService;
import com.erichiroshi.algafood.mappers.FormaPagamentoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController {

    private final RestauranteService restauranteService;
    private final FormaPagamentoMapper formaPagamentoMapper;

    public RestauranteFormaPagamentoController(RestauranteService restauranteService, FormaPagamentoMapper formaPagamentoMapper) {
        this.restauranteService = restauranteService;
        this.formaPagamentoMapper = formaPagamentoMapper;
    }

    @GetMapping
    public ResponseEntity<Set<FormaPagamentoDto>> listar(@PathVariable Long restauranteId) {
        Set<FormaPagamento> list = restauranteService.findById(restauranteId).getFormasPagamento();

        return ResponseEntity.ok(list.stream().map(formaPagamentoMapper::toDto).collect(Collectors.toSet()));

    }

    @PutMapping("/{formaPagamentoId}")
    public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
        restauranteService.associarFormaPagamento(restauranteId, formaPagamentoId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{formaPagamentoId}")
    public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
        restauranteService.desassociarFormaPagamento(restauranteId, formaPagamentoId);
        return ResponseEntity.noContent().build();
    }

}
