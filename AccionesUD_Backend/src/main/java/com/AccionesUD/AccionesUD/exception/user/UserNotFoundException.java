package com.AccionesUD.AccionesUD.exception.user;

import com.AccionesUD.AccionesUD.exception.GlobalException;

public class UserNotFoundException extends GlobalException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
