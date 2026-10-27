package com.thaua.ifrn.chamados.http.dto.response.calleds;

import java.util.UUID;

public record CalledCategoryResponse(
        UUID id,
        String name
) {
}
