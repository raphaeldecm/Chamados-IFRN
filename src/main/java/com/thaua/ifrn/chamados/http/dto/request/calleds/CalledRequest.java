package com.thaua.ifrn.chamados.http.dto.request.calleds;

import com.thaua.ifrn.chamados.domain.enums.Priority;

import java.util.UUID;

public record CalledRequest(
        String title,
        String description,
        String place,
        Priority priority,
        UUID categoryId
) {
}
