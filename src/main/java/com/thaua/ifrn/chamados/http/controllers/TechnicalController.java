package com.thaua.ifrn.chamados.http.controllers;

import com.thaua.ifrn.chamados.http.dto.request.technicals.CreateTechnicalRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/technicals")
@RequiredArgsConstructor
public class TechnicalController {
}
