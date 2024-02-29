package com.erichiroshi.algafood.api.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.erichiroshi.algafood.domain.model.ItemPedido}
 */
public record ItemPedidoDto(

        Long produtoId,
        String produtoNome,
        Integer quantidade,
        BigDecimal precoUnitario,
        BigDecimal precoTotal,
        String observacao

) implements Serializable {
}