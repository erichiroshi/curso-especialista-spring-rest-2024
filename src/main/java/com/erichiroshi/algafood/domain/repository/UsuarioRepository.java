package com.erichiroshi.algafood.domain.repository;

import com.erichiroshi.algafood.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}