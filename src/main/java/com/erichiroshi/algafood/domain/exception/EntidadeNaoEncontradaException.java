package com.erichiroshi.algafood.domain.exception;

public abstract class EntidadeNaoEncontradaException extends NegocioException {
	private static final long serialVersionUID = 1L;

    protected EntidadeNaoEncontradaException(String message) {
        super(message);
    }
}
