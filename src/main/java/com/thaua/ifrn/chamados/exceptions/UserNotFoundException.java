package com.thaua.ifrn.chamados.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException() {
        super("Usuário não foi encontrado");
    }
}
