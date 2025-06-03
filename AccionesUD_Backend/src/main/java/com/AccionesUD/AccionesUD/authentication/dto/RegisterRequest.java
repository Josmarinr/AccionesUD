package com.AccionesUD.AccionesUD.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class RegisterRequest {
    Double id; //Cedula de identidad o pasaporte
    String username;
    String password;
    String firstname;
    String lastname;
    Double phone;
    String address;
}
