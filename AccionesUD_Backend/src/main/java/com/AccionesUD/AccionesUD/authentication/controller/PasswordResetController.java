package com.AccionesUD.AccionesUD.authentication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AccionesUD.AccionesUD.authentication.dto.PasswordResetRequestDTO;
import com.AccionesUD.AccionesUD.authentication.dto.PasswordResetTokenDTO;
import com.AccionesUD.AccionesUD.authentication.dto.PasswordUpdateDTO;
import com.AccionesUD.AccionesUD.authentication.service.PasswordResetService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth/password")
@RequiredArgsConstructor
public class PasswordResetController {
    private final PasswordResetService passwordResetService;

@PostMapping("/request")
public ResponseEntity<String> requestReset(@RequestBody PasswordResetRequestDTO dto) {
    passwordResetService.generateToken(dto);
    return ResponseEntity.ok("Correo de recuperación enviado.");
}

@PostMapping("/validate")
public ResponseEntity<String> validateToken(@RequestBody PasswordResetTokenDTO dto) {
    passwordResetService.validateToken(dto.getToken());
    return ResponseEntity.ok("Token válido.");
}

@PostMapping("/update")
public ResponseEntity<String> updatePassword(@RequestBody PasswordUpdateDTO dto) {
    passwordResetService.updatePassword(dto);
    return ResponseEntity.ok("Contraseña actualizada correctamente.");
}
}