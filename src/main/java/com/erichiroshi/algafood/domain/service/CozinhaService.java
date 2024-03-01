package com.erichiroshi.algafood.domain.service;

import com.erichiroshi.algafood.api.model.dtos.inputs.CozinhaInputDto;
import com.erichiroshi.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.erichiroshi.algafood.domain.exception.EntidadeEmUsoException;
import com.erichiroshi.algafood.domain.model.Cozinha;
import com.erichiroshi.algafood.domain.repository.CozinhaRepository;
import com.erichiroshi.algafood.mappers.CozinhaMapper;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CozinhaService {

    private final CozinhaRepository repository;

    private final CozinhaMapper mapper;

    public CozinhaService(CozinhaRepository repository, CozinhaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
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
    public Cozinha atualizar(Long cozinhaId, @Valid CozinhaInputDto cozinhaInputDto) {
        Cozinha cozinhaAtual = findById(cozinhaId);
        cozinhaAtual = mapper.partialUpdate(cozinhaInputDto, cozinhaAtual);

        return repository.save(cozinhaAtual);
    }

}
