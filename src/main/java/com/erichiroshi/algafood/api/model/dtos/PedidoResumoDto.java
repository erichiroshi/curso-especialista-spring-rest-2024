package com.erichiroshi.algafood.api.model.dtos;

import com.erichiroshi.algafood.domain.enums.StatusPedido;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * DTO for {@link com.erichiroshi.algafood.domain.model.Pedido}
 */

public record PedidoResumoDto(

        String codigo,
        BigDecimal subtotal,
        BigDecimal taxaFrete,
        BigDecimal valorTotal,
        StatusPedido status,
        OffsetDateTime dataCriacao,
        RestauranteResumoDto restaurante,
        UsuarioDto cliente

) implements Serializable {
}