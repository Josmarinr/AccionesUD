package com.AccionesUD.AccionesUD.exception.auth;

import com.AccionesUD.AccionesUD.exception.GlobalException;

public class OtpExpiredException extends GlobalException {
    public OtpExpiredException(String message) {
        super(message);
    }
}
