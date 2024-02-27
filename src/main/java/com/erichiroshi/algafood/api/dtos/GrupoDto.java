package com.erichiroshi.algafood.api.dtos;

import java.io.Serializable;

/**
 * DTO for {@link com.erichiroshi.algafood.domain.model.Grupo}
 */
public record GrupoDto(

        Long id,
        String nome

) implements Serializable {
}