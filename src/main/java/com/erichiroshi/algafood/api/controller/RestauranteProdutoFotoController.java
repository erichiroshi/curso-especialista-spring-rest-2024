package com.erichiroshi.algafood.api.controller;

import com.erichiroshi.algafood.api.model.dtos.inputs.FotoProdutoInput;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId, @Valid FotoProdutoInput foto) {

        var nomeArquivo = UUID.randomUUID().toString() + "_" + foto.getArquivo().getOriginalFilename();

        var arquivoFoto = Path.of("C:\\temp\\ws-sts\\algafood-api\\catalago", nomeArquivo);

        System.out.println(arquivoFoto);
        System.out.println(foto.getArquivo().getContentType());
        System.out.println(foto.getDescricao());

        try {
            foto.getArquivo().transferTo(arquivoFoto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
