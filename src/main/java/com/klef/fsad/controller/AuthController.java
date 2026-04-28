package com.klef.fsad.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klef.fsad.model.Admin;
import com.klef.fsad.model.User;
import com.klef.fsad.security.JWTUtilizer;
import com.klef.fsad.service.AdminService;
import com.klef.fsad.service.EmailService;
import com.klef.fsad.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private JWTUtilizer jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> req) {

        String email = req.get("email");
        String password = req.get("password");

        Map<String, Object> res = new HashMap<>();

        User user = userService.checkUserLogin(email, password);

        if (user != null) {

            if (!"ACTIVE".equals(user.getAccountStatus())) {
                res.put("message", "Account is blocked");
                return ResponseEntity.status(403).body(res);
            }

            if (user.isTwoFactorEnabled()) {
                emailService.sendOtpToUser(email);
                res.put("message", "OTP sent");
                res.put("role", "USER");
                res.put("email", email);
                res.put("twoFactor", true);
                return ResponseEntity.ok(res);
            }

            String token = jwtUtil.generateJWTToken(email, "USER");

            res.put("token", token);
            res.put("role", "USER");
            res.put("id", user.getId());
            res.put("name", user.getName());
            res.put("message", "Login successful");

            return ResponseEntity.ok(res);
        }
        
        Admin admin = adminService.checkAdminLogin(email, password);

        if (admin != null) {

            if (admin.isTwoFactorEnabled()) {
                emailService.sendOtpToAdmin(email);
                res.put("message", "OTP sent");
                res.put("role", "ADMIN");
                res.put("email", email);
                res.put("twoFactor", true);
                return ResponseEntity.ok(res);
            }

            String token = jwtUtil.generateJWTToken(email, "ADMIN");

            res.put("token", token);
            res.put("role", "ADMIN");
            res.put("id", admin.getId());
            res.put("name", admin.getName());
            res.put("message", "Login successful");

            return ResponseEntity.ok(res);
        }

        res.put("message", "Invalid credentials");
        return ResponseEntity.status(401).body(res);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody Map<String, String> req) {

        String email = req.get("email");
        String otp = req.get("otp");
        String role = req.get("role");

        Map<String, Object> res = new HashMap<>();

        String result = "";

        if ("USER".equalsIgnoreCase(role)) {
            result = emailService.verifyOtpUser(email, otp);

            if ("OTP Verified".equals(result)) {
                String token = jwtUtil.generateJWTToken(email, "USER");
                res.put("token", token);
                res.put("role", "USER");
                return ResponseEntity.ok(res);
            }
        }

        else if ("ADMIN".equalsIgnoreCase(role)) {
            result = emailService.verifyOtpAdmin(email, otp);

            if ("OTP Verified".equals(result)) {
                String token = jwtUtil.generateJWTToken(email, "ADMIN");
                res.put("token", token);
                res.put("role", "ADMIN");
                return ResponseEntity.ok(res);
            }
        }

        res.put("message", result);
        return ResponseEntity.status(400).body(res);
    }

    // 🔁 FORGOT PASSWORD - SEND OTP
    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOtp(@RequestBody Map<String, String> req) {

        String email = req.get("email");

        Map<String, String> res = new HashMap<>();

        if (userService.getUserByEmail(email) != null) {
            res.put("message", emailService.sendOtpToUser(email));
            res.put("role", "USER");
            return ResponseEntity.ok(res);
        }

        if (adminService.getAdminByEmail(email) != null) {
            res.put("message", emailService.sendOtpToAdmin(email));
            res.put("role", "ADMIN");
            return ResponseEntity.ok(res);
        }

        res.put("message", "Email not found");
        return ResponseEntity.status(404).body(res);
    }

    // 🔄 RESET PASSWORD
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> req) {

        String email = req.get("email");
        String password = req.get("password");
        String role = req.get("role");

        Map<String, String> res = new HashMap<>();

        if ("USER".equalsIgnoreCase(role)) {
            res.put("message", emailService.resetPasswordUser(email, password));
        } else {
            res.put("message", emailService.resetPasswordAdmin(email, password));
        }

        return ResponseEntity.ok(res);
    }
}