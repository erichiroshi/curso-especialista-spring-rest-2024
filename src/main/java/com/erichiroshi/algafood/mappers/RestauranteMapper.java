package com.erichiroshi.algafood.mappers;

import com.erichiroshi.algafood.api.dtos.inputs.RestauranteInputDto;
import com.erichiroshi.algafood.api.dtos.RestauranteDto;
import com.erichiroshi.algafood.domain.model.Restaurante;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RestauranteMapper {
    Restaurante toEntity(RestauranteDto restauranteDto);

    RestauranteDto toDto(Restaurante restaurante);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Restaurante update(RestauranteDto restauranteDto, @MappingTarget Restaurante restaurante);

    Restaurante toEntity(RestauranteInputDto restauranteInputDto);

    RestauranteInputDto toDto1(Restaurante restaurante);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "cozinha", ignore = true)
    Restaurante update(RestauranteInputDto restauranteInputDto, @MappingTarget Restaurante restaurante);
}