package com.erichiroshi.algafood.domain.service;

import com.erichiroshi.algafood.domain.exception.EntidadeEmUsoExecption;
import com.erichiroshi.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.erichiroshi.algafood.domain.model.Estado;
import com.erichiroshi.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    private final EstadoRepository repository;

    @Autowired
    public EstadoService(EstadoRepository repository) {
        this.repository = repository;
    }

    public List<Estado> findAll() {
        return repository.findAll();
    }

    public Estado findById(Long estadoId) {
        return repository.findById(estadoId).orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format("Não existe um cadastro de estado com código %d", estadoId)));
    }

    public Estado salvar(Estado estado) {
        return repository.save(estado);
    }

    public void excluir(Long estadoId) {
        findById(estadoId);

        try {
            repository.deleteById(estadoId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoExecption(
                    String.format("Estado de código %d não pode ser removida, pois está em uso", estadoId));
        }
    }

    public Estado update(Long estadoId, Estado estado) {
        Estado estadoAtual = findById(estadoId);
        BeanUtils.copyProperties(estado, estadoAtual, "id");
        return repository.save(estadoAtual);
    }

}
