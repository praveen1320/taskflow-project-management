
package com.taskflow_backend.controller;

import com.taskflow_backend.dto.AuthResponseDTO;
import com.taskflow_backend.dto.LoginRequestDTO;
import com.taskflow_backend.dto.RegisterRequestDTO;
import com.taskflow_backend.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(
            @Valid @RequestBody RegisterRequestDTO request) {

        String response = authService.registerUser(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> loginUser(
            @Valid @RequestBody LoginRequestDTO request) {

        AuthResponseDTO response = authService.loginUser(request);

        return ResponseEntity.ok(response);
    }
}
