package com.erichiroshi.algafood.domain.service;

import com.erichiroshi.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.erichiroshi.algafood.domain.exception.NegocioException;
import com.erichiroshi.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.erichiroshi.algafood.domain.model.Cozinha;
import com.erichiroshi.algafood.domain.model.Restaurante;
import com.erichiroshi.algafood.domain.repository.RestauranteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class RestauranteService {

    private final RestauranteRepository repository;

    private final CozinhaService cozinhaService;

    public RestauranteService(RestauranteRepository repository, CozinhaService cozinhaService) {
        this.repository = repository;
        this.cozinhaService = cozinhaService;
    }

    public List<Restaurante> findAll() {
        return repository.findAll();
    }

    public Restaurante findById(Long restauranteId) {
        return repository.findById(restauranteId)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
    }

    public Restaurante salvar(Restaurante restaurante) {
        try {
            Cozinha cozinha = cozinhaService.findById(restaurante.getCozinha().getId());
            restaurante.setCozinha(cozinha);
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }

        return repository.save(restaurante);
    }

    public Restaurante atualizar(Long restauranteId, Restaurante restaurante) {
        Restaurante restauranteAtual = findById(restauranteId);

        try {
            Cozinha cozinha = cozinhaService.findById(restaurante.getCozinha().getId());
            BeanUtils.copyProperties(restaurante, restauranteAtual,
                    "id", "formasPagamento", "endereco", "dataCadastro", "produtos");
            restauranteAtual.setCozinha(cozinha);
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
        return repository.save(restauranteAtual);
    }

    public Restaurante atualizarParcial(Long restauranteId, Map<String, Object> campos) {
        Restaurante restauranteAtual = findById(restauranteId);

        merge(campos, restauranteAtual);

        return atualizar(restauranteId, restauranteAtual);
    }

    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            assert field != null;
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

            System.out.println(nomePropriedade + " = " + valorPropriedade);

            ReflectionUtils.setField(field, restauranteDestino, novoValor);
        });
    }
}
