package com.thaua.ifrn.chamados.http.exceptions;

public class CalledNotFoundException extends RuntimeException {
    public CalledNotFoundException(String message) {
        super(message);
    }

    public CalledNotFoundException() {
        super("Chamado não foi encontrado");
    }
}
