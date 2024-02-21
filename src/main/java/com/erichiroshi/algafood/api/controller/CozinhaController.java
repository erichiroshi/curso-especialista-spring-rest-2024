package com.erichiroshi.algafood.api.controller;

import com.erichiroshi.algafood.domain.model.Cozinha;
import com.erichiroshi.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        if (optionalCozinha.isPresent())
            return ResponseEntity.ok(optionalCozinha.get());
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
        return ResponseEntity.created(null).body(repository.save(cozinha));
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
}
