package com.thaua.ifrn.chamados.business.services;

import com.thaua.ifrn.chamados.http.dto.request.auth.LoginRequest;
import com.thaua.ifrn.chamados.http.dto.response.auth.AuthLoginResponse;
import com.thaua.ifrn.chamados.persistence.entities.UserEntity;
import com.thaua.ifrn.chamados.persistence.mappers.AuthMapper;
import com.thaua.ifrn.chamados.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final AuthMapper authMapper;

    public AuthLoginResponse login(LoginRequest request) {
        UserEntity authenticatedUser = (UserEntity) authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));


        authenticatedUser.getToken().setAccessToken(tokenService.generateToken(authenticatedUser));

        UserEntity savedUser = this.userRepository.save(authenticatedUser);

        return this.authMapper.map(savedUser.getToken());
    }
}
