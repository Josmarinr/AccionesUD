package com.AccionesUD.AccionesUD.authentication.dto;

import lombok.Data;

@Data
public class OtpRequest {
    private String username;
    private String otp;
}
