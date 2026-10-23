package com.thaua.ifrn.chamados.http.controllers;

import com.thaua.ifrn.chamados.business.services.SectorService;
import com.thaua.ifrn.chamados.http.dto.request.sectors.CreateSectorRequest;
import com.thaua.ifrn.chamados.http.dto.response.sectors.SectorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
