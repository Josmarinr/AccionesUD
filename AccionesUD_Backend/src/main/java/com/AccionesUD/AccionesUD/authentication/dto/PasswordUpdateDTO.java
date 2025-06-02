package com.AccionesUD.AccionesUD.authentication.dto;

import lombok.Data;

@Data
public class PasswordUpdateDTO {
    private String token;
    private String newPassword;
}