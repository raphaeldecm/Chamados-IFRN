package com.thaua.ifrn.chamados.http.controllers;

import com.thaua.ifrn.chamados.business.services.CalledService;
import com.thaua.ifrn.chamados.http.dto.request.calleds.CalledRequest;
import com.thaua.ifrn.chamados.http.dto.response.calleds.CalledResponse;
import com.thaua.ifrn.chamados.persistence.entities.UserEntity;
import com.thaua.ifrn.chamados.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/requesters")
public class RequesterController {
    private final CalledService calledService;
    private final UserRepository userRepository;

    @PostMapping("/calleds")
    public ResponseEntity<CalledResponse> openCalled(
            @AuthenticationPrincipal UserEntity currentUser,
            @RequestBody CalledRequest request
    ) {
        return ResponseEntity.ok(this.calledService.openCalled(currentUser, request));
    }

    @PatchMapping("/calleds/{calledId}/cancel")
    public ResponseEntity<CalledResponse> cancelCalled(
            @AuthenticationPrincipal UserEntity currentUser,
            @PathVariable UUID calledId
    ) {
        return ResponseEntity.ok(this.calledService.cancel(currentUser, calledId));
    }
}
