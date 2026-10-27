package com.thaua.ifrn.chamados.http.controllers;

import com.thaua.ifrn.chamados.business.CalledService;
import com.thaua.ifrn.chamados.http.dto.request.calleds.CalledRequest;
import com.thaua.ifrn.chamados.http.dto.response.calleds.CalledResponse;
import com.thaua.ifrn.chamados.http.dto.response.calleds.CalledWithInteractionsResponse;
import com.thaua.ifrn.chamados.persistence.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/calleds")
@RequiredArgsConstructor
public class CalledController {
    private final CalledService calledService;

    @PostMapping
    public ResponseEntity<CalledResponse> openCalled(
            @AuthenticationPrincipal UserEntity currentUser,
            @RequestBody CalledRequest request
    ) {
        return ResponseEntity.ok(this.calledService.openCalled(currentUser, request));
    }

    @GetMapping("/{calledId}")
    public ResponseEntity<CalledWithInteractionsResponse> fetch(@PathVariable UUID calledId) {
        return ResponseEntity.ok(this.calledService.fetch(calledId));
    }
}
