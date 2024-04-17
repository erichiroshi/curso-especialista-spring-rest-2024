package com.erichiroshi.algafood.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
@Entity
public class FotoProduto {

	@Id
	@Column(name = "produto_id")
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Produto produto;

	private String nomeArquivo;
	private String descricao;
	private String contentType;
	private Long tamanho;

}