package com.thaua.ifrn.chamados.http.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/server")
public class ServerController {
    @GetMapping("/50")
    public ResponseEntity<String> serverIsRunning() {
        Random random = new Random();
        int number = random.nextInt(0, 2);
        return ResponseEntity.status((number == 0) ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body("server is running");
    }

    @GetMapping
    public ResponseEntity<String> serverIsRunningg() {
        return ResponseEntity.ok("server is running");
    }

}
