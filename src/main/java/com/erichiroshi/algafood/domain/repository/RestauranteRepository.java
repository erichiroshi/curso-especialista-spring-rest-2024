package com.erichiroshi.algafood.domain.repository;

import com.erichiroshi.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    @Query("from Restaurante r join fetch r.cozinha")
    List<Restaurante> findAll();
}