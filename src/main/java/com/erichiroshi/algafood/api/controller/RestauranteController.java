package com.erichiroshi.algafood.api.controller;

import com.erichiroshi.algafood.domain.model.Restaurante;
import com.erichiroshi.algafood.domain.service.RestauranteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final RestauranteService service;

    public RestauranteController(RestauranteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Restaurante>> listar() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> buscarId(@PathVariable Long restauranteId) {
        Restaurante restaurante = service.findById(restauranteId);
        return ResponseEntity.ok(restaurante);
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@Valid @RequestBody Restaurante restaurante) {
        restaurante = service.salvar(restaurante);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<?> atualizar(@PathVariable Long restauranteId, @Valid @RequestBody Restaurante restaurante) {
        restaurante = service.atualizar(restauranteId, restaurante);
        return ResponseEntity.ok(restaurante);
    }

    @PatchMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> atualizarParcial(
            @PathVariable Long restauranteId, @RequestBody Map<String, Object> campos, HttpServletRequest request) {
        Restaurante updated = service.atualizarParcial(restauranteId, campos, request);
        return ResponseEntity.ok(updated);
    }
}
