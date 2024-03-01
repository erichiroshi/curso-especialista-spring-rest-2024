package com.erichiroshi.algafood.api.model.dtos;

import java.io.Serializable;

/**
 * DTO for {@link com.erichiroshi.algafood.domain.model.Restaurante}
 */
public record RestauranteResumoDto(

        Long id,
        String nome

) implements Serializable {
}