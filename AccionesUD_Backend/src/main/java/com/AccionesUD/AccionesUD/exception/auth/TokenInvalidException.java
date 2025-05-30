package com.AccionesUD.AccionesUD.exception.auth;

import com.AccionesUD.AccionesUD.exception.GlobalException;

public class TokenInvalidException extends GlobalException {
    public TokenInvalidException(String message) {
        super(message);
    }
}