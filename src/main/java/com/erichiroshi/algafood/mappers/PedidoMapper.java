package com.erichiroshi.algafood.mappers;

import com.erichiroshi.algafood.api.model.dtos.ItemPedidoDto;
import com.erichiroshi.algafood.api.model.dtos.PedidoDto;
import com.erichiroshi.algafood.api.model.dtos.PedidoResumoDto;
import com.erichiroshi.algafood.api.model.dtos.inputs.ItemPedidoInputDto;
import com.erichiroshi.algafood.api.model.dtos.inputs.PedidoInputDto;
import com.erichiroshi.algafood.domain.model.ItemPedido;
import com.erichiroshi.algafood.domain.model.Pedido;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {
        EnderecoMapper.class, FormaPagamentoMapper.class, UsuarioMapper.class, RestauranteMapper.class})
public interface PedidoMapper {

    Pedido toEntity(PedidoDto pedidoDto);


    PedidoDto toDto(Pedido pedido);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Pedido partialUpdate(PedidoDto pedidoDto, @MappingTarget Pedido pedido);

    @Mapping(source = "produto.id", target = "produtoId")
    @Mapping(source = "produto.nome", target = "produtoNome")
    ItemPedidoDto toItemPedidoDto(ItemPedido item);

    Pedido toEntity(PedidoResumoDto pedidoResumoDto);

    @Mapping(source = "cliente.nome", target = "nomeCliente")
    PedidoResumoDto toDto1(Pedido pedido);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Pedido partialUpdate(PedidoResumoDto pedidoResumoDto, @MappingTarget Pedido pedido);

    Pedido toEntity(PedidoInputDto pedidoInputDto);

    @AfterMapping
    default void linkItens(@MappingTarget Pedido pedido) {
        pedido.getItens().forEach(iten -> iten.setPedido(pedido));
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Pedido partialUpdate(PedidoInputDto pedidoInputDto, @MappingTarget Pedido pedido);

    @Mapping(source = "produtoId", target = "produto.id")
    ItemPedido toItemPedido(ItemPedidoInputDto item);
}