package com.fuel50.microservicea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CallBController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${serviceB.url}")
    private String serviceBUrl;

    @GetMapping("/callB")
    public ResponseEntity<String> callServiceB() {
        ResponseEntity<String> response = restTemplate.getForEntity(serviceBUrl, String.class);
        return ResponseEntity.ok("Response from B: " + response.getBody());
    }
}