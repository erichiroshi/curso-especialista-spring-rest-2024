package com.erichiroshi.algafood.api.model.dtos;

import java.io.Serializable;

public record FotoProdutoDto(
		
		String nomeArquivo, 
		String descricao,
		String contentType,
		Long tamanho

) implements Serializable {
}