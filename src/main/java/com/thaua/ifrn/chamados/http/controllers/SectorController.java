package com.thaua.ifrn.chamados.http.controllers;

import com.thaua.ifrn.chamados.business.services.SectorService;
import com.thaua.ifrn.chamados.http.dto.request.sectors.CreateSectorRequest;
import com.thaua.ifrn.chamados.http.dto.response.sectors.SectorResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/sectors")
@RequiredArgsConstructor
public class SectorController {
    private final SectorService sectorService;

    @PostMapping
    public ResponseEntity<SectorResponse> create(
            @RequestBody CreateSectorRequest request
    ) {
        return ResponseEntity.ok(this.sectorService.create(request));
    }

    @GetMapping("/{sectorId}")
    public ResponseEntity<SectorResponse> fetch(@PathVariable UUID sectorId) {
        return ResponseEntity.ok(this.sectorService.fetch(sectorId));
    }

    @GetMapping
    public ResponseEntity<Page<SectorResponse>> list(
            @ParameterObject @PageableDefault Pageable pageable,
            @RequestParam String name,
            @RequestParam String acronym
    ) {
        return ResponseEntity.ok(this.sectorService.list(pageable, name, acronym));
    }
}
