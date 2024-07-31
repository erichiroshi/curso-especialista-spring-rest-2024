package com.erichiroshi.algafood.domain.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erichiroshi.algafood.domain.model.FotoProduto;
import com.erichiroshi.algafood.domain.repository.ProdutoRepository;

@Service
public class CatalogoFotoProdutoService {

	private ProdutoRepository produtoRepository;

	public CatalogoFotoProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	@Transactional
	public FotoProduto salvar(FotoProduto foto) {
		Long restauranteId = foto.getRestauranteId();
		Long produtoId = foto.getProduto().getId();

		Optional<FotoProduto> fotoExistente = produtoRepository.findFotoById(restauranteId, produtoId);

		if (fotoExistente.isPresent()) {
			produtoRepository.delete(fotoExistente.get());
		}

		return produtoRepository.save(foto);
	}
}
