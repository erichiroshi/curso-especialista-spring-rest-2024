package com.erichiroshi.algafood.api.model.dtos.inputs;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.erichiroshi.algafood.core.validation.FileContentType;
import com.erichiroshi.algafood.core.validation.FileSize;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoInput {

	@NotNull
	@FileSize(max = "500KB")
	@FileContentType(allowed = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
	private MultipartFile arquivo;

    @NotBlank
    private String descricao;

}
