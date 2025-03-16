package com.softdesign.avaliacao_tecnica.exception;

public class SessaoVotacaoInvalidaException extends RuntimeException {
    public SessaoVotacaoInvalidaException(String mensagem) {
        super(mensagem);
    }
}