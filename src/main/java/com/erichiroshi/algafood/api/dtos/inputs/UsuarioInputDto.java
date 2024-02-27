package com.erichiroshi.algafood.api.dtos.inputs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link com.erichiroshi.algafood.domain.model.Usuario}
 */
public record UsuarioInputDto(

        @NotBlank
        String nome,

        @Email
        @NotBlank
        String email,

        @NotBlank
        String senha

) implements Serializable {
}