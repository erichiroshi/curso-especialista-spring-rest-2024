package com.erichiroshi.algafood.domain.service;

import com.erichiroshi.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.erichiroshi.algafood.domain.exception.EntidadeEmUsoException;
import com.erichiroshi.algafood.domain.model.Cozinha;
import com.erichiroshi.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CozinhaService {

    private final CozinhaRepository repository;

    public CozinhaService(CozinhaRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Cozinha> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Cozinha findById(Long cozinhaId) {
        return repository.findById(cozinhaId)
                .orElseThrow(() -> new CozinhaNaoEncontradaException(cozinhaId));
    }

    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return repository.save(cozinha);
    }

    @Transactional
    public void excluir(Long cozinhaId) {
        findById(cozinhaId);

        try {
            repository.deleteById(cozinhaId);
            repository.flush();

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Cozinha de código %d não pode ser removida, pois está em uso", cozinhaId));
        }
    }

    @Transactional
    public Cozinha atualizar(Long cozinhaId, Cozinha cozinha) {
        Cozinha cozinhaAtual = findById(cozinhaId);
        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
        return repository.save(cozinhaAtual);
    }

}
