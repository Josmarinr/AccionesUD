package com.AccionesUD.AccionesUD.authentication.domain.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.AccionesUD.AccionesUD.authentication.domain.model.Role;
import com.AccionesUD.AccionesUD.authentication.domain.model.User;
import com.AccionesUD.AccionesUD.authentication.dto.AuthResponse;
import com.AccionesUD.AccionesUD.authentication.dto.RegisterRequest;
import com.AccionesUD.AccionesUD.authentication.infrastructure.security.JwtService;
import com.AccionesUD.AccionesUD.authentication.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("El correo electrónico ya está registrado.");
        }

        if (userRepository.existsById(request.getId())) {
            throw new RuntimeException("Ya existe un usuario con esta identificación.");
        }

        User user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .firstname(request.getFirstname())
            .lastname(request.getLastname())
            .id(request.getId())
            .phone(request.getPhone())
            .address(request.getAddress())
            .role(Role.USER)
            .dailyOrderLimit(request.getDailyOrderLimit())
            .otpEnabled(request.isOtpEnabled())
            .build();

        userRepository.save(user);

        return AuthResponse.builder().token(jwtService.getToken(user)).build();
    }
}
