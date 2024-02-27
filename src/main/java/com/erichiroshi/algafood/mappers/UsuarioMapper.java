package com.erichiroshi.algafood.mappers;

import com.erichiroshi.algafood.api.dtos.UsuarioDto;
import com.erichiroshi.algafood.api.dtos.inputs.UsuarioInputDto;
import com.erichiroshi.algafood.api.dtos.inputs.UsuarioUpdateDto;
import com.erichiroshi.algafood.api.dtos.inputs.UsuarioSenhaUpdateDto;
import com.erichiroshi.algafood.domain.model.Usuario;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper {
    Usuario toEntity(UsuarioDto usuarioDto);

    UsuarioDto toDto(Usuario usuario);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Usuario partialUpdate(UsuarioDto usuarioDto, @MappingTarget Usuario usuario);

    Usuario toEntity(UsuarioInputDto usuarioInputDto);

    UsuarioInputDto toDto1(Usuario usuario);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Usuario partialUpdate(UsuarioInputDto usuarioInputDto, @MappingTarget Usuario usuario);

    Usuario toEntity(UsuarioUpdateDto usuarioUpdateDto);

    UsuarioUpdateDto toDto2(Usuario usuario);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Usuario partialUpdate(UsuarioUpdateDto usuarioUpdateDto, @MappingTarget Usuario usuario);

    Usuario toEntity(UsuarioSenhaUpdateDto usuarioSenhaUpdateDto);

    UsuarioSenhaUpdateDto toDto3(Usuario usuario);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Usuario partialUpdate(UsuarioSenhaUpdateDto usuarioSenhaUpdateDto, @MappingTarget Usuario usuario);
}