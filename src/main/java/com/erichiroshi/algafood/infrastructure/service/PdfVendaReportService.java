package com.erichiroshi.algafood.infrastructure.service;

import com.erichiroshi.algafood.api.model.dtos.VendaDiaria;
import com.erichiroshi.algafood.domain.filter.VendaDiariaFilter;
import com.erichiroshi.algafood.domain.service.VendaQueryService;
import com.erichiroshi.algafood.domain.service.VendaReportService;
import com.erichiroshi.algafood.infrastructure.service.report.ReportException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class PdfVendaReportService implements VendaReportService {

    private final VendaQueryService vendaQueryService;

    public PdfVendaReportService(VendaQueryService vendaQueryService) {
        this.vendaQueryService = vendaQueryService;
    }

    @Override
    public byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {

        try {
            InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/vendas-diarias.jasper");

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));

            List<VendaDiaria> vendaDiarias = vendaQueryService.consultarVendasDiarias(filtro, timeOffset);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(vendaDiarias);


            JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);
            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (Exception e) {
            throw new ReportException("Não foi possível emitir relatório de vendas diárias", e);
        }
    }
}
