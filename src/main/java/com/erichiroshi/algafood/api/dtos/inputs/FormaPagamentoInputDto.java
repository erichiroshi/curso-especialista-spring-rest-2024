package com.erichiroshi.algafood.api.dtos.inputs;

import java.io.Serializable;

/**
 * DTO for {@link com.erichiroshi.algafood.domain.model.FormaPagamento}
 */
public record FormaPagamentoInputDto(

        String descricao

) implements Serializable {
}