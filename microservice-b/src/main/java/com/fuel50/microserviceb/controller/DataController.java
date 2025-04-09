package com.fuel50.microserviceb.controller;


import com.fuel50.microserviceb.entity.DemoEntity;
import com.fuel50.microserviceb.repository.DemoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataController {

    private final DemoRepository repository;

    public DataController(DemoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/data")
    public List<DemoEntity> getData() {
        return repository.findAll();
    }
}