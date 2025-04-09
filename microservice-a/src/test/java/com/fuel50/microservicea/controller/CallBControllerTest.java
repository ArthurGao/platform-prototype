package com.fuel50.microservicea.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CallBController.class)
@TestPropertySource(properties = "serviceB.url=http://dummy-service/data")
class CallBControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void testCallServiceB() throws Exception {
        String dummyResponse = "dummy data";
        ResponseEntity<String> stubResponse = new ResponseEntity<>(dummyResponse, HttpStatus.OK);
        when(restTemplate.getForEntity("http://dummy-service/data", String.class))
                .thenReturn(stubResponse);

        mockMvc.perform(get("/callB"))
                .andExpect(status().isOk())
                .andExpect(content().string("Response from B: " + dummyResponse));
    }
}