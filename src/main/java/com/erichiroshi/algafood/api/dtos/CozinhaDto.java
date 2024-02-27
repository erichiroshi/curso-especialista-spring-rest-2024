package com.erichiroshi.algafood.api.dtos;

import com.erichiroshi.algafood.core.validation.Groups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.erichiroshi.algafood.domain.model.Cozinha}
 */
public record CozinhaDto(
        @NotNull(groups = Groups.CozinhaId.class)
        Long id,

        @NotBlank
        String nome

) implements Serializable {
}