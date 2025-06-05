package com.AccionesUD.AccionesUD.dto.auth;

import lombok.Data;

@Data
public class OtpRequest {
    private String username;
    private String otp;
}
