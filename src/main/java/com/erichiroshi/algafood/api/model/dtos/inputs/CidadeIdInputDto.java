package com.erichiroshi.algafood.api.model.dtos.inputs;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.erichiroshi.algafood.domain.model.Cidade}
 */
public record CidadeIdInputDto(

        @NotNull
        Long id

) implements Serializable {
}