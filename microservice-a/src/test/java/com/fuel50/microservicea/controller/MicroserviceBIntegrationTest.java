package com.fuel50.microservicea.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MicroserviceBIntegrationTest {

    @Value("${serviceB.url}")
    private String serviceBUrl;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetData() {
        ResponseEntity<String> response = restTemplate.getForEntity(serviceBUrl, String.class);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        String body = response.getBody();
        assertThat(body).contains("Sample Data 1");
        assertThat(body).contains("Sample Data 2");
    }
}