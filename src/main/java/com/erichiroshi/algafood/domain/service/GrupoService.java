package com.erichiroshi.algafood.domain.service;

import com.erichiroshi.algafood.api.dtos.inputs.GrupoInputDto;
import com.erichiroshi.algafood.domain.exception.GrupoNaoEncontradoException;
import com.erichiroshi.algafood.domain.exception.EntidadeEmUsoException;
import com.erichiroshi.algafood.domain.model.Grupo;
import com.erichiroshi.algafood.domain.repository.GrupoRepository;
import com.erichiroshi.algafood.mappers.GrupoMapper;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GrupoService {

    private final GrupoRepository repository;

    private final GrupoMapper mapper;

    public GrupoService(GrupoRepository repository, GrupoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<Grupo> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Grupo findById(Long grupoId) {
        return repository.findById(grupoId)
                .orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));
    }

    @Transactional
    public Grupo salvar(Grupo grupo) {
        return repository.save(grupo);
    }

    @Transactional
    public void excluir(Long grupoId) {
        findById(grupoId);

        try {
            repository.deleteById(grupoId);
            repository.flush();

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Grupo de código %d não pode ser removida, pois está em uso", grupoId));
        }
    }

    @Transactional
    public Grupo atualizar(Long grupoId, @Valid GrupoInputDto grupoInputDto) {
        Grupo grupoAtual = findById(grupoId);
        grupoAtual = mapper.partialUpdate(grupoInputDto, grupoAtual);

        return repository.save(grupoAtual);
    }

}
