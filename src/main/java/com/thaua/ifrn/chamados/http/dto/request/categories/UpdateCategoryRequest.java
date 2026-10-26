package com.thaua.ifrn.chamados.http.dto.request.categories;

import java.util.UUID;

public record UpdateCategoryRequest(
        String name,
        String description,
        UUID sectorId
) {
}
