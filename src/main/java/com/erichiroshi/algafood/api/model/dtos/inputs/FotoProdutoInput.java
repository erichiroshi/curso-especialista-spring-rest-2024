package com.erichiroshi.algafood.api.model.dtos.inputs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class FotoProdutoInput {

    private MultipartFile arquivo;
    private String descricao;

}
