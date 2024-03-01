package com.erichiroshi.algafood.api.model.dtos.inputs;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.erichiroshi.algafood.domain.model.Endereco}
 */
public record EnderecoInputDto(

        @NotBlank
        String cep,

        @NotBlank
        String logradouro,

        @NotBlank
        String numero,

        String complemento,

        @NotBlank
        String bairro,

        @Valid
        @NotNull
        CidadeIdInputDto cidade

) implements Serializable {
}