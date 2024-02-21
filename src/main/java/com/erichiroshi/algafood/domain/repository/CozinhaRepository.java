package com.erichiroshi.algafood.domain.repository;

import com.erichiroshi.algafood.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
}