package com.erichiroshi.algafood.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.erichiroshi.algafood.api.model.dtos.FotoProdutoDto;
import com.erichiroshi.algafood.domain.model.FotoProduto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = { EstadoMapper.class })
public interface FotoProdutoMapper {

	FotoProduto toEntity(FotoProdutoDto fotoProdutoDto);

	FotoProdutoDto toDto(FotoProduto fotoProduto);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	FotoProduto partialUpdate(FotoProdutoDto fotoProdutoDto, @MappingTarget FotoProduto fotoProduto);

}