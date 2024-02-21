package com.erichiroshi.algafood.api.controller;

import com.erichiroshi.algafood.domain.model.Cozinha;
import com.erichiroshi.algafood.domain.repository.CozinhaRepository;
import com.erichiroshi.algafood.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    private final CozinhaRepository repository;
    private final CadastroCozinhaService service;

    public CozinhaController(CozinhaRepository repository, CadastroCozinhaService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Cozinha>> listar() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscarId(@PathVariable Long cozinhaId) {
        Optional<Cozinha> optionalCozinha = repository.findById(cozinhaId);
        if (optionalCozinha.isPresent())
            return ResponseEntity.ok(optionalCozinha.get());
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
        return ResponseEntity.created(null).body(service.salvar(cozinha));
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
        Optional<Cozinha> optionalCozinha = repository.findById(cozinhaId);

        if (optionalCozinha.isPresent()) {
            Cozinha cozinhaAtual = optionalCozinha.get();
            BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
            cozinhaAtual = repository.save(cozinhaAtual);
            return ResponseEntity.ok(cozinhaAtual);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<Void> remover(@PathVariable Long cozinhaId) {
        try {
            Optional<Cozinha> optionalCozinha = repository.findById(cozinhaId);
            if (optionalCozinha.isPresent()) {
                repository.deleteById(cozinhaId);
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.notFound().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
