package com.AccionesUD.AccionesUD.authentication.service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.AccionesUD.AccionesUD.authentication.dto.AuthResponse;
import com.AccionesUD.AccionesUD.authentication.dto.LoginRequest;
import com.AccionesUD.AccionesUD.authentication.dto.OtpRequest;
import com.AccionesUD.AccionesUD.authentication.dto.RegisterRequest;
import com.AccionesUD.AccionesUD.authentication.entity.Role;
import com.AccionesUD.AccionesUD.authentication.entity.User;
import com.AccionesUD.AccionesUD.authentication.repository.UserRepository;
import com.AccionesUD.AccionesUD.global.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;

    private static final long OTP_VALIDITY_MILLIS = 1 * 60 * 1000; // 5 minutos

    // Almacena código OTP y timestamp
    private final Map<String, OtpEntry> otpStorage = new ConcurrentHashMap<>();

    private record OtpEntry(String code, long timestamp) {}

    public String login(LoginRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        String otp = String.format("%06d", new Random().nextInt(999999));
        long now = System.currentTimeMillis();
        otpStorage.put(request.getUsername(), new OtpEntry(otp, now));

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        emailService.sendOtpEmail(user.getUsername(), otp); // Asumimos username == email

        return "OTP sent to your email";
    }

    public AuthResponse verifyOtp(OtpRequest request) {
        OtpEntry entry = otpStorage.get(request.getUsername());

        if (entry == null || !entry.code().equals(request.getOtp())) {
            throw new RuntimeException("Invalid OTP");
        }

        long now = System.currentTimeMillis();
        if (now - entry.timestamp() > OTP_VALIDITY_MILLIS) {
            otpStorage.remove(request.getUsername());
            throw new RuntimeException("OTP expired");
        }

        otpStorage.remove(request.getUsername());

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);

        return AuthResponse.builder().token(token).build();
    }

   public AuthResponse register(RegisterRequest request) {
    // Verificar si el username ya existe
    if (userRepository.existsByUsername(request.getUsername())) {
        throw new RuntimeException("El correo electrónico ya está registrado.");
    }

    // Verificar si el ID ya existe
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
        .build();

    userRepository.save(user);

    return AuthResponse.builder()
        .token(jwtService.getToken(user))
        .build();
}


}
