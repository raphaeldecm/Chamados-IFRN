package com.thaua.ifrn.chamados.http.dto.response.calleds;

import java.time.LocalDateTime;
import java.util.UUID;

public record CalledInteractionsResponse(
        UUID id,
        String text,
        CalledInteractionsAuthorResponse author,
        LocalDateTime createdAt
) {
}
