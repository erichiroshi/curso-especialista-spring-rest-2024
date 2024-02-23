package com.erichiroshi.algafood.api.controller;

import com.erichiroshi.algafood.domain.model.Cidade;
import com.erichiroshi.algafood.domain.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    private final CidadeService service;

    @Autowired
    public CidadeController(CidadeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Cidade>> listar() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{cidadeId}")
    public ResponseEntity<?> buscarId(@PathVariable Long cidadeId) {
        return ResponseEntity.ok(service.findById(cidadeId));
    }

    @PostMapping
    public ResponseEntity<Cidade> adicionar(@RequestBody Cidade cidade) {
        cidade = service.salvar(cidade);
        return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
    }

    @PutMapping("/{cidadeId}")
    public ResponseEntity<Cidade> atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {
        Cidade cidadeUpdate = service.atualizar(cidadeId, cidade);
        return ResponseEntity.ok(cidadeUpdate);
    }

    @DeleteMapping("/{cidadeId}")
    public ResponseEntity<Void> remover(@PathVariable Long cidadeId) {
        service.excluir(cidadeId);
        return ResponseEntity.noContent().build();
    }
}