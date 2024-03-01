package com.erichiroshi.algafood.api.model.dtos;

import com.erichiroshi.algafood.api.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.erichiroshi.algafood.domain.model.Restaurante}
 */
public record RestauranteDto(

        @JsonView({ RestauranteView.Resumo.class, RestauranteView.ApenasNome.class })
        Long id,

        @JsonView({ RestauranteView.Resumo.class, RestauranteView.ApenasNome.class })
        String nome,

        @JsonView(RestauranteView.Resumo.class)
        BigDecimal taxaFrete,

        @JsonView(RestauranteView.Resumo.class)
        CozinhaDto cozinha,
        Boolean ativo,
        Boolean aberto,
        EnderecoDto endereco

) implements Serializable {
}