package com.erichiroshi.algafood.api.model.dtos.inputs;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link com.erichiroshi.algafood.domain.model.Estado}
 */
public record EstadoInputDto(

        @NotBlank
        String nome

) implements Serializable {
}