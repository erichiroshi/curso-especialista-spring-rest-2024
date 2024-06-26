package com.erichiroshi.algafood.api.model.dtos;

import java.io.Serializable;

/**
 * DTO for {@link com.erichiroshi.algafood.domain.model.FormaPagamento}
 */
public record FormaPagamentoDto(

        Long id,
        String descricao

) implements Serializable {
}