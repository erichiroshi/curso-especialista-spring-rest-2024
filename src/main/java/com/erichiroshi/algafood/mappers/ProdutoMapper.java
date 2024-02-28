package com.erichiroshi.algafood.mappers;

import com.erichiroshi.algafood.api.dtos.ProdutoDto;
import com.erichiroshi.algafood.api.dtos.inputs.ProdutoInputDto;
import com.erichiroshi.algafood.domain.model.Produto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProdutoMapper {
    Produto toEntity(ProdutoDto produtoDto);

    ProdutoDto toDto(Produto produto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Produto partialUpdate(ProdutoDto produtoDto, @MappingTarget Produto produto);

    Produto toEntity(ProdutoInputDto produtoInputDto);

    ProdutoInputDto toDto1(Produto produto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Produto partialUpdate(ProdutoInputDto produtoInputDto, @MappingTarget Produto produto);
}