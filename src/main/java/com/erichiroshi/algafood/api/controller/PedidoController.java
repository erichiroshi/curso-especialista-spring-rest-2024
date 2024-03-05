package com.erichiroshi.algafood.api.controller;

import com.erichiroshi.algafood.api.model.dtos.PedidoDto;
import com.erichiroshi.algafood.api.model.dtos.PedidoResumoDto;
import com.erichiroshi.algafood.api.model.dtos.inputs.PedidoInputDto;
import com.erichiroshi.algafood.core.data.PageableTranslator;
import com.erichiroshi.algafood.domain.model.Pedido;
import com.erichiroshi.algafood.domain.filter.PedidoFilter;
import com.erichiroshi.algafood.infrastructure.repository.spec.PedidoSpecs;
import com.erichiroshi.algafood.domain.service.EmissaoPedidoService;
import com.erichiroshi.algafood.mappers.PedidoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public ResponseEntity<Page<PedidoResumoDto>> pesquisar(PedidoFilter filtro, @PageableDefault(size = 10) Pageable pageable) {
        pageable = traduzirPageable(pageable);

        Page<Pedido> list = service.findAll(PedidoSpecs.usandoFiltro(filtro), pageable);
        return ResponseEntity.ok(list.map(mapper::toDto1));
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

    private Pageable traduzirPageable(Pageable apiPageable) {
        var mapeamento = Map.of(
                "codigo", "codigo",
                "restaurante.nome", "restaurante.nome",
                "nomeCliente", "cliente.nome",
                "valorTotal", "valorTotal"
        );

        return PageableTranslator.translate(apiPageable, mapeamento);
    }
}
