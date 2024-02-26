package com.erichiroshi.algafood.domain.service;

import com.erichiroshi.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.erichiroshi.algafood.domain.exception.EntidadeEmUsoException;
import com.erichiroshi.algafood.domain.model.Cozinha;
import com.erichiroshi.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CozinhaService {

    private final CozinhaRepository repository;

    public CozinhaService(CozinhaRepository repository) {
        this.repository = repository;
    }

    public List<Cozinha> findAll() {
        return repository.findAll();
    }

    public Cozinha findById(Long cozinhaId) {
        return repository.findById(cozinhaId)
                .orElseThrow(() -> new CozinhaNaoEncontradaException(cozinhaId));
    }

    public Cozinha salvar(Cozinha cozinha) {
        return repository.save(cozinha);
    }

    public void excluir(Long cozinhaId) {
        findById(cozinhaId);

        try {
            repository.deleteById(cozinhaId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Cozinha de código %d não pode ser removida, pois está em uso", cozinhaId));
        }
    }

    public Cozinha atualizar(Long cozinhaId, Cozinha cozinha) {
        Cozinha cozinhaAtual = findById(cozinhaId);
        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
        return repository.save(cozinhaAtual);
    }

}
