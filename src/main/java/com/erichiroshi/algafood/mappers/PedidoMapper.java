package com.erichiroshi.algafood.mappers;

import com.erichiroshi.algafood.api.dtos.ItemPedidoDto;
import com.erichiroshi.algafood.api.dtos.PedidoDto;
import com.erichiroshi.algafood.domain.model.ItemPedido;
import com.erichiroshi.algafood.domain.model.Pedido;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {
		EnderecoMapper.class, FormaPagamentoMapper.class, UsuarioMapper.class })
public interface PedidoMapper {

	Pedido toEntity(PedidoDto pedidoDto);

	PedidoDto toDto(Pedido pedido);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	Pedido partialUpdate(PedidoDto pedidoDto, @MappingTarget Pedido pedido);

	@Mapping(source = "produto.id", target = "produtoId")
	@Mapping(source = "produto.nome", target = "produtoNome")
	ItemPedidoDto toItemPedidoDto(ItemPedido item);

}