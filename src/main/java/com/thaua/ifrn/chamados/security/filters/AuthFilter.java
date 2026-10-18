package com.thaua.ifrn.chamados.security.filters;

import com.thaua.ifrn.chamados.business.services.TokenService;
import com.thaua.ifrn.chamados.persistence.entities.UserEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {
    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        String token = null;
        String email = null;

        if (authorization != null && authorization.startsWith("Bearer")) {
            token = authorization.substring(7);
            email = tokenService.extractEmail(token);
        }

        if (token != null && email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserEntity userFound = this.tokenService.findUserByToken(token);

            Authentication authenticatedUser = new UsernamePasswordAuthenticationToken(userFound, null, userFound.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticatedUser);

        }

        this.doFilterInternal(request, response, filterChain);
    }
}
