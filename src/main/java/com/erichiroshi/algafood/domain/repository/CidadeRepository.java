package com.erichiroshi.algafood.domain.repository;

import com.erichiroshi.algafood.domain.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}