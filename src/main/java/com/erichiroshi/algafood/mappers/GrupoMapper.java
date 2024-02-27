package com.erichiroshi.algafood.mappers;

import com.erichiroshi.algafood.api.dtos.GrupoDto;
import com.erichiroshi.algafood.api.dtos.inputs.GrupoInputDto;
import com.erichiroshi.algafood.domain.model.Grupo;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface GrupoMapper {
	
    Grupo toEntity(GrupoDto grupoDto);

    GrupoDto toDto(Grupo grupo);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Grupo partialUpdate(GrupoDto grupoDto, @MappingTarget Grupo grupo);

    Grupo toEntity(GrupoInputDto grupoInputDto);

    GrupoInputDto toDto1(Grupo grupo);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Grupo partialUpdate(GrupoInputDto grupoInputDto, @MappingTarget Grupo grupo);
}