package com.thaua.ifrn.chamados.business.services;

import com.thaua.ifrn.chamados.exceptions.UserNotFoundException;
import com.thaua.ifrn.chamados.persistence.entities.UserEntity;
import com.thaua.ifrn.chamados.persistence.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final PublicKey publicKey;
    private final UserRepository userRepository;

    public UserEntity findUserByToken(String token) {
        return userRepository.findActiveUserByValidToken(token)
                .orElseThrow(UserNotFoundException::new);
    }

    public String extractEmail(String token) {
        return this.extractClaims(token, Claims::getSubject);
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsFunction) {
        return claimsFunction.apply(this.extractAllClaims(token));
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(this.publicKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
