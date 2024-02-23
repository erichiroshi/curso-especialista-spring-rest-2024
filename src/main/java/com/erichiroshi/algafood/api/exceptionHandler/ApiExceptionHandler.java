package com.erichiroshi.algafood.api.exceptionHandler;

import com.erichiroshi.algafood.domain.exception.EntidadeEmUsoExecption;
import com.erichiroshi.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.erichiroshi.algafood.domain.exception.NegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        StandardErrorType type = StandardErrorType.MENSAGEM_INCOMPREENSIVEL;
        String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";

        StandardError error = createStandardErrorBuilder((HttpStatus) status, type, detail)
                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                .build();

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardErrorType type = StandardErrorType.RECURSO_NAO_ENCONTRADO;
        String detail = ex.getMessage();

        StandardError error = createStandardErrorBuilder(status, type, detail)
                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                .build();

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardErrorType type = StandardErrorType.ERRO_NEGOCIO;
        String detail = ex.getMessage();

        StandardError error = createStandardErrorBuilder(status, type, detail)
                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                .build();

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeEmUsoExecption.class)
    public ResponseEntity<?> handleEntidadeEmUsoExecption(EntidadeEmUsoExecption ex, WebRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        StandardErrorType type = StandardErrorType.ENTIDADE_EM_USO;
        String detail = ex.getMessage();

        StandardError error = createStandardErrorBuilder(status, type, detail)
                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                .build();

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        if (body == null) {
            body = StandardError.builder()
                    .timestamp(LocalDateTime.now())
                    .status(statusCode.value())
                    .title(HttpStatus.valueOf(statusCode.value()).getReasonPhrase())
                    .detail(ex.getMessage())
                    .path(((ServletWebRequest) request).getRequest().getRequestURI())
                    .build();

        } else if (body instanceof String) {
            body = StandardError.builder()
                    .timestamp(LocalDateTime.now())
                    .status(statusCode.value())
                    .title((String) body)
                    .detail(ex.getMessage())
                    .path(((ServletWebRequest) request).getRequest().getRequestURI())
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    private StandardError.StandardErrorBuilder createStandardErrorBuilder(HttpStatus status, StandardErrorType type, String detail) {

        return StandardError.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .type(type.getUri())
                .title(type.getTitle())
                .detail(detail);
    }


}
