package com.thaua.ifrn.chamados.http.dto.response.calleds;

import com.thaua.ifrn.chamados.domain.enums.CalledStatus;
import com.thaua.ifrn.chamados.domain.enums.Priority;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CalledWithInteractionsResponse(
        UUID id,
        String title,
        String description,
        String place,
        Priority priority,
        CalledStatus status,
        String solution,
        CalledRequesterResponse requester,
        CalledTechnicalResponse technical,
        CalledCategoryResponse category,
        List<CalledInteractionsResponse> interactions,
        LocalDateTime openingDate,
        LocalDateTime closedDate
) {
}
