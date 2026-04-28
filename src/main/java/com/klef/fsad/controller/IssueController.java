package com.klef.fsad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klef.fsad.dto.IssueDTO;
import com.klef.fsad.model.Issue;
import com.klef.fsad.service.IssueService;

@RestController
@RequestMapping("/api/issue")
@CrossOrigin("*")
public class IssueController {

    @Autowired
    private IssueService service;

    @PostMapping("/report")
    public ResponseEntity<?> report(@RequestBody IssueDTO dto) {
        return ResponseEntity.ok(service.reportIssue(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Issue>> getAll() {
        return ResponseEntity.ok(service.getAllIssues());
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<List<Issue>> getByCity(@PathVariable int id) {
        return ResponseEntity.ok(service.getIssuesByCity(id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Issue>> getByUser(@PathVariable int id) {
        return ResponseEntity.ok(service.getIssuesByUser(id));
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable int id,
        @RequestParam String status) {
        return ResponseEntity.ok(service.updateStatus(id, status));
    }
}