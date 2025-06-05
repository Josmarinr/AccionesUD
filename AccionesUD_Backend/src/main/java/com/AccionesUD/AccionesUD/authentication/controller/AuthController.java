package com.AccionesUD.AccionesUD.authentication.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.AccionesUD.AccionesUD.authentication.application.AuthApplicationService;
import com.AccionesUD.AccionesUD.authentication.dto.AuthResponse;
import com.AccionesUD.AccionesUD.authentication.dto.LoginRequest;
import com.AccionesUD.AccionesUD.authentication.dto.OtpRequest;
import com.AccionesUD.AccionesUD.authentication.dto.RegisterRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

private final AuthApplicationService authApplicationService;

// Paso 1: autentica y envía OTP al correo
@PostMapping("login")
public ResponseEntity<Object> login(@RequestBody LoginRequest request) {
return ResponseEntity.ok(authApplicationService.login(request));
}

// Paso 2: verifica OTP y entrega el JWT
@PostMapping("verify-otp")
public ResponseEntity<AuthResponse> verifyOtp(@RequestBody OtpRequest request) {
return ResponseEntity.ok(authApplicationService.verifyOtp(request));
}

// Registro
@PostMapping("register")
public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
try {
return ResponseEntity.ok(authApplicationService.register(request));
} catch (RuntimeException e) {
return ResponseEntity.badRequest().body(e.getMessage());
}
}
}
