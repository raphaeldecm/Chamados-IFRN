package com.thaua.ifrn.chamados.http.exceptions;

public class SectorNotFoundException extends RuntimeException {
    public SectorNotFoundException(String message) {
        super(message);
    }
    public SectorNotFoundException() {
        super("Setor não foi encontrado");
    }
}
