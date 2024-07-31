package com.erichiroshi.algafood.domain.service;

import java.io.InputStream;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erichiroshi.algafood.domain.model.FotoProduto;
import com.erichiroshi.algafood.domain.repository.ProdutoRepository;
import com.erichiroshi.algafood.domain.service.FotoStorageService.NovaFoto;

@Service
public class CatalogoFotoProdutoService {

	private ProdutoRepository produtoRepository;
	
	private FotoStorageService fotoStorageService;

	public CatalogoFotoProdutoService(ProdutoRepository produtoRepository, FotoStorageService fotoStorageService) {
		this.produtoRepository = produtoRepository;
		this.fotoStorageService = fotoStorageService;
	}

	@Transactional
	public FotoProduto salvar(FotoProduto foto, InputStream dadosArquivo) {
		Long restauranteId = foto.getRestauranteId();
		Long produtoId = foto.getProduto().getId();
		String nomeNovoArquivo = fotoStorageService.gerarNomeArquivo(foto.getNomeArquivo());
		String nomeArquivoExistente = null;
		
		Optional<FotoProduto> fotoExistente = produtoRepository
				.findFotoById(restauranteId, produtoId);
		
		if (fotoExistente.isPresent()) {
			nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
			produtoRepository.delete(fotoExistente.get());
		}
		
		foto.setNomeArquivo(nomeNovoArquivo);
		foto =  produtoRepository.save(foto);
		produtoRepository.flush();
		
		NovaFoto novaFoto = NovaFoto.builder()
				.nomeAquivo(foto.getNomeArquivo())
				.inputStream(dadosArquivo)
				.build();

		fotoStorageService.substituir(nomeArquivoExistente, novaFoto);
		
		return foto;
	}
}
