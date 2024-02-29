package com.erichiroshi.algafood.domain.service;

import com.erichiroshi.algafood.domain.exception.PedidoNaoEncontradoException;
import com.erichiroshi.algafood.domain.model.Pedido;
import com.erichiroshi.algafood.domain.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmissaoPedidoService {

	private final PedidoRepository repository;

	public EmissaoPedidoService(PedidoRepository repository) {
		this.repository = repository;
	}

	@Transactional(readOnly = true)
	public List<Pedido> findAll() {
		return repository.findAll();
	}

	@Transactional(readOnly = true)
	public Pedido findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new PedidoNaoEncontradoException(id));
	}
}
