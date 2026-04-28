package com.klef.fsad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klef.fsad.model.Infrastructure;
import com.klef.fsad.service.InfrastructureService;

@RestController
@RequestMapping("/api/infrastructure")
@CrossOrigin("*")
public class InfrastructureController {

    @Autowired
    private InfrastructureService service;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Infrastructure infra) {
        return ResponseEntity.ok(service.addInfrastructure(infra));
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<List<Infrastructure>> get(@PathVariable int id) {
        return ResponseEntity.ok(service.getByCity(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return ResponseEntity.ok(service.deleteInfrastructure(id));
    }
}