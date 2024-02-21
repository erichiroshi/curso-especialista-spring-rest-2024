package com.erichiroshi.algafood.domain.service;

import com.erichiroshi.algafood.domain.model.Cozinha;
import com.erichiroshi.algafood.domain.repository.CozinhaRepository;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {

    private final CozinhaRepository repository;

    public CadastroCozinhaService(CozinhaRepository repository) {
        this.repository = repository;
    }

    public Cozinha salvar(Cozinha cozinha) {
        return repository.save(cozinha);
    }


}
