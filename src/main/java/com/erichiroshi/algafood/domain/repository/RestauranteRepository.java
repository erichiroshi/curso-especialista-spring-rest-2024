package com.erichiroshi.algafood.domain.repository;

import com.erichiroshi.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
}