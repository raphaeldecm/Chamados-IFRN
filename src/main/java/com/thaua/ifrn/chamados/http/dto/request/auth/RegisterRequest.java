package com.thaua.ifrn.chamados.http.dto.request.auth;

public record RegisterRequest(
        String name,
        String email,
        String password
) {
}
