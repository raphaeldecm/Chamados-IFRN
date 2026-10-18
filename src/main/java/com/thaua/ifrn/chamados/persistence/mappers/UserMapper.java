package com.thaua.ifrn.chamados.persistence.mappers;

import com.thaua.ifrn.chamados.http.dto.request.auth.RegisterRequest;
import com.thaua.ifrn.chamados.http.dto.response.auth.AuthLoginResponse;
import com.thaua.ifrn.chamados.http.dto.response.users.UserResponse;
import com.thaua.ifrn.chamados.persistence.entities.TokenEntity;
import com.thaua.ifrn.chamados.persistence.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse map(UserEntity userEntity);
}
