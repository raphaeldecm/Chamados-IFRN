package com.thaua.ifrn.chamados.http.dto.response.categories;

import java.util.UUID;

public record CategorySectorResponse(
        UUID id,
        String name,
        String acronym
) {
}
