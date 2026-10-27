package com.thaua.ifrn.chamados.http.dto.response.calleds;

import java.util.UUID;

public record CalledTechnicalResponse(
        UUID id,
        String email,
        CalledTechnicalSectorResponse sector
) {
}
