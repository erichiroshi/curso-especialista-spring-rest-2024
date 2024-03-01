package com.erichiroshi.algafood.api.model.dtos.inputs;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link com.erichiroshi.algafood.domain.model.FormaPagamento}
 */
public record FormaPagamentoInputDto(

        @NotBlank
        String descricao

) implements Serializable {
}