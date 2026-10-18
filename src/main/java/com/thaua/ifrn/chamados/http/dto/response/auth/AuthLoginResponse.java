package com.thaua.ifrn.chamados.http.dto.response.auth;

import java.util.UUID;

public record AuthLoginResponse(
        String accessToken,
        String refreshToken
) {
}
