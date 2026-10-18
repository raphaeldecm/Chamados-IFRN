package com.thaua.ifrn.chamados.http.dto.response.auth;

import java.util.UUID;

public record AuthResponse(
        UUID id,
        String name,
        String email,
        Long registration,
        Boolean active
) {
}
