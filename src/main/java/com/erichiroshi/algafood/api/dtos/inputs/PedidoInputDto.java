package com.erichiroshi.algafood.api.dtos.inputs;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.erichiroshi.algafood.domain.model.Pedido}
 */
public record PedidoInputDto(

        @Valid
        @NotNull
        RestauranteIdInputDto restaurante,

        @Valid
        @NotNull
        EnderecoInputDto enderecoEntrega,

        @Valid
        @NotNull
        FormaPagamentoIdInputDto formaPagamento,

        @Valid
        @Size(min = 1)
        @NotNull
        List<ItemPedidoInputDto> itens


) implements Serializable {
}