package com.fuel50.microserviceb.controller;

import com.fuel50.microserviceb.entity.DemoEntity;
import com.fuel50.microserviceb.repository.DemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class DataControllerIntegrationTest {

    @Container
    public static PostgreSQLContainer<?> postgresContainer =
            new PostgreSQLContainer<>("postgres:14")
                    .withDatabaseName("demodb")
                    .withUsername("demo")
                    .withPassword("demo");

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
        registry.add("spring.datasource.driver-class-name", postgresContainer::getDriverClassName);
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DemoRepository demoRepository;

    @Test
    public void testDataController() {
        demoRepository.deleteAll();
        demoRepository.saveAll(List.of(
                new DemoEntity("Test Data 1"),
                new DemoEntity("Test Data 2")
        ));

        ResponseEntity<String> response = restTemplate.getForEntity("/data", String.class);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();

        String body = response.getBody();
        assertThat(body).contains("Test Data 1");
        assertThat(body).contains("Test Data 2");
    }
}