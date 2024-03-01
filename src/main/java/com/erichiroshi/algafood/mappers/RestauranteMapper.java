package com.erichiroshi.algafood.mappers;

import com.erichiroshi.algafood.api.model.dtos.RestauranteDto;
import com.erichiroshi.algafood.api.model.dtos.RestauranteResumoDto;
import com.erichiroshi.algafood.api.model.dtos.inputs.RestauranteInputDto;
import com.erichiroshi.algafood.domain.model.Restaurante;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RestauranteMapper {

    @Mapping(source = "endereco.cidade.estado", target = "endereco.cidade.estado.nome")
    Restaurante toEntity(RestauranteDto restauranteDto);

    @Mapping(source = "endereco.cidade.estado.nome", target = "endereco.cidade.estado")
    RestauranteDto toDto(Restaurante restaurante);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "endereco.cidade.estado", target = "endereco.cidade.estado.nome")
    Restaurante update(RestauranteDto restauranteDto, @MappingTarget Restaurante restaurante);

    Restaurante toEntity(RestauranteInputDto restauranteInputDto);

    RestauranteInputDto toDto1(Restaurante restaurante);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "cozinha", ignore = true)
    @Mapping(target = "endereco", ignore = true)
    Restaurante update(RestauranteInputDto restauranteInputDto, @MappingTarget Restaurante restaurante);

    Restaurante toEntity(RestauranteResumoDto restauranteResumoDto);

    RestauranteResumoDto toDto2(Restaurante restaurante);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Restaurante partialUpdate(RestauranteResumoDto restauranteResumoDto, @MappingTarget Restaurante restaurante);
}