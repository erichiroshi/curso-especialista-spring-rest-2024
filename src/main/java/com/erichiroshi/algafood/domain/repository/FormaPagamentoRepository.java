package com.erichiroshi.algafood.domain.repository;

import com.erichiroshi.algafood.domain.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {
}