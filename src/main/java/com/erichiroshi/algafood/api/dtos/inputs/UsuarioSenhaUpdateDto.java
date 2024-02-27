package com.erichiroshi.algafood.api.dtos.inputs;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link com.erichiroshi.algafood.domain.model.Usuario}
 */
public record UsuarioSenhaUpdateDto(

        @NotBlank
        String senhaAtual,

        @NotBlank
        String novaSenha

) implements Serializable {
}