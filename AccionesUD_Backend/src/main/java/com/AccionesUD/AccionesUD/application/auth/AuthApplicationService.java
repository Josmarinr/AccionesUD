package com.AccionesUD.AccionesUD.application.auth;


import org.springframework.stereotype.Service;

import com.AccionesUD.AccionesUD.domain.service.auth.LoginService;
import com.AccionesUD.AccionesUD.domain.service.auth.OtpService;
import com.AccionesUD.AccionesUD.domain.service.auth.UserRegistrationService;
import com.AccionesUD.AccionesUD.dto.auth.AuthResponse;
import com.AccionesUD.AccionesUD.dto.auth.LoginRequest;
import com.AccionesUD.AccionesUD.dto.auth.OtpRequest;
import com.AccionesUD.AccionesUD.dto.auth.RegisterRequest;

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
