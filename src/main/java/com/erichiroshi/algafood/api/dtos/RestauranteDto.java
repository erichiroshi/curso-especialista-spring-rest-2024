package com.erichiroshi.algafood.api.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.erichiroshi.algafood.domain.model.Restaurante}
 */
public record RestauranteDto(

        Long id,
        String nome,
        BigDecimal taxaFrete,
        CozinhaDto cozinha,
        Boolean ativo

) implements Serializable {
}