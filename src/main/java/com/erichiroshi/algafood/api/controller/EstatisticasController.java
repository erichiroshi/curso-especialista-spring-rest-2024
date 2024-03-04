package com.erichiroshi.algafood.api.controller;

import com.erichiroshi.algafood.api.model.dtos.VendaDiaria;
import com.erichiroshi.algafood.domain.filter.VendaDiariaFilter;
import com.erichiroshi.algafood.domain.service.VendaQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticasController {

    private final VendaQueryService vendaQueryService;

    public EstatisticasController(VendaQueryService vendaQueryService) {
        this.vendaQueryService = vendaQueryService;
    }

    @GetMapping("/vendas-diarias")
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro,
                                                    @RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {
        return vendaQueryService.consultarVendasDiarias(filtro, timeOffset);
    }
}
