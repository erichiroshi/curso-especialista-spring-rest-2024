package com.erichiroshi.algafood.mappers;

import com.erichiroshi.algafood.api.model.dtos.CozinhaDto;
import com.erichiroshi.algafood.api.model.dtos.inputs.CozinhaInputDto;
import com.erichiroshi.algafood.domain.model.Cozinha;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CozinhaMapper {
	
    Cozinha toEntity(CozinhaDto cozinhaDto);

    CozinhaDto toDto(Cozinha cozinha);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cozinha partialUpdate(CozinhaDto cozinhaDto, @MappingTarget Cozinha cozinha);

    Cozinha toEntity(CozinhaInputDto cozinhaInputDto);

    CozinhaInputDto toDto1(Cozinha cozinha);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cozinha partialUpdate(CozinhaInputDto cozinhaInputDto, @MappingTarget Cozinha cozinha);
}