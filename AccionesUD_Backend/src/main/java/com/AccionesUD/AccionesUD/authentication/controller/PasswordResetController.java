package com.AccionesUD.AccionesUD.authentication.controller;

import com.AccionesUD.AccionesUD.authentication.application.PasswordResetApplicationService;
import com.AccionesUD.AccionesUD.authentication.dto.PasswordResetRequestDTO;
import com.AccionesUD.AccionesUD.authentication.dto.PasswordResetTokenDTO;
import com.AccionesUD.AccionesUD.authentication.dto.PasswordUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth/password")
@RequiredArgsConstructor
public class PasswordResetController {
    private final PasswordResetApplicationService appService;

@PostMapping("/request")
public ResponseEntity<Map<String, String>> requestReset(@RequestBody PasswordResetRequestDTO dto) {
    appService.requestReset(dto);
    return ResponseEntity.ok(Map.of("message", "Correo de recuperación enviado."));
}

@PostMapping("/validate")
public ResponseEntity<Map<String, String>> validateToken(@RequestBody PasswordResetTokenDTO dto) {
    appService.validateToken(dto.getToken());
    return ResponseEntity.ok(Map.of("message", "Token válido."));
}

@PostMapping("/update")
public ResponseEntity<Map<String, String>> updatePassword(@RequestBody PasswordUpdateDTO dto) {
    appService.updatePassword(dto);
    return ResponseEntity.ok(Map.of("message", "Contraseña actualizada correctamente."));
}

}