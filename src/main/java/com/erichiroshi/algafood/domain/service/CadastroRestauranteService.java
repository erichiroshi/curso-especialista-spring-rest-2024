package com.erichiroshi.algafood.domain.service;

import com.erichiroshi.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.erichiroshi.algafood.domain.model.Restaurante;
import com.erichiroshi.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroRestauranteService {

    private final RestauranteRepository repository;

    @Autowired
    public CadastroRestauranteService(RestauranteRepository repository) {
        this.repository = repository;
    }

    public List<Restaurante> findAll() {
        return repository.findAll();
    }

    public Restaurante findById(Long restauranteId) {
        return repository.findById(restauranteId).orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format("Não existe um cadastro de restaurante com código %d", restauranteId)));
    }

}
