package com.erichiroshi.algafood.mappers;

import com.erichiroshi.algafood.api.dtos.EnderecoDto;
import com.erichiroshi.algafood.domain.model.Endereco;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EnderecoMapper {
    @Mapping(source = "cidade.estado", target = "cidade.estado.nome")
    Endereco toEntity(EnderecoDto enderecoDto);

    @Mapping(source = "cidade.estado.nome", target = "cidade.estado")
    EnderecoDto toDto(Endereco endereco);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "cidade.estado", target = "cidade.estado.nome")
    Endereco partialUpdate(EnderecoDto enderecoDto, @MappingTarget Endereco endereco);
}