package com.erichiroshi.algafood.api.model.dtos;

import java.io.Serializable;

/**
 * DTO for {@link com.erichiroshi.algafood.domain.model.Endereco}
 */
public record EnderecoDto(

        String cep,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        CidadeResumoDto cidade

) implements Serializable {
}