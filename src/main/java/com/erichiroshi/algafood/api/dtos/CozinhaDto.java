package com.erichiroshi.algafood.api.dtos;

import java.io.Serializable;

/**
 * DTO for {@link com.erichiroshi.algafood.domain.model.Cozinha}
 */
public record CozinhaDto(

        Long id,
        String nome

) implements Serializable {
}