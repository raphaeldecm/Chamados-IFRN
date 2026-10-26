package com.thaua.ifrn.chamados.persistence.mappers;

import com.thaua.ifrn.chamados.domain.sectors.UpdateSector;
import com.thaua.ifrn.chamados.http.dto.request.sectors.CreateSectorRequest;
import com.thaua.ifrn.chamados.http.dto.request.sectors.UpdateSectorRequest;
import com.thaua.ifrn.chamados.http.dto.response.sectors.SectorResponse;
import com.thaua.ifrn.chamados.persistence.entities.SectorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SectorMapper {
    SectorEntity map(CreateSectorRequest request);
    SectorResponse map(SectorEntity entity);
    UpdateSector map(UpdateSectorRequest request);
}
