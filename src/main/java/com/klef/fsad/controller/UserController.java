package com.klef.fsad.controller;

import java.util.List;
import java.util.Map;

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

import com.klef.fsad.dto.UserRegisterDTO;
import com.klef.fsad.model.User;
import com.klef.fsad.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/dashboard")
    public ResponseEntity<?> getDashboardStats() {
        return ResponseEntity.ok(userService.getDashboardStats());
    }
    
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDTO dto) {

        User user = new User();

        user.setName(dto.name);
        user.setGender(dto.gender);
        user.setEmail(dto.email);
        user.setPassword(dto.password);
        user.setContact(dto.contact);
        user.setLocation(dto.location);

        return ResponseEntity.ok(userService.registerUser(user));
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.viewAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        return ResponseEntity.ok(userService.viewUserById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody User u) {

        String msg = userService.updateProfile(
            id, u.getName(), u.getEmail(),
            u.getGender(), u.getContact(), u.getLocation()
        );

        return ResponseEntity.ok(msg);
    }

    @PutMapping("/password/{id}")
    public ResponseEntity<?> changePassword(@PathVariable int id,
        @RequestBody Map<String,String> req) {

        String msg = userService.changePassword(
            id, req.get("oldPassword"), req.get("newPassword")
        );

        return ResponseEntity.ok(msg);
    }

    @PutMapping("/2fa/{id}")
    public ResponseEntity<?> toggle2FA(@PathVariable int id,
        @RequestParam boolean enabled) {

        return ResponseEntity.ok(userService.toggle2FA(id, enabled));
    }
}