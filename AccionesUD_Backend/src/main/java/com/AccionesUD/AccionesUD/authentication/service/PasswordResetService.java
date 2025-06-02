package com.AccionesUD.AccionesUD.authentication.service;

import com.AccionesUD.AccionesUD.authentication.dto.PasswordResetRequestDTO;
import com.AccionesUD.AccionesUD.authentication.dto.PasswordUpdateDTO;

public interface PasswordResetService {
    void generateToken(PasswordResetRequestDTO request);
    void validateToken(String token);
    void updatePassword(PasswordUpdateDTO request);
}