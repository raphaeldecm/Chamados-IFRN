package com.thaua.ifrn.chamados.business.services;

import com.thaua.ifrn.chamados.http.dto.request.sectors.CreateSectorRequest;
import com.thaua.ifrn.chamados.http.dto.response.sectors.SectorResponse;
import com.thaua.ifrn.chamados.persistence.entities.SectorEntity;
import com.thaua.ifrn.chamados.persistence.mappers.SectorMapper;
import com.thaua.ifrn.chamados.persistence.repositories.SectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SectorService {
    private final SectorRepository sectorRepository;
    private final SectorMapper sectorMapper;

    public SectorResponse create(CreateSectorRequest request) {
        SectorEntity preSaved = this.sectorMapper.map(request);

        return this.sectorMapper.map(this.sectorRepository.save(preSaved));
    }
}
