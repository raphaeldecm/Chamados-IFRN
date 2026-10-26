package com.thaua.ifrn.chamados.http.exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException() {
        super("Categoria não foi encontrada");
    }
}
