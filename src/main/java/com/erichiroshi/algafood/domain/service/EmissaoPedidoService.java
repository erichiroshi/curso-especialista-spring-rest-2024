package com.erichiroshi.algafood.domain.service;

import com.erichiroshi.algafood.domain.exception.PedidoNaoEncontradoException;
import com.erichiroshi.algafood.domain.model.Pedido;
import com.erichiroshi.algafood.domain.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmissaoPedidoService {

	private final PedidoRepository repository;

	public EmissaoPedidoService(PedidoRepository repository) {
		this.repository = repository;
	}

	public List<Pedido> listar() {
		return repository.findAll();
	}

	public Pedido buscar(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new PedidoNaoEncontradoException(id));
	}
}
