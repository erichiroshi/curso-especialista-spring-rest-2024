package com.erichiroshi.algafood.api.dtos.inputs;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.erichiroshi.algafood.domain.model.Restaurante}
 */
public record RestauranteInputDto(
        @NotBlank
        String nome,

        @NotNull
        @PositiveOrZero
        BigDecimal taxaFrete,

        @Valid
        @NotNull
        CozinhaIdInputDto cozinha

) implements Serializable {
}