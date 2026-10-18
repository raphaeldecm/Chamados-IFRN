package com.thaua.ifrn.chamados.business.services;

import com.thaua.ifrn.chamados.http.exceptions.UserNotFoundException;
import com.thaua.ifrn.chamados.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByEmailAndActive(username)
                .orElseThrow(UserNotFoundException::new);
    }
}
