package com.erichiroshi.algafood.api.dtos.inputs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.io.Serializable;

/**
 * DTO for {@link com.erichiroshi.algafood.domain.model.ItemPedido}
 */
public record ItemPedidoInputDto(

        @NotNull
        Long produtoId,

        @NotNull
        @PositiveOrZero
        Integer quantidade,

        @NotNull
        String observacao

) implements Serializable {
}