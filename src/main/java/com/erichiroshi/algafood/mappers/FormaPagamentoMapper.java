package com.erichiroshi.algafood.mappers;

import com.erichiroshi.algafood.api.model.dtos.FormaPagamentoDto;
import com.erichiroshi.algafood.api.model.dtos.inputs.FormaPagamentoInputDto;
import com.erichiroshi.algafood.domain.model.FormaPagamento;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FormaPagamentoMapper {
	
    FormaPagamento toEntity(FormaPagamentoDto formaPagamentoDto);

    FormaPagamentoDto toDto(FormaPagamento formaPagamento);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FormaPagamento partialUpdate(FormaPagamentoDto formaPagamentoDto, @MappingTarget FormaPagamento formaPagamento);

    FormaPagamento toEntity(FormaPagamentoInputDto formaPagamentoInputDto);

    FormaPagamentoInputDto toDto1(FormaPagamento formaPagamento);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FormaPagamento partialUpdate(FormaPagamentoInputDto formaPagamentoInputDto, @MappingTarget FormaPagamento formaPagamento);
}