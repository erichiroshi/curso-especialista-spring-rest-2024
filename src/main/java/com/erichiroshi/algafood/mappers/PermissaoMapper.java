package com.erichiroshi.algafood.mappers;

import com.erichiroshi.algafood.api.dtos.PermissaoDto;
import com.erichiroshi.algafood.domain.model.Permissao;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PermissaoMapper {
    Permissao toEntity(PermissaoDto permissaoDto);

    PermissaoDto toDto(Permissao permissao);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Permissao partialUpdate(PermissaoDto permissaoDto, @MappingTarget Permissao permissao);
}