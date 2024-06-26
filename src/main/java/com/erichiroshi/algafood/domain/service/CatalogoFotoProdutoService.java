package com.erichiroshi.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erichiroshi.algafood.domain.model.FotoProduto;
import com.erichiroshi.algafood.domain.repository.ProdutoRepository;

@Service
public class CatalogoFotoProdutoService {

	@Autowired
	private ProdutoRepository repository;

	@Transactional
	public FotoProduto salvar(FotoProduto foto) {
		return repository.save(foto);
	}
}
