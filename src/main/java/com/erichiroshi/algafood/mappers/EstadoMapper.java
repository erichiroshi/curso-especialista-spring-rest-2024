package com.erichiroshi.algafood.mappers;

import com.erichiroshi.algafood.api.dtos.EstadoDto;
import com.erichiroshi.algafood.api.dtos.inputs.EstadoInputDto;
import com.erichiroshi.algafood.domain.model.Estado;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EstadoMapper {
    Estado toEntity(EstadoDto estadoDto);

    EstadoDto toDto(Estado estado);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Estado partialUpdate(EstadoDto estadoDto, @MappingTarget Estado estado);

    Estado toEntity(EstadoInputDto estadoInputDto);

    EstadoInputDto toDto1(Estado estado);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Estado partialUpdate(EstadoInputDto estadoInputDto, @MappingTarget Estado estado);
}