package com.erichiroshi.algafood.api.controller;

import com.erichiroshi.algafood.api.model.dtos.PedidoDto;
import com.erichiroshi.algafood.api.model.dtos.PedidoResumoDto;
import com.erichiroshi.algafood.api.model.dtos.inputs.PedidoInputDto;
import com.erichiroshi.algafood.domain.model.Pedido;
import com.erichiroshi.algafood.domain.repository.filter.PedidoFilter;
import com.erichiroshi.algafood.domain.repository.specs.PedidoSpecs;
import com.erichiroshi.algafood.domain.service.EmissaoPedidoService;
import com.erichiroshi.algafood.mappers.PedidoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<PedidoResumoDto>> pesquisar(PedidoFilter filtro) {
        List<Pedido> list = service.findAll(PedidoSpecs.usandoFiltro(filtro));
        return ResponseEntity.ok(list.stream().map(mapper::toDto1).collect(Collectors.toList()));
    }

    @GetMapping("/{codigoPedido}")
    public ResponseEntity<PedidoDto> buscar(@PathVariable String codigoPedido) {
        Pedido pedido = service.findByCodigo(codigoPedido);

        return ResponseEntity.ok(mapper.toDto(pedido));
    }

    @PostMapping
    public ResponseEntity<PedidoDto> adicionar(@RequestBody PedidoInputDto pedidoInputDto) {
        Pedido pedido = service.emitir(pedidoInputDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(pedido));
    }
}
