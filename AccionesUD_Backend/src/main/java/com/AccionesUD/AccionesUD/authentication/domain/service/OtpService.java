package com.AccionesUD.AccionesUD.authentication.domain.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.AccionesUD.AccionesUD.authentication.domain.model.User;
import com.AccionesUD.AccionesUD.authentication.dto.AuthResponse;
import com.AccionesUD.AccionesUD.authentication.dto.OtpRequest;
import com.AccionesUD.AccionesUD.authentication.infrastructure.security.JwtService;
import com.AccionesUD.AccionesUD.authentication.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OtpService {

    private static final long OTP_VALIDITY_MILLIS = 1 * 60 * 1000;
    private final Map<String, OtpEntry> otpStorage = new ConcurrentHashMap<>();

    private final JwtService jwtService;
    private final UserRepository userRepository;

    private record OtpEntry(String code, long timestamp) {}

    public void storeOtp(String username, String code) {
        otpStorage.put(username, new OtpEntry(code, System.currentTimeMillis()));
    }

    public AuthResponse verifyOtp(OtpRequest request) {
        OtpEntry entry = otpStorage.get(request.getUsername());

        if (entry == null || !entry.code().equals(request.getOtp())) {
            throw new RuntimeException("Invalid OTP");
        }

        if (System.currentTimeMillis() - entry.timestamp() > OTP_VALIDITY_MILLIS) {
            otpStorage.remove(request.getUsername());
            throw new RuntimeException("OTP expired");
        }

        otpStorage.remove(request.getUsername());
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);

        return AuthResponse.builder().token(token).build();
    }
}
