package com.erichiroshi.algafood.domain.service;

import com.erichiroshi.algafood.domain.exception.PermissaoNaoEncontradaException;
import com.erichiroshi.algafood.domain.model.Permissao;
import com.erichiroshi.algafood.domain.repository.PermissaoRepository;
import org.springframework.stereotype.Service;

@Service
public class PermissaoService {

    private final PermissaoRepository repository;

    public PermissaoService(PermissaoRepository repository) {
        this.repository = repository;
    }

    protected Permissao findById(Long permissaoId) {
        return repository.findById(permissaoId)
                .orElseThrow(() -> new PermissaoNaoEncontradaException(permissaoId));
    }

}