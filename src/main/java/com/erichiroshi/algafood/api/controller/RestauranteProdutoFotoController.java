package com.erichiroshi.algafood.api.controller;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.erichiroshi.algafood.api.model.dtos.FotoProdutoDto;
import com.erichiroshi.algafood.api.model.dtos.inputs.FotoProdutoInput;
import com.erichiroshi.algafood.domain.model.FotoProduto;
import com.erichiroshi.algafood.domain.model.Produto;
import com.erichiroshi.algafood.domain.service.CatalogoFotoProdutoService;
import com.erichiroshi.algafood.domain.service.ProdutoService;
import com.erichiroshi.algafood.mappers.FotoProdutoMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

	private ProdutoService produtoService;
	private CatalogoFotoProdutoService catalogoFotoProdutoService;	
	private FotoProdutoMapper fotoProdutoMapper;

	public RestauranteProdutoFotoController(ProdutoService produtoService, CatalogoFotoProdutoService catalogoFotoProdutoService,
			FotoProdutoMapper fotoProdutoMapper) {
		this.produtoService = produtoService;
		this.catalogoFotoProdutoService = catalogoFotoProdutoService;
		this.fotoProdutoMapper = fotoProdutoMapper;
	}


	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoDto atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId,
			@Valid FotoProdutoInput fotoProdutoInput) throws IOException {

		Produto produto = produtoService.findById(restauranteId, produtoId);
		
		MultipartFile arquivo = fotoProdutoInput.getArquivo();

		FotoProduto foto = new FotoProduto();
		foto.setProduto(produto);
		foto.setDescricao(fotoProdutoInput.getDescricao());
		foto.setContentType(arquivo.getContentType());
		foto.setTamanho(arquivo.getSize());
		foto.setNomeArquivo(arquivo.getOriginalFilename());

		FotoProduto fotoSalva = catalogoFotoProdutoService.salvar(foto, arquivo.getInputStream());
		
		return fotoProdutoMapper.toDto(fotoSalva);

	}
}
