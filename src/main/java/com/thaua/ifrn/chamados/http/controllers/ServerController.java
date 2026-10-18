package com.thaua.ifrn.chamados.http.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server")
public class ServerController {
    @GetMapping
    public ResponseEntity<String> serverIsRunning() {
        return ResponseEntity.ok("server is running");
    }

}
