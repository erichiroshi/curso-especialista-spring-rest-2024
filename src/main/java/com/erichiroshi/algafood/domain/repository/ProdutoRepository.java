package com.erichiroshi.algafood.domain.repository;

import com.erichiroshi.algafood.domain.model.Produto;
import com.erichiroshi.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends CustomJpaRepository<Produto, Long>, ProdutoRepositoryQueries {

    @Query("from Produto where restaurante.id = :restaurante and id = :produto")
    Optional<Produto> findById(@Param("restaurante") Long restauranteId, @Param("produto") Long produtoId);

    @Query("from Produto p where p.ativo = true and p.restaurante = :restaurante")
    List<Produto> findAtivosByRestaurante(Restaurante restaurante);

    List<Produto> findProdutosByRestaurante(Restaurante restaurante);
}