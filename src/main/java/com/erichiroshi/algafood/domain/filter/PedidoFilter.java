package com.erichiroshi.algafood.domain.filter;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Setter
@Getter
public class PedidoFilter {

	private Long clienteId;
	private Long restauranteId;

	private OffsetDateTime dataCriacaoInicio;

	private OffsetDateTime dataCriacaoFim;

}
