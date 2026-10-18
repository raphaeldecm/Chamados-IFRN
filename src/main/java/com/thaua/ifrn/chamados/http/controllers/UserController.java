package com.thaua.ifrn.chamados.http.controllers;

import com.thaua.ifrn.chamados.business.services.UserService;
import com.thaua.ifrn.chamados.http.dto.response.users.UserResponse;
import com.thaua.ifrn.chamados.persistence.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponse> me(@AuthenticationPrincipal UserEntity userEntity) {
        return ResponseEntity.ok(this.userService.me(userEntity));
    }
}
