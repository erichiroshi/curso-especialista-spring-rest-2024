package com.erichiroshi.algafood.mappers;

import com.erichiroshi.algafood.api.model.dtos.EnderecoDto;
import com.erichiroshi.algafood.api.model.dtos.inputs.EnderecoInputDto;
import com.erichiroshi.algafood.domain.model.Endereco;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {CidadeMapper.class})
public interface EnderecoMapper {
	
    @Mapping(source = "cidade.estado", target = "cidade.estado.nome")
    Endereco toEntity(EnderecoDto enderecoDto);

    @Mapping(source = "cidade.estado.nome", target = "cidade.estado")
    EnderecoDto toDto(Endereco endereco);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "cidade.estado", target = "cidade.estado.nome")
    Endereco partialUpdate(EnderecoDto enderecoDto, @MappingTarget Endereco endereco);

    Endereco toEntity(EnderecoInputDto enderecoInputDto);

    EnderecoInputDto toDto1(Endereco endereco);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Endereco partialUpdate(EnderecoInputDto enderecoInputDto, @MappingTarget Endereco endereco);
}