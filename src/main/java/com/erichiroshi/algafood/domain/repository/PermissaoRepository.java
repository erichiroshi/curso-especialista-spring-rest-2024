package com.erichiroshi.algafood.domain.repository;

import com.erichiroshi.algafood.domain.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
}