package com.erichiroshi.algafood.api.controller;

import com.erichiroshi.algafood.domain.exception.EntidadeEmUsoException;
import com.erichiroshi.algafood.domain.exception.EstadoNaoEncontradoException;
import com.erichiroshi.algafood.domain.model.Estado;
import com.erichiroshi.algafood.domain.service.EstadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    private final EstadoService service;

    public EstadoController(EstadoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Estado>> listar() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{estadoId}")
    public ResponseEntity<?> buscarId(@PathVariable Long estadoId) {
        return ResponseEntity.ok(service.findById(estadoId));
    }

    @PostMapping
    public ResponseEntity<Estado> adicionar(@Valid @RequestBody Estado estado) {
        estado = service.salvar(estado);
        return ResponseEntity.status(HttpStatus.CREATED).body(estado);
    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<?> atualizar(@PathVariable Long estadoId, @Valid @RequestBody Estado estado) {
        try {
            Estado update = service.atualizar(estadoId, estado);
            return ResponseEntity.ok(update);
        } catch (EstadoNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{estadoId}")
    public ResponseEntity<?> remover(@PathVariable Long estadoId) {
        try {
            service.excluir(estadoId);
            return ResponseEntity.noContent().build();

        } catch (EstadoNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}