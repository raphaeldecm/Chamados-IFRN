package com.thaua.ifrn.chamados.http.dto.request.technicals;

import java.util.UUID;

public record CreateTechnicalRequest(
        String name,
        String email,
        String password,
        UUID sectorId
) {
}
