package com.erichiroshi.algafood.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.erichiroshi.algafood.domain.model.Restaurante}
 */
public record RestauranteDto(
        Long id,

        @NotBlank
        String nome,

        @NotNull
        @PositiveOrZero
        BigDecimal taxaFrete,

        @NotNull
        CozinhaDto cozinha

) implements Serializable {
}