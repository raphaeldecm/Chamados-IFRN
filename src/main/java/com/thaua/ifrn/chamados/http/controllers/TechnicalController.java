package com.thaua.ifrn.chamados.http.controllers;

import com.thaua.ifrn.chamados.business.services.TechnicalService;
import com.thaua.ifrn.chamados.http.dto.response.calleds.CalledResponse;
import com.thaua.ifrn.chamados.persistence.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/technicals")
@RequiredArgsConstructor
public class TechnicalController {
    private final TechnicalService technicalService;

    @PatchMapping("/calleds/{calledId}")
    public ResponseEntity<CalledResponse> assign(
            @AuthenticationPrincipal UserEntity currentUser,
            @PathVariable UUID calledId
    ) {
        return ResponseEntity.ok(this.technicalService.assign(currentUser, calledId));
    }
}
