package com.thaua.ifrn.chamados.http.controllers;

import com.thaua.ifrn.chamados.business.CalledService;
import com.thaua.ifrn.chamados.http.dto.request.calleds.CalledRequest;
import com.thaua.ifrn.chamados.http.dto.response.calleds.CalledResponse;
import com.thaua.ifrn.chamados.persistence.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
