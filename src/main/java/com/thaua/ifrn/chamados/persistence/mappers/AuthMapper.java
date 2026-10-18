package com.thaua.ifrn.chamados.persistence.mappers;

import com.thaua.ifrn.chamados.http.dto.response.auth.AuthLoginResponse;
import com.thaua.ifrn.chamados.persistence.entities.TokenEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    AuthLoginResponse map(TokenEntity entity);
}
