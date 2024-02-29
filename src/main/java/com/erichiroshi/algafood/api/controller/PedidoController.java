package com.erichiroshi.algafood.api.controller;

import com.erichiroshi.algafood.api.dtos.PedidoDto;
import com.erichiroshi.algafood.api.dtos.PedidoResumoDto;
import com.erichiroshi.algafood.domain.model.Pedido;
import com.erichiroshi.algafood.domain.service.EmissaoPedidoService;
import com.erichiroshi.algafood.mappers.PedidoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        List<Pedido> list = service.listar();
        return ResponseEntity.ok(list.stream().map(mapper::toDto1).collect(Collectors.toList()));
    }

    @GetMapping("/{pedidoId}")
    public ResponseEntity<PedidoDto> buscar(@PathVariable Long pedidoId) {
        Pedido pedido = service.buscar(pedidoId);

        return ResponseEntity.ok(mapper.toDto(pedido));
    }
}
