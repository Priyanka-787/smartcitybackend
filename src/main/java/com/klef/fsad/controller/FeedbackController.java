package com.klef.fsad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.fsad.dto.FeedbackDTO;
import com.klef.fsad.dto.FeedbackResponseDTO;
import com.klef.fsad.model.Feedback;
import com.klef.fsad.service.FeedbackService;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin("*")
public class FeedbackController {

    @Autowired
    private FeedbackService service;

    @PostMapping("/add")
    public ResponseEntity<FeedbackResponseDTO> add(@RequestBody FeedbackDTO dto) {
        return ResponseEntity.ok(service.addFeedback(dto));
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<List<Feedback>> getCity(@PathVariable int id) {
        return ResponseEntity.ok(service.getByCity(id));
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<FeedbackResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAllFeedback());
    }
}