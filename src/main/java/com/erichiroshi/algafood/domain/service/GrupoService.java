package com.erichiroshi.algafood.domain.service;

import com.erichiroshi.algafood.api.dtos.inputs.GrupoInputDto;
import com.erichiroshi.algafood.domain.exception.EntidadeEmUsoException;
import com.erichiroshi.algafood.domain.exception.GrupoNaoEncontradoException;
import com.erichiroshi.algafood.domain.model.Grupo;
import com.erichiroshi.algafood.domain.model.Permissao;
import com.erichiroshi.algafood.domain.repository.GrupoRepository;
import com.erichiroshi.algafood.mappers.GrupoMapper;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class GrupoService {

    private final GrupoRepository repository;

    private final GrupoMapper mapper;

    private final PermissaoService permissaoService;

    public GrupoService(GrupoRepository repository, GrupoMapper mapper, PermissaoService permissaoService) {
        this.repository = repository;
        this.mapper = mapper;
        this.permissaoService = permissaoService;
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

    @Transactional(readOnly = true)
    public Set<Permissao> listar(Long grupoId) {
        Grupo grupo = findById(grupoId);

        return grupo.getPermissoes();
    }

    @Transactional
    public void associarPermissao(Long grupoId, Long permissaoId) {
        Grupo grupo = findById(grupoId);
        Permissao permissao = permissaoService.findById(permissaoId);

        grupo.adicionarPermissao(permissao);
    }

    @Transactional
    public void desassociarPermissao(Long grupoId, Long permissaoId) {
        Grupo grupo = findById(grupoId);
        Permissao permissao = permissaoService.findById(permissaoId);

        grupo.removerPermissao(permissao);
    }

}
