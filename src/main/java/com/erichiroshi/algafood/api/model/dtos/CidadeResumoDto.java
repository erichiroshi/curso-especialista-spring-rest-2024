package com.erichiroshi.algafood.api.model.dtos;

import java.io.Serializable;

/**
 * DTO for {@link com.erichiroshi.algafood.domain.model.Cidade}
 */
public record CidadeResumoDto(

        Long id,
        String nome,
        String estado

) implements Serializable {
}