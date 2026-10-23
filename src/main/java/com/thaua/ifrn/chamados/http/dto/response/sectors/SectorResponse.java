package com.thaua.ifrn.chamados.http.dto.response.sectors;

import java.util.UUID;

public record SectorResponse(
        UUID id,
        String name,
        String acronym
) {
}
