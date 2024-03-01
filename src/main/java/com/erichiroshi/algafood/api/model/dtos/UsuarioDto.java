package com.erichiroshi.algafood.api.model.dtos;

import java.io.Serializable;

/**
 * DTO for {@link com.erichiroshi.algafood.domain.model.Usuario}
 */
public record UsuarioDto(

        Long id,
        String nome,
        String email

) implements Serializable {
}