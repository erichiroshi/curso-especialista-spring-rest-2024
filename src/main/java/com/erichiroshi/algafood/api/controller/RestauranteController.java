package com.erichiroshi.algafood.api.controller;

import com.erichiroshi.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.erichiroshi.algafood.domain.model.Restaurante;
import com.erichiroshi.algafood.domain.repository.RestauranteRepository;
import com.erichiroshi.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final CadastroRestauranteService service;

    private final RestauranteRepository repository;

    @Autowired
    public RestauranteController(CadastroRestauranteService service, RestauranteRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Restaurante>> listar() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> buscarId(@PathVariable Long restauranteId) {
        try {
            Restaurante restaurante = service.findById(restauranteId);
            return ResponseEntity.ok(restaurante);

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Restaurante restaurante) {
        try {
            restaurante = service.insert(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<?> atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {
        try {
            restaurante = service.update(restauranteId, restaurante);
            return ResponseEntity.ok(restaurante);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
