package com.fuel50.microserviceb.controller;

import com.fuel50.microserviceb.entity.DemoEntity;
import com.fuel50.microserviceb.repository.DemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DataController.class)
class DataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DemoRepository demoRepository;

    @Test
    void testGetData() throws Exception {
        List<DemoEntity> entities = List.of(
                new DemoEntity("Test Data 1"),
                new DemoEntity("Test Data 2")
        );

        when(demoRepository.findAll()).thenReturn(entities);

        mockMvc.perform(get("/data")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Data 1"))
                .andExpect(jsonPath("$[1].name").value("Test Data 2"));
    }
}