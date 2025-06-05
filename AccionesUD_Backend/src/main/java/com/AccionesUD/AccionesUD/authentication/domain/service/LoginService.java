package com.AccionesUD.AccionesUD.authentication.domain.service;

import java.util.Random;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.AccionesUD.AccionesUD.authentication.domain.model.User;
import com.AccionesUD.AccionesUD.authentication.dto.LoginRequest;
import com.AccionesUD.AccionesUD.authentication.infrastructure.email.EmailService;
import com.AccionesUD.AccionesUD.authentication.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final OtpService otpService;

    public String login(LoginRequest request) {
    authenticationManager.authenticate(
    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
);

        String otp = String.format("%06d", new Random().nextInt(999999));
        otpService.storeOtp(request.getUsername(), otp);

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        emailService.sendOtpEmail(user.getUsername(), otp);

        return "OTP sent to your email";
    }
}
