package com.erichiroshi.algafood;

import com.erichiroshi.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.erichiroshi.algafood.domain.exception.EntidadeEmUsoException;
import com.erichiroshi.algafood.domain.model.Cozinha;
import com.erichiroshi.algafood.domain.service.CozinhaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CadastroCozinhaIT {

    @Autowired
    private CozinhaService cozinhaService;

    @Test
    public void deveAtribuirId_QuandoCadastrarCozinhaComDadosCorretos() {
        // cenário
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome("Chinesa");

        // ação
        novaCozinha = cozinhaService.salvar(novaCozinha);

        // validação
        assertThat(novaCozinha).isNotNull();
        assertThat(novaCozinha.getId()).isNotNull();
    }

    @Test
    public void deveFalhar_QuandoCadastrarCozinhaSemNome() {
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome(null);

        DataIntegrityViolationException erroEsperado =
                Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
                    cozinhaService.salvar(novaCozinha);
                });

        assertThat(erroEsperado).isNotNull();
    }

    @Test
    public void deveFalhar_QuandoExcluirCozinhaEmUso() {

        EntidadeEmUsoException erroEsperado =
                Assertions.assertThrows(EntidadeEmUsoException.class, () -> {
                    cozinhaService.excluir(1L);
                });

        assertThat(erroEsperado).isNotNull();
    }

    @Test
    public void deveFalhar_QuandoExcluirCozinhaInexistente() {

        CozinhaNaoEncontradaException erroEsperado =
                Assertions.assertThrows(CozinhaNaoEncontradaException.class, () -> {
                    cozinhaService.excluir(100L);
                });

        assertThat(erroEsperado).isNotNull();
    }

}