package com.thaua.ifrn.chamados.http.dto.response.calleds;

import java.util.UUID;

public record CalledRequester(
        UUID id,
        String email
) {
}
