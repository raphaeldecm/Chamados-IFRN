package com.thaua.ifrn.chamados.business.services;

import com.thaua.ifrn.chamados.http.dto.response.users.UserResponse;
import com.thaua.ifrn.chamados.persistence.entities.UserEntity;
import com.thaua.ifrn.chamados.persistence.mappers.UserMapper;
import com.thaua.ifrn.chamados.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse me(UserEntity userEntity) {
        return this.userMapper.map(userEntity);
    }
}
