package com.erichiroshi.algafood.api.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.erichiroshi.algafood.domain.model.Produto}
 */
public record ProdutoDto(

        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        Boolean ativo

) implements Serializable {
}