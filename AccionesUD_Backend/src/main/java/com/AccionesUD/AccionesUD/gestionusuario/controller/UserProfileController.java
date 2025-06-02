package com.AccionesUD.AccionesUD.gestionusuario.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.AccionesUD.AccionesUD.gestionusuario.dto.UpdateUserProfileRequest;
import com.AccionesUD.AccionesUD.gestionusuario.service.UserProfileService;
import com.AccionesUD.AccionesUD.usuario.entity.User;
import com.AccionesUD.AccionesUD.authentication.repository.UserRepository;

import lombok.RequiredArgsConstructor;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/user/profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;
    private final UserRepository userRepository;

    @PutMapping("/update")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Map<String, String>> updateUserProfile(@RequestBody UpdateUserProfileRequest request) {
        userProfileService.updateProfile(request);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Perfil actualizado exitosamente");
        return ResponseEntity.ok(response);
    }


    @GetMapping("/me")
    public ResponseEntity<UpdateUserProfileRequest> getMyProfile(Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        UpdateUserProfileRequest response = UpdateUserProfileRequest.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .username(user.getUsername())
                .phone(user.getPhone())
                .address(user.getAddress())
                .otpEnabled(user.isOtpEnabled())
                .dailyOrderLimit(user.getDailyOrderLimit())
                .build();

        return ResponseEntity.ok(response);
    }
}
