package com.thaua.ifrn.chamados.http.dto.response.calleds;

import java.util.UUID;

public record CalledTechnical(
        UUID id,
        String email,
        CalledTechnicalSector sector
) {
}
