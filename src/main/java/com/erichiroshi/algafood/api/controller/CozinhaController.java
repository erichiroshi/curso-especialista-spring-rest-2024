package com.erichiroshi.algafood.api.controller;

import com.erichiroshi.algafood.domain.model.Cozinha;
import com.erichiroshi.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository repository;

    @GetMapping
    public ResponseEntity<List<Cozinha>> listar() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscarId(@PathVariable Long cozinhaId) {
        Optional<Cozinha> optionalCozinha = repository.findById(cozinhaId);
        if(optionalCozinha.isPresent())
            return ResponseEntity.ok(optionalCozinha.get());
        return ResponseEntity.notFound().build();
    }
}
