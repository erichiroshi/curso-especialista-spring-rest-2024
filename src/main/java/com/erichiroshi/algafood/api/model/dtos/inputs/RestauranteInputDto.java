package com.erichiroshi.algafood.api.model.dtos.inputs;

import com.erichiroshi.algafood.core.validation.ValorZeroIncluiDescricao;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.erichiroshi.algafood.domain.model.Restaurante}
 */
@ValorZeroIncluiDescricao(valorField = "taxaFrete", descricaoField = "nome", descricaoObrigatoria = "Frete Grátis")
public record RestauranteInputDto(

        @NotBlank
        String nome,

        @NotNull
        @PositiveOrZero
        BigDecimal taxaFrete,

        @Valid
        @NotNull
        CozinhaIdInputDto cozinha,

        @Valid
        @NotNull
        EnderecoInputDto endereco

) implements Serializable {
}