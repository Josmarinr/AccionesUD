package com.AccionesUD.AccionesUD.authentication.application;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.AccionesUD.AccionesUD.authentication.domain.service.LoginService;
import com.AccionesUD.AccionesUD.authentication.domain.service.OtpService;
import com.AccionesUD.AccionesUD.authentication.domain.service.UserRegistrationService;
import com.AccionesUD.AccionesUD.authentication.dto.AuthResponse;
import com.AccionesUD.AccionesUD.authentication.dto.LoginRequest;
import com.AccionesUD.AccionesUD.authentication.dto.OtpRequest;
import com.AccionesUD.AccionesUD.authentication.dto.RegisterRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthApplicationService {
    
    private final LoginService loginService;
    private final OtpService otpService;
    private final UserRegistrationService registrationService;

    public Object login(LoginRequest request) {
        return loginService.login(request); // Devuelve el resultado directamente
    }

    public AuthResponse verifyOtp(OtpRequest request) {
        return otpService.verifyOtp(request);
    }

    public AuthResponse register(RegisterRequest request) {
        return registrationService.register(request);
    }
}
