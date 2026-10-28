package com.thaua.ifrn.chamados.http.exceptions;

public class TechnicalNotFoundException extends RuntimeException {
    public TechnicalNotFoundException(String message) {
        super(message);
    }

    public TechnicalNotFoundException() {
        super("Tecnico não foi encontrado");
    }
}
