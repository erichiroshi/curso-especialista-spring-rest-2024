package com.erichiroshi.algafood.domain.service;

import com.erichiroshi.algafood.api.model.dtos.inputs.PedidoInputDto;
import com.erichiroshi.algafood.domain.exception.NegocioException;
import com.erichiroshi.algafood.domain.exception.PedidoNaoEncontradoException;
import com.erichiroshi.algafood.domain.model.*;
import com.erichiroshi.algafood.domain.repository.PedidoRepository;
import com.erichiroshi.algafood.mappers.PedidoMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmissaoPedidoService {

    private final PedidoRepository repository;

    private final PedidoMapper mapper;

    private final CidadeService cidadeService;
    private final UsuarioService usuarioService;
    private final RestauranteService restauranteService;
    private final FormaPagamentoService formaPagamentoService;
    private final ProdutoService produtoService;

    public EmissaoPedidoService(PedidoRepository repository, PedidoMapper mapper, CidadeService cidadeService, UsuarioService usuarioService, RestauranteService restauranteService, FormaPagamentoService formaPagamentoService, ProdutoService produtoService) {
        this.repository = repository;
        this.mapper = mapper;
        this.cidadeService = cidadeService;
        this.usuarioService = usuarioService;
        this.restauranteService = restauranteService;
        this.formaPagamentoService = formaPagamentoService;
        this.produtoService = produtoService;
    }

    @Transactional(readOnly = true)
    public List<Pedido> findAll(Specification<Pedido> pedidoSpecification) {
        return repository.findAll(pedidoSpecification);
    }

    @Transactional(readOnly = true)
    public Pedido findByCodigo(String codigoPedido) {
        return repository.findByCodigo(codigoPedido)
                .orElseThrow(() -> new PedidoNaoEncontradoException(codigoPedido));
    }

    @Transactional
    public Pedido emitir(PedidoInputDto pedidoInputDto) {
        Pedido pedido = mapper.toEntity(pedidoInputDto);
        pedido.setCliente(new Usuario());
        pedido.getCliente().setId(1L);

        validarPedido(pedido);
        validarItens(pedido);

        pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
        pedido.calcularValorTotal();

        return repository.save(pedido);
    }

    private void validarPedido(Pedido pedido) {
        Cidade cidade = cidadeService.findById(pedido.getEnderecoEntrega().getCidade().getId());
        Usuario cliente = usuarioService.findById(pedido.getCliente().getId());
        Restaurante restaurante = restauranteService.findById(pedido.getRestaurante().getId());
        FormaPagamento formaPagamento = formaPagamentoService.findById(pedido.getFormaPagamento().getId());

        pedido.getEnderecoEntrega().setCidade(cidade);
        pedido.setCliente(cliente);
        pedido.setRestaurante(restaurante);
        pedido.setFormaPagamento(formaPagamento);

        if (restaurante.naoAceitaFormaPagamento(formaPagamento)) {
            throw new NegocioException(String.format("Forma de pagamento '%s' não é aceita por esse restaurante.",
                    formaPagamento.getDescricao()));
        }
    }

    private void validarItens(Pedido pedido) {
        pedido.getItens().forEach(item -> {
            Produto produto = produtoService.findById(
                    pedido.getRestaurante().getId(), item.getProduto().getId());

            item.setPedido(pedido);
            item.setProduto(produto);
            item.setPrecoUnitario(produto.getPreco());
        });
    }
}
