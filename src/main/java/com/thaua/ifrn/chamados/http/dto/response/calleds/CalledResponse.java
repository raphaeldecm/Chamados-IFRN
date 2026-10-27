package com.thaua.ifrn.chamados.http.dto.response.calleds;

import com.thaua.ifrn.chamados.domain.enums.CalledStatus;
import com.thaua.ifrn.chamados.domain.enums.Priority;

import java.time.LocalDateTime;
import java.util.UUID;

public record CalledResponse(
        UUID id,
        String title,
        String description,
        String place,
        Priority priority,
        CalledStatus status,
        String solution,
        CalledRequester requester,
        CalledTechnical technical,
        CalledCategory category,
        LocalDateTime openingDate,
        LocalDateTime closedDate
) {
}
