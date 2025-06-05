package com.AccionesUD.AccionesUD.domain.service.auth;

import java.util.Random;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.AccionesUD.AccionesUD.domain.model.User;
import com.AccionesUD.AccionesUD.dto.auth.AuthResponse;
import com.AccionesUD.AccionesUD.dto.auth.LoginRequest;
import com.AccionesUD.AccionesUD.repository.UserRepository;
import com.AccionesUD.AccionesUD.utilities.email.EmailService;
import com.AccionesUD.AccionesUD.utilities.security.JwtService;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final OtpService otpService;
    private final JwtService jwtService;

    public Object login(LoginRequest request) {
        // Autenticación básica (usuario/contraseña)
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // Obtener usuario
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Si el OTP está activado
        if (user.isOtpEnabled()) {
            String otp = String.format("%06d", new Random().nextInt(999999));
            otpService.storeOtp(user.getUsername(), otp);
            emailService.sendOtpEmail(user.getUsername(), otp);
            return Map.of("message", "OTP sent to your email");
        }

        // Si el OTP no está activado, devolver JWT directamente
        String token = jwtService.getToken(user);
        return AuthResponse.builder().token(token).build();
    }
}
