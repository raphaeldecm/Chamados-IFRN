package com.thaua.ifrn.chamados.http.dto.response.technicals;

import java.util.UUID;

public record TechnicalSectorResponse(
        UUID id,
        String name,
        String acronym
) {
}
