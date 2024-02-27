package com.erichiroshi.algafood.api.dtos.inputs;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.erichiroshi.algafood.domain.model.Cidade}
 */
public record CidadeInputDto(

        @NotBlank
        String nome,

        @Valid
        @NotNull
        EstadoIdInputDto estado

) implements Serializable {
}