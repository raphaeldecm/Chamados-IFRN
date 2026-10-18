package com.thaua.ifrn.chamados.http.dto.request.auth;

public record LoginRequest(
        String email,
        String password
) {
}
