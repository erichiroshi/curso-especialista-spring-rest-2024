package com.erichiroshi.algafood.api.model.dtos.inputs;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.erichiroshi.algafood.domain.model.Cozinha}
 */
public record CozinhaIdInputDto(

        @NotNull
        Long id

) implements Serializable {
}