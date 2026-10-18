package com.thaua.ifrn.chamados.business.services;

import com.thaua.ifrn.chamados.domain.enums.Role;
import com.thaua.ifrn.chamados.http.dto.request.auth.LoginRequest;
import com.thaua.ifrn.chamados.http.dto.request.auth.RegisterRequest;
import com.thaua.ifrn.chamados.http.dto.response.auth.AuthLoginResponse;
import com.thaua.ifrn.chamados.persistence.entities.TokenEntity;
import com.thaua.ifrn.chamados.persistence.entities.UserEntity;
import com.thaua.ifrn.chamados.persistence.mappers.AuthMapper;
import com.thaua.ifrn.chamados.persistence.mappers.UserMapper;
import com.thaua.ifrn.chamados.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final AuthMapper authMapper;

    public AuthLoginResponse login(LoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        UserEntity authenticatedUser = (UserEntity) authenticationManager.authenticate(authenticationToken).getPrincipal();


        authenticatedUser.getToken().setAccessToken(tokenService.generateToken(authenticatedUser));

        UserEntity savedUser = this.userRepository.save(authenticatedUser);

        return this.authMapper.map(savedUser.getToken());
    }

    public AuthLoginResponse register(RegisterRequest request) {
        UserEntity preSaved = this.authMapper.map(request);

        preSaved.setRole(Role.REQUESTER);
        preSaved.setPassword(this.passwordEncoder.encode(preSaved.getPassword()));
        preSaved.setRegistration(new Random().nextLong());

        UserEntity saved = this.userRepository.save(preSaved);

        saved.setToken(
                TokenEntity.builder()
                        .accessToken(this.tokenService.generateToken(saved))
                        .refreshToken(this.tokenService.generateToken(saved))
                        .user(saved)
                        .build()
        );

        saved = this.userRepository.save(saved);

        return authMapper.map(saved.getToken());
    }
}
