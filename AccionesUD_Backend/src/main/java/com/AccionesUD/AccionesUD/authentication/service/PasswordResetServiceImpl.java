package com.AccionesUD.AccionesUD.authentication.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.AccionesUD.AccionesUD.authentication.dto.PasswordResetRequestDTO;
import com.AccionesUD.AccionesUD.authentication.dto.PasswordUpdateDTO;
import com.AccionesUD.AccionesUD.usuario.entity.PasswordResetToken;
import com.AccionesUD.AccionesUD.usuario.entity.User;
import com.AccionesUD.AccionesUD.usuario.repository.PasswordResetTokenRepository;
import com.AccionesUD.AccionesUD.usuario.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
    public class PasswordResetServiceImpl implements PasswordResetService {
    private final PasswordResetTokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void generateToken(PasswordResetRequestDTO request) {
        String email = request.getEmail();
        userRepository.findByUsername(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        tokenRepository.deleteByEmail(email); // borra anteriores
        String token = UUID.randomUUID().toString();

        PasswordResetToken resetToken = PasswordResetToken.builder()
                .token(token)
                .email(email)
                .expiration(LocalDateTime.now().plusMinutes(15))
                .used(false)
                .build();

        tokenRepository.save(resetToken);
        String link = "http://localhost:4200/reset-password?token=" + token;
        emailService.sendEmail(email, "Recuperación de contraseña", "Da clic en el enlace: " + link);
    }

    @Override
    public void validateToken(String token) {
        PasswordResetToken resetToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token inválido"));

        if (resetToken.isUsed() || resetToken.getExpiration().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expirado o ya usado");
        }
    }

    @Override
    public void updatePassword(PasswordUpdateDTO request) {
        PasswordResetToken resetToken = tokenRepository.findByToken(request.getToken())
                .orElseThrow(() -> new RuntimeException("Token inválido"));

        if (resetToken.isUsed() || resetToken.getExpiration().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expirado o ya usado");
        }

        User user = userRepository.findByUsername(resetToken.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        resetToken.setUsed(true);
        tokenRepository.save(resetToken);
    }


}