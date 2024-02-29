package com.erichiroshi.algafood.api.dtos.inputs;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.erichiroshi.algafood.domain.model.Restaurante}
 */
public record RestauranteIdInputDto(

        @NotNull
        Long id

) implements Serializable {
}