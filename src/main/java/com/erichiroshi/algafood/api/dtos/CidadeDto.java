package com.erichiroshi.algafood.api.dtos;

import java.io.Serializable;

/**
 * DTO for {@link com.erichiroshi.algafood.domain.model.Cidade}
 */
public record CidadeDto(

        Long id,
        String nome,
        EstadoDto estado

) implements Serializable {
}