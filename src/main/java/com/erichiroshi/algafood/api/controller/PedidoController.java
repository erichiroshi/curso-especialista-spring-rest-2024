package com.erichiroshi.algafood.api.controller;

import com.erichiroshi.algafood.api.dtos.PedidoDto;
import com.erichiroshi.algafood.api.dtos.PedidoResumoDto;
import com.erichiroshi.algafood.api.dtos.inputs.PedidoInputDto;
import com.erichiroshi.algafood.domain.model.Pedido;
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
    public ResponseEntity<List<PedidoResumoDto>> listar() {
        List<Pedido> list = service.findAll();
        return ResponseEntity.ok(list.stream().map(mapper::toDto1).collect(Collectors.toList()));
    }

    @GetMapping("/{pedidoId}")
    public ResponseEntity<PedidoDto> buscar(@PathVariable Long pedidoId) {
        Pedido pedido = service.findById(pedidoId);

        return ResponseEntity.ok(mapper.toDto(pedido));
    }

    @PostMapping
    public ResponseEntity<PedidoDto> adicionar(@RequestBody PedidoInputDto pedidoInputDto){
        Pedido pedido = service.emitir(pedidoInputDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(pedido));
    }
}
