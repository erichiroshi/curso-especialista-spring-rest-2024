package com.erichiroshi.algafood.domain.service;

import com.erichiroshi.algafood.api.model.dtos.inputs.RestauranteInputDto;
import com.erichiroshi.algafood.domain.exception.CidadeNaoEncontradaException;
import com.erichiroshi.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.erichiroshi.algafood.domain.exception.NegocioException;
import com.erichiroshi.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.erichiroshi.algafood.domain.model.*;
import com.erichiroshi.algafood.domain.repository.RestauranteRepository;
import com.erichiroshi.algafood.mappers.RestauranteMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class RestauranteService {

    private final RestauranteRepository repository;

    private final CozinhaService cozinhaService;

    private final RestauranteMapper mapper;

    private final CidadeService cidadeService;

    private final FormaPagamentoService formaPagamentoService;

    private final UsuarioService usuarioService;

    public RestauranteService(RestauranteRepository repository, CozinhaService cozinhaService, RestauranteMapper mapper, CidadeService cidadeService, FormaPagamentoService formaPagamentoService, UsuarioService usuarioService) {
        this.repository = repository;
        this.cozinhaService = cozinhaService;
        this.mapper = mapper;
        this.cidadeService = cidadeService;
        this.formaPagamentoService = formaPagamentoService;
        this.usuarioService = usuarioService;
    }

    @Transactional(readOnly = true)
    public List<Restaurante> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Restaurante findById(Long restauranteId) {
        return repository.findById(restauranteId)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
    }

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        try {
            Cozinha cozinha = cozinhaService.findById(restaurante.getCozinha().getId());
            Cidade cidade = cidadeService.findById(restaurante.getEndereco().getCidade().getId());

            restaurante.setCozinha(cozinha);
            restaurante.getEndereco().setCidade(cidade);

        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }

        return repository.save(restaurante);
    }

    @Transactional
    public Restaurante atualizar(Long restauranteId, RestauranteInputDto restauranteInputDto) {
        Restaurante restauranteAtual = findById(restauranteId);

        try {
            Cozinha cozinha = cozinhaService.findById(restauranteInputDto.cozinha().id());
            Cidade cidade = cidadeService.findById(restauranteInputDto.endereco().cidade().id());

            restauranteAtual = mapper.update(restauranteInputDto, restauranteAtual);

            restauranteAtual.setCozinha(cozinha);
            restauranteAtual.getEndereco().setCidade(cidade);

        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
        return repository.save(restauranteAtual);
    }

    @Transactional
    public void ativar(Long restauranteId) {
        Restaurante restaurante = findById(restauranteId);
        restaurante.ativar();
    }

    @Transactional
    public void inativar(Long restauranteId) {
        Restaurante restaurante = findById(restauranteId);
        restaurante.inativar();
    }

    @Transactional
    public void ativarVarios(List<Long> restauranteIds) {
        try {
            restauranteIds.forEach(this::ativar);
        } catch (RestauranteNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @Transactional
    public void inativarVarios(List<Long> restauranteIds) {
        try {
            restauranteIds.forEach(this::inativar);
        } catch (RestauranteNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @Transactional
    public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
        Restaurante restaurante = findById(restauranteId);
        FormaPagamento formaPagamento = formaPagamentoService.findById(formaPagamentoId);
        restaurante.adicionarFormaPagamento(formaPagamento);
    }

    @Transactional
    public void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
        Restaurante restaurante = findById(restauranteId);
        FormaPagamento formaPagamento = formaPagamentoService.findById(formaPagamentoId);
        restaurante.removerFormaPagamento(formaPagamento);
    }

    @Transactional
    public void abrir(Long restauranteId) {
        Restaurante restaurante = findById(restauranteId);
        restaurante.abrir();
    }

    @Transactional
    public void fechar(Long restauranteId) {
        Restaurante restaurante = findById(restauranteId);
        restaurante.fechar();
    }

    @Transactional(readOnly = true)
    public Set<Usuario> listarResponsaveis(Long restauranteId) {
        Restaurante restaurante = findById(restauranteId);
        return restaurante.getResponsaveis();
    }

    @Transactional
    public void associarResponsavel(Long restauranteId, Long usuarioId) {
        Restaurante restaurante = findById(restauranteId);
        Usuario usuario = usuarioService.findById(usuarioId);

        restaurante.adicionarResponsavel(usuario);
    }

    @Transactional
    public void desassociarResponsavel(Long restauranteId, Long usuarioId) {
        Restaurante restaurante = findById(restauranteId);
        Usuario usuario = usuarioService.findById(usuarioId);

        restaurante.removerResponsavel(usuario);
    }
}
