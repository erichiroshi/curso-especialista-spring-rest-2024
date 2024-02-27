package com.erichiroshi.algafood.api.controller;

import com.erichiroshi.algafood.api.dtos.RestauranteDto;
import com.erichiroshi.algafood.domain.model.Restaurante;
import com.erichiroshi.algafood.domain.service.RestauranteService;
import com.erichiroshi.algafood.mappers.RestauranteMapper;
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
    private final RestauranteMapper restauranteMapper;

    public RestauranteController(RestauranteService service,
                                 RestauranteMapper restauranteMapper) {
        this.service = service;
        this.restauranteMapper = restauranteMapper;
    }

    @GetMapping
    public ResponseEntity<List<RestauranteDto>> listar() {
        List<Restaurante> list = service.findAll();
        return ResponseEntity.ok(list.stream().map(restauranteMapper::toDto).toList());
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<RestauranteDto> buscarId(@PathVariable Long restauranteId) {
        Restaurante restaurante = service.findById(restauranteId);
        return ResponseEntity.ok(restauranteMapper.toDto(restaurante));
    }

    @PostMapping
    public ResponseEntity<RestauranteDto> adicionar(@Valid @RequestBody Restaurante restaurante) {
        restaurante = service.salvar(restaurante);
        return ResponseEntity.status(HttpStatus.CREATED).body(restauranteMapper.toDto(restaurante));
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<RestauranteDto> atualizar(@PathVariable Long restauranteId, @Valid @RequestBody Restaurante restaurante) {
        restaurante = service.atualizar(restauranteId, restaurante);
        return ResponseEntity.ok(restauranteMapper.toDto(restaurante));
    }

    @PatchMapping("/{restauranteId}")
    public ResponseEntity<RestauranteDto> atualizarParcial(
            @PathVariable Long restauranteId, @RequestBody Map<String, Object> campos, HttpServletRequest request) {
        Restaurante updated = service.atualizarParcial(restauranteId, campos, request);
        return ResponseEntity.ok(restauranteMapper.toDto(updated));
    }
}
