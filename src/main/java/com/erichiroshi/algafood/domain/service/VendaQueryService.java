package com.erichiroshi.algafood.domain.service;

import com.erichiroshi.algafood.api.model.dtos.VendaDiaria;
import com.erichiroshi.algafood.domain.filter.VendaDiariaFilter;

import java.util.List;

public interface VendaQueryService {

    List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset);

}
