package com.erichiroshi.algafood.domain.service;

import com.erichiroshi.algafood.api.dtos.inputs.ProdutoInputDto;
import com.erichiroshi.algafood.domain.exception.ProdutoNaoEncontradoException;
import com.erichiroshi.algafood.domain.model.Produto;
import com.erichiroshi.algafood.domain.model.Restaurante;
import com.erichiroshi.algafood.domain.repository.ProdutoRepository;
import com.erichiroshi.algafood.mappers.ProdutoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    private final RestauranteService restauranteService;

    private final ProdutoMapper mapper;

    public ProdutoService(ProdutoRepository repository, RestauranteService restauranteService, ProdutoMapper mapper) {
        this.repository = repository;
        this.restauranteService = restauranteService;
        this.mapper = mapper;
    }

    private Produto findById(Long restauranteId, Long produtoId) {
        return repository.findById(restauranteId, produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(restauranteId, produtoId));
    }

    @Transactional(readOnly = true)
    public List<Produto> listar(Long restauranteId) {
        Restaurante restaurante = restauranteService.findById(restauranteId);

        return repository.findByRestaurante(restaurante);
    }

    @Transactional(readOnly = true)
    public Produto buscar(Long restauranteId, Long produtoId) {
        return findById(restauranteId, produtoId);
    }

    @Transactional
    public Produto salvar(Long restauranteId, Produto produto) {
        Restaurante restaurante = restauranteService.findById(restauranteId);

        restaurante.adicionarProduto(produto);
        produto.setRestaurante(restaurante);

        return repository.save(produto);
    }

    @Transactional
    public Produto atualizar(Long restauranteId, Long produtoId, ProdutoInputDto produtoInputDto) {
        Produto produto = findById(restauranteId, produtoId);
        produto = mapper.partialUpdate(produtoInputDto, produto);

        return repository.save(produto);
    }

}
