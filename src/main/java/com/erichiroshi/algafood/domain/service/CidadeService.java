package com.erichiroshi.algafood.domain.service;

import com.erichiroshi.algafood.api.model.dtos.inputs.CidadeInputDto;
import com.erichiroshi.algafood.domain.exception.CidadeNaoEncontradaException;
import com.erichiroshi.algafood.domain.exception.EntidadeEmUsoException;
import com.erichiroshi.algafood.domain.exception.EstadoNaoEncontradoException;
import com.erichiroshi.algafood.domain.exception.NegocioException;
import com.erichiroshi.algafood.domain.model.Cidade;
import com.erichiroshi.algafood.domain.model.Estado;
import com.erichiroshi.algafood.domain.repository.CidadeRepository;
import com.erichiroshi.algafood.mappers.CidadeMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CidadeService {

    private final CidadeRepository repository;

    private final EstadoService estadoService;

    private final CidadeMapper mapper;

    public CidadeService(CidadeRepository repository, EstadoService estadoService, CidadeMapper mapper) {
        this.repository = repository;
        this.estadoService = estadoService;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<Cidade> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Cidade findById(Long cidadeId) {
        return repository.findById(cidadeId)
                .orElseThrow(() -> new CidadeNaoEncontradaException(cidadeId));
    }

    @Transactional
    public Cidade salvar(Cidade cidade) {
        try {
            Estado estado = estadoService.findById(cidade.getEstado().getId());
            cidade.setEstado(estado);

        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
        return repository.save(cidade);
    }

    @Transactional
    public void excluir(Long cidadeId) {
        findById(cidadeId);

        try {
            repository.deleteById(cidadeId);
            repository.flush();

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Cidade de código %d não pode ser removida, pois está em uso", cidadeId));
        }
    }

    @Transactional
    public Cidade atualizar(Long cidadeId, CidadeInputDto cidadeInputDto) {
        Cidade cidadeAtual = findById(cidadeId);

        try {
            mapper.partialUpdate(cidadeInputDto, cidadeAtual);

            Estado estado = estadoService.findById(cidadeInputDto.estado().id());
            cidadeAtual.setEstado(estado);

        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
        return repository.save(cidadeAtual);
    }

}
