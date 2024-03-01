package com.erichiroshi.algafood.domain.service;

import com.erichiroshi.algafood.domain.model.Pedido;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FluxoPedidoService {

    private final EmissaoPedidoService emissaoPedido;

    public FluxoPedidoService(EmissaoPedidoService emissaoPedido) {
        this.emissaoPedido = emissaoPedido;
    }

    @Transactional
    public void confirmar(String codigoPedido) {
        Pedido pedido = emissaoPedido.findByCodigo(codigoPedido);
        pedido.confirmar();
    }

    @Transactional
    public void entregar(String codigoPedido) {
        Pedido pedido = emissaoPedido.findByCodigo(codigoPedido);
        pedido.entregar();
    }

    @Transactional
    public void cancelar(String codigoPedido) {
        Pedido pedido = emissaoPedido.findByCodigo(codigoPedido);
        pedido.cancelar();
    }

}