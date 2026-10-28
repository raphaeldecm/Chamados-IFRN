package com.thaua.ifrn.chamados.http.controllers;

import com.thaua.ifrn.chamados.business.CalledService;
import com.thaua.ifrn.chamados.domain.enums.CalledStatus;
import com.thaua.ifrn.chamados.domain.enums.Priority;
import com.thaua.ifrn.chamados.http.dto.request.calleds.CalledRequest;
import com.thaua.ifrn.chamados.http.dto.response.calleds.CalledResponse;
import com.thaua.ifrn.chamados.http.dto.response.calleds.CalledWithInteractionsResponse;
import com.thaua.ifrn.chamados.persistence.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping
    public ResponseEntity<Page<CalledResponse>> list(
            @ParameterObject @PageableDefault Pageable pageable,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String place,
            @RequestParam(required = false) Priority priority,
            @RequestParam(required = false)CalledStatus status,
            @RequestParam(required = false) String solution,
            @RequestParam(required = false) String requester,
            @RequestParam(required = false) String technical,
            @RequestParam(required = false) String category
    ) {
        return ResponseEntity.ok(this.calledService.list(pageable, title, description, place, priority, status, solution, requester, technical, category));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<Page<CalledResponse>> listByUser(
            @PathVariable UUID userId,
            @ParameterObject @PageableDefault Pageable pageable
    ) {
        return ResponseEntity.ok(this.calledService.listByUser(userId, pageable));
    }
}
