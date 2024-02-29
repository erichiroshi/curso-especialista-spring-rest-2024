package com.erichiroshi.algafood.api.dtos;

import com.erichiroshi.algafood.domain.enums.StatusPedido;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * DTO for {@link com.erichiroshi.algafood.domain.model.Pedido}
 */
public record PedidoDto(

        Long id,
        BigDecimal subtotal,
        BigDecimal taxaFrete,
        BigDecimal valorTotal,
        StatusPedido status,
        OffsetDateTime dataCriacao,
        OffsetDateTime dataConfirmacao,
        OffsetDateTime dataEntrega,
        OffsetDateTime dataCancelamento,
        RestauranteResumoDto restaurante,
        UsuarioDto cliente,
        FormaPagamentoDto formaPagamento,
        EnderecoDto enderecoEntrega,
        List<ItemPedidoDto> itens

) implements Serializable {
}