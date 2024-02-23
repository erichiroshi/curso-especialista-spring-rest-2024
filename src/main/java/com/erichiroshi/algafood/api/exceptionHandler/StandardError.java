package com.erichiroshi.algafood.api.exceptionHandler;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class StandardError {

    private LocalDateTime dataHora;
    private String mensagem;
}
