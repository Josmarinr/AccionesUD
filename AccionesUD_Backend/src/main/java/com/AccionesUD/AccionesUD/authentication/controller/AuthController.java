package com.AccionesUD.AccionesUD.authentication.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.AccionesUD.AccionesUD.authentication.dto.AuthResponse;
import com.AccionesUD.AccionesUD.authentication.dto.LoginRequest;
import com.AccionesUD.AccionesUD.authentication.dto.OtpRequest;
import com.AccionesUD.AccionesUD.authentication.dto.RegisterRequest;
import com.AccionesUD.AccionesUD.authentication.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200") 
public class AuthController {

    private final AuthService authService;

    // Paso 1: autentica y envía OTP al correo
    @PostMapping("login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
        authService.login(request);
        Map<String, String> response = new HashMap<>();
        response.put("message", "OTP sent to your email");
        return ResponseEntity.ok(response);
    }


    // Paso 2: verifica OTP y entrega el JWT
    @PostMapping("verify-otp")
    public ResponseEntity<AuthResponse> verifyOtp(@RequestBody OtpRequest request) {
        return ResponseEntity.ok(authService.verifyOtp(request));
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            return ResponseEntity.ok(authService.register(request));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
