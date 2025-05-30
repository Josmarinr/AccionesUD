package com.AccionesUD.AccionesUD.exception.auth;

import com.AccionesUD.AccionesUD.exception.GlobalException;

public class InvalidOtpException extends GlobalException {
    public InvalidOtpException(String message) {
        super(message);
    }
}
