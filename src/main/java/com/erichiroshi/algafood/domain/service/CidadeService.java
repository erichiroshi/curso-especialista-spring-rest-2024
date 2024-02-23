package com.erichiroshi.algafood.domain.service;

import com.erichiroshi.algafood.domain.exception.EntidadeEmUsoExecption;
import com.erichiroshi.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.erichiroshi.algafood.domain.exception.NegocioException;
import com.erichiroshi.algafood.domain.model.Cidade;
import com.erichiroshi.algafood.domain.model.Estado;
import com.erichiroshi.algafood.domain.repository.CidadeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    private final CidadeRepository repository;

    private final EstadoService estadoService;

    @Autowired
    public CidadeService(CidadeRepository repository, EstadoService estadoService) {
        this.repository = repository;
        this.estadoService = estadoService;
    }

    public List<Cidade> findAll() {
        return repository.findAll();
    }

    public Cidade findById(Long cidadeId) {
        return repository.findById(cidadeId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Não existe um cadastro de cidade com código %d", cidadeId)));
    }

    public Cidade salvar(Cidade cidade) {
        try {
            Estado estado = estadoService.findById(cidade.getEstado().getId());
            cidade.setEstado(estado);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
        return repository.save(cidade);
    }

    public void excluir(Long cidadeId) {
        findById(cidadeId);

        try {
            repository.deleteById(cidadeId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoExecption(
                    String.format("Cidade de código %d não pode ser removida, pois está em uso", cidadeId));
        }
    }

    public Cidade atualizar(Long cidadeId, Cidade cidade) {
        Cidade cidadeAtual = findById(cidadeId);
        BeanUtils.copyProperties(cidade, cidadeAtual, "id");

        try {
            Estado estado = estadoService.findById(cidade.getEstado().getId());
            cidadeAtual.setEstado(estado);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
        return repository.save(cidadeAtual);
    }

}
