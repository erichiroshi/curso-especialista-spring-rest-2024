package com.erichiroshi.algafood.api.controller;

import com.erichiroshi.algafood.api.model.dtos.PedidoDto;
import com.erichiroshi.algafood.api.model.dtos.PedidoResumoDto;
import com.erichiroshi.algafood.api.model.dtos.inputs.PedidoInputDto;
import com.erichiroshi.algafood.domain.model.Pedido;
import com.erichiroshi.algafood.domain.service.EmissaoPedidoService;
import com.erichiroshi.algafood.mappers.PedidoMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final EmissaoPedidoService service;
    private final PedidoMapper mapper;

    public PedidoController(EmissaoPedidoService service, PedidoMapper pedidoMapper) {
        this.service = service;
        this.mapper = pedidoMapper;
    }

    @GetMapping
    public MappingJacksonValue listar(@RequestParam(required = false) String campos) {
        List<Pedido> list = service.findAll();
        List<PedidoResumoDto> pedidosModel = list.stream().map(mapper::toDto1).collect(Collectors.toList());

        MappingJacksonValue pedidosWrapper = new MappingJacksonValue(pedidosModel);

        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.serializeAll());

        if (StringUtils.isNotBlank(campos)) {
            filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.filterOutAllExcept(campos.split(",")));
        }

        pedidosWrapper.setFilters(filterProvider);

        return pedidosWrapper;
    }



//    @GetMapping
//    public ResponseEntity<List<PedidoResumoDto>> listar() {
//        List<Pedido> list = service.findAll();
//        return ResponseEntity.ok(list.stream().map(mapper::toDto1).collect(Collectors.toList()));
//    }

    @GetMapping("/{codigoPedido}")
    public ResponseEntity<PedidoDto> buscar(@PathVariable String codigoPedido) {
        Pedido pedido = service.findByCodigo(codigoPedido);

        return ResponseEntity.ok(mapper.toDto(pedido));
    }

    @PostMapping
    public ResponseEntity<PedidoDto> adicionar(@RequestBody PedidoInputDto pedidoInputDto){
        Pedido pedido = service.emitir(pedidoInputDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(pedido));
    }
}
