package com.erichiroshi.algafood.domain.service;

import com.erichiroshi.algafood.domain.exception.EntidadeEmUsoExecption;
import com.erichiroshi.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.erichiroshi.algafood.domain.model.Cozinha;
import com.erichiroshi.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroCozinhaService {

    private final CozinhaRepository repository;

    public CadastroCozinhaService(CozinhaRepository repository) {
        this.repository = repository;
    }

    public List<Cozinha> findAll() {
        return repository.findAll();
    }

    public Cozinha findById(Long cozinhaId) {
        return repository.findById(cozinhaId).orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format("Não existe um cadastro de cozinha com código %d", cozinhaId)));
    }

    public Cozinha salvar(Cozinha cozinha) {
        return repository.save(cozinha);
    }

    public void excluir(Long cozinhaId) {
        Optional<Cozinha> optionalCozinha = repository.findById(cozinhaId);
        if (optionalCozinha.isEmpty()) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de cozinha com código %d", cozinhaId));
        }

        try {
            repository.deleteById(cozinhaId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoExecption(
                    String.format("Cozinha de código %d não pode ser removida, pois está em uso", cozinhaId));
        }
    }

    public Cozinha update(Long cozinhaId, Cozinha cozinha) {
        Cozinha cozinhaAtual = findById(cozinhaId);
        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
        return repository.save(cozinhaAtual);
    }

}
