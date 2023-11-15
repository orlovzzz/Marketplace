package com.example.controller;

import com.example.DTO.User;
import com.example.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/new/")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        String response = authService.register(user);
        HttpStatus status = response.equals("Success") ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, status);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user, HttpServletResponse response) {
        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + authService.login(user));
        return ResponseEntity.ok("Successful");
    }

    @PostMapping("/becomeSeller")
    public ResponseEntity<?> becomeSeller(@RequestBody Map<String, String> request) {
        return ResponseEntity.ok(authService.becomeSeller(request.get("login")));
    }
}