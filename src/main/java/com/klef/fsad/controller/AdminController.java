package com.klef.fsad.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.fsad.model.User;
import com.klef.fsad.service.ReportService;
import com.klef.fsad.service.UserService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private ReportService reportService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.viewAllUsers());
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        return ResponseEntity.ok("User Deleted Successfully");
    }

    @PutMapping("/user")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        return ResponseEntity.ok("User Updated Successfully");
    }

    @GetMapping("/stats")
    public Map<String, Long> getStats() {

        Map<String, Long> stats = new HashMap<>();

        stats.put("totalIssues", reportService.getTotalIssues());
        stats.put("open", reportService.getOpenIssues());
        stats.put("inProgress", reportService.getInProgressIssues());
        stats.put("resolved", reportService.getResolvedIssues());
        stats.put("feedback", reportService.getFeedbackCount());

        return stats;
    }
}