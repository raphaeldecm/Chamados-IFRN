package com.thaua.ifrn.chamados.http.dto.response.categories;

import java.util.UUID;

public record CategoryResponse(
        UUID id,
        String name,
        String description,
        CategorySectorResponse sector
) {
}
