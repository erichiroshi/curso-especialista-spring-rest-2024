package com.erichiroshi.algafood.api.model.dtos.inputs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.erichiroshi.algafood.domain.model.Produto}
 */
public record ProdutoInputDto(

        @NotBlank
        String nome,

        @NotBlank
        String descricao,

        @NotNull
        @PositiveOrZero
        BigDecimal preco,

        @NotNull
        Boolean ativo

) implements Serializable {
}