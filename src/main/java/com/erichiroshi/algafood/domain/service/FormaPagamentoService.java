package com.erichiroshi.algafood.domain.service;

import com.erichiroshi.algafood.api.dtos.inputs.FormaPagamentoInputDto;
import com.erichiroshi.algafood.domain.exception.EntidadeEmUsoException;
import com.erichiroshi.algafood.domain.exception.FormaPagamentoNaoEncontradaException;
import com.erichiroshi.algafood.domain.model.FormaPagamento;
import com.erichiroshi.algafood.domain.repository.FormaPagamentoRepository;
import com.erichiroshi.algafood.mappers.FormaPagamentoMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FormaPagamentoService {

    private static final String MSG_FORMA_PAGAMENTO_EM_USO
            = "Forma de pagamento de código %d não pode ser removida, pois está em uso";

    private final FormaPagamentoRepository repository;

    private final FormaPagamentoMapper mapper;

    public FormaPagamentoService(FormaPagamentoRepository repository, FormaPagamentoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public FormaPagamento findById(Long formaPagamentoId) {
        return repository.findById(formaPagamentoId)
                .orElseThrow(() -> new FormaPagamentoNaoEncontradaException(formaPagamentoId));
    }

    @Transactional
    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return repository.save(formaPagamento);
    }

    @Transactional
    public void excluir(Long formaPagamentoId) {
        findById(formaPagamentoId);

        try {
            repository.deleteById(formaPagamentoId);
            repository.flush();

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_FORMA_PAGAMENTO_EM_USO, formaPagamentoId));
        }
    }

    @Transactional
    public FormaPagamento atualizar(Long formaPagamentoId, FormaPagamentoInputDto formaPagamentoInputDto) {
        FormaPagamento formaPagamentoAtual = findById(formaPagamentoId);
        formaPagamentoAtual = mapper.partialUpdate(formaPagamentoInputDto, formaPagamentoAtual);

        return repository.save(formaPagamentoAtual);
    }

}