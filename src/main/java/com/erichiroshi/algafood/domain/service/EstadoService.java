package com.erichiroshi.algafood.domain.service;

import com.erichiroshi.algafood.api.dtos.inputs.EstadoInputDto;
import com.erichiroshi.algafood.domain.exception.EntidadeEmUsoException;
import com.erichiroshi.algafood.domain.exception.EstadoNaoEncontradoException;
import com.erichiroshi.algafood.domain.model.Estado;
import com.erichiroshi.algafood.domain.repository.EstadoRepository;
import com.erichiroshi.algafood.mappers.EstadoMapper;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EstadoService {

    private final EstadoRepository repository;
    private EstadoMapper mapper;

    public EstadoService(EstadoRepository repository, EstadoMapper estadoMapper) {
        this.repository = repository;
        this.mapper = estadoMapper;
    }

    @Transactional(readOnly = true)
    public List<Estado> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Estado findById(Long estadoId) {
        return repository.findById(estadoId)
                .orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));
    }

    @Transactional
    public Estado salvar(Estado estado) {
        return repository.save(estado);
    }

    @Transactional
    public void excluir(Long estadoId) {
        findById(estadoId);

        try {
            repository.deleteById(estadoId);
            repository.flush();

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Estado de código %d não pode ser removida, pois está em uso", estadoId));
        }
    }

    @Transactional
    public Estado atualizar(Long estadoId, @Valid EstadoInputDto estadoInputDto) {
        Estado estadoAtual = findById(estadoId);
        estadoAtual = mapper.partialUpdate(estadoInputDto, estadoAtual);

        return repository.save(estadoAtual);
    }

}
