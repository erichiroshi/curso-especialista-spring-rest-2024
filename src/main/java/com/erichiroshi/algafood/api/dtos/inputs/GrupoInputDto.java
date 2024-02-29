package com.erichiroshi.algafood.api.dtos.inputs;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link com.erichiroshi.algafood.domain.model.Grupo}
 */
public record GrupoInputDto(

        @NotBlank
        String nome

) implements Serializable {
}