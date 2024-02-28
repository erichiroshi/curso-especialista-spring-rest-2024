package com.erichiroshi.algafood.api.dtos;

import java.io.Serializable;

/**
 * DTO for {@link com.erichiroshi.algafood.domain.model.Permissao}
 */
public record PermissaoDto(

        Long id,
        String nome,
        String descricao

) implements Serializable {
}