package com.AccionesUD.AccionesUD.exception.auth;

import com.AccionesUD.AccionesUD.exception.GlobalException;

public class TokenExpiredException extends GlobalException {
    public TokenExpiredException(String message) {
        super(message);
    }
}