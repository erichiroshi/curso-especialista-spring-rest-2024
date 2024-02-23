package com.erichiroshi.algafood.api.exceptionHandler;

import com.erichiroshi.algafood.domain.exception.EntidadeEmUsoExecption;
import com.erichiroshi.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.erichiroshi.algafood.domain.exception.NegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<StandardError> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e) {
        StandardError error = StandardError.builder()
                .dataHora(LocalDateTime.now())
                .mensagem(e.getMessage()).build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<StandardError> tratarEntidadeNaoEncontradaException(NegocioException e) {
        StandardError error = StandardError.builder()
                .dataHora(LocalDateTime.now())
                .mensagem(e.getMessage()).build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(EntidadeEmUsoExecption.class)
    public ResponseEntity<StandardError> tratarEntidadeNaoEncontradaException(EntidadeEmUsoExecption e) {
        StandardError error = StandardError.builder()
                .dataHora(LocalDateTime.now())
                .mensagem(e.getMessage()).build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

}
