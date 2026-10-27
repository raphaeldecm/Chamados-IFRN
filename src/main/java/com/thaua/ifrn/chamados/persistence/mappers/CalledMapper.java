package com.thaua.ifrn.chamados.persistence.mappers;

import com.thaua.ifrn.chamados.http.dto.request.calleds.CalledRequest;
import com.thaua.ifrn.chamados.http.dto.response.calleds.CalledResponse;
import com.thaua.ifrn.chamados.persistence.entities.CalledEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CalledMapper {
    CalledEntity map(CalledRequest request);
    CalledResponse map(CalledEntity entity);
}
