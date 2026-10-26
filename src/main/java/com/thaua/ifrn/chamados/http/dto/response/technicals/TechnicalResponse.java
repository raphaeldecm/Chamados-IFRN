package com.thaua.ifrn.chamados.http.dto.response.technicals;

import com.thaua.ifrn.chamados.domain.enums.Role;

import java.util.UUID;

public record TechnicalResponse(
        UUID id,
        String name,
        String email,
        Long registration,
        Boolean active,
        TechnicalSectorResponse sector,
        Role role

) {
}
