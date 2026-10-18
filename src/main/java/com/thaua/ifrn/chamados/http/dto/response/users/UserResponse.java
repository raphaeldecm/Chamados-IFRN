package com.thaua.ifrn.chamados.http.dto.response.users;

import com.thaua.ifrn.chamados.domain.enums.Role;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String email,
        Long registration,
        Boolean active,
        Role role
) {
}
