package com.thaua.ifrn.chamados.business.services;

import com.thaua.ifrn.chamados.http.dto.request.sectors.CreateSectorRequest;
import com.thaua.ifrn.chamados.http.dto.response.sectors.SectorResponse;
import com.thaua.ifrn.chamados.http.exceptions.SectorNotFoundException;
import com.thaua.ifrn.chamados.persistence.entities.SectorEntity;
import com.thaua.ifrn.chamados.persistence.mappers.SectorMapper;
import com.thaua.ifrn.chamados.persistence.repositories.SectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SectorService {
    private final SectorRepository sectorRepository;
    private final SectorMapper sectorMapper;

    public SectorResponse create(CreateSectorRequest request) {
        SectorEntity preSaved = this.sectorMapper.map(request);
        return this.sectorMapper.map(this.sectorRepository.save(preSaved));
    }

    public SectorResponse fetch(UUID sectorId) {
        return this.sectorMapper.map(
                this.sectorRepository.findById(sectorId)
                        .orElseThrow(SectorNotFoundException::new)
        );
    }

    public Page<SectorResponse> list(Pageable pageable, String name, String acronym) {
        Specification<SectorEntity> byName = ((root, query, criteriaBuilder) ->
                name == null || name.isBlank()
                ? criteriaBuilder.conjunction()
                : criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + name.toLowerCase() + "%"
                        )
                );

        Specification<SectorEntity> byAcronym = ((root, query, criteriaBuilder) ->
                acronym == null || acronym.isBlank()
                        ? criteriaBuilder.conjunction()
                        : criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("acronym")),
                        "%" + acronym.toLowerCase() + "%"
                )
        );


        Specification<SectorEntity> specification = byName.and(byAcronym);


        return this.sectorRepository.findAll(specification, pageable)
                .map(this.sectorMapper::map);
    }
}
