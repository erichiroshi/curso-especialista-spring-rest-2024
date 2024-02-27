package com.erichiroshi.algafood.mappers;

import com.erichiroshi.algafood.api.dtos.CidadeDto;
import com.erichiroshi.algafood.api.dtos.inputs.CidadeIdInputDto;
import com.erichiroshi.algafood.api.dtos.inputs.CidadeInputDto;
import com.erichiroshi.algafood.domain.model.Cidade;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {EstadoMapper.class})
public interface CidadeMapper {
	
    Cidade toEntity(CidadeDto cidadeDto);

    CidadeDto toDto(Cidade cidade);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cidade partialUpdate(CidadeDto cidadeDto, @MappingTarget Cidade cidade);

    Cidade toEntity(CidadeInputDto cidadeInputDto);

    CidadeInputDto toDto1(Cidade cidade);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "estado", ignore = true)
    Cidade partialUpdate(CidadeInputDto cidadeInputDto, @MappingTarget Cidade cidade);

    Cidade toEntity(CidadeIdInputDto cidadeIdInputDto);

    CidadeIdInputDto toDto2(Cidade cidade);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cidade partialUpdate(CidadeIdInputDto cidadeIdInputDto, @MappingTarget Cidade cidade);
}