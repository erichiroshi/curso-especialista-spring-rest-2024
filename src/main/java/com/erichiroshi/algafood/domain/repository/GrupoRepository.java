package com.erichiroshi.algafood.domain.repository;

import com.erichiroshi.algafood.domain.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {
}