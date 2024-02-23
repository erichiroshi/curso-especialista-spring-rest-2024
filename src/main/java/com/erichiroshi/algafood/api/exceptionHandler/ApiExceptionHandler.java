package com.erichiroshi.algafood.api.exceptionHandler;

import com.erichiroshi.algafood.domain.exception.EntidadeEmUsoExecption;
import com.erichiroshi.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.erichiroshi.algafood.domain.exception.NegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request) {
        String error = "Entidade não Encontrada.";
        HttpStatusCode status = HttpStatus.NOT_FOUND;
        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> tratarNegocioException(NegocioException ex, WebRequest request) {
        String error = "Entidade não Encontrada.";
        HttpStatusCode status = HttpStatus.BAD_REQUEST;

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeEmUsoExecption.class)
    public ResponseEntity<?> tratarEntidadeEmUsoExecption(EntidadeEmUsoExecption ex, WebRequest request) {
        String error = "Entidade em uso.";
        HttpStatusCode status = HttpStatus.CONFLICT;

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        if (body == null) {
            body = StandardError.builder()
                    .timestamp(LocalDateTime.now())
                    .status(statusCode.value())
                    .error(HttpStatus.valueOf(statusCode.value()).getReasonPhrase())
                    .mensagem(ex.getMessage())
                    .path(((ServletWebRequest) request).getRequest().getRequestURI())
                    .build();

        } else if (body instanceof String) {
            body = StandardError.builder()
                    .timestamp(LocalDateTime.now())
                    .status(statusCode.value())
                    .error((String) body)
                    .mensagem(ex.getMessage())
                    .path(((ServletWebRequest) request).getRequest().getRequestURI())
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }


}
