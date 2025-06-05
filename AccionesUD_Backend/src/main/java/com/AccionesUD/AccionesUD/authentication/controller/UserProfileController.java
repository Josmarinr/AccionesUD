package com.AccionesUD.AccionesUD.authentication.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AccionesUD.AccionesUD.authentication.application.UserProfileApplicationService;
import com.AccionesUD.AccionesUD.authentication.dto.UpdateUserProfileRequest;
import org.springframework.security.core.Authentication;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user/profile")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserProfileController {
    private final UserProfileApplicationService appService;

    @PutMapping("/update")
    public ResponseEntity<Map<String, String>> updateUserProfile(@RequestBody UpdateUserProfileRequest request) {
        appService.updateUserProfile(request);
        return ResponseEntity.ok(Map.of("message", "Perfil actualizado exitosamente"));
    }

    @GetMapping("/me")
    public ResponseEntity<UpdateUserProfileRequest> getMyProfile(Authentication auth) {
        return ResponseEntity.ok(appService.getProfile(auth.getName()));
    }
}