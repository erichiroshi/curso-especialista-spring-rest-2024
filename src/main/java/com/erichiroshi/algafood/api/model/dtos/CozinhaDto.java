package com.erichiroshi.algafood.api.model.dtos;

import com.erichiroshi.algafood.api.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;

/**
 * DTO for {@link com.erichiroshi.algafood.domain.model.Cozinha}
 */
public record CozinhaDto(

        @JsonView(RestauranteView.Resumo.class)
        Long id,

        @JsonView(RestauranteView.Resumo.class)
        String nome

) implements Serializable {
}