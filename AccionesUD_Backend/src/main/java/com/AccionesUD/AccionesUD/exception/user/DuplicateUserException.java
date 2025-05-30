package com.AccionesUD.AccionesUD.exception.user;

import com.AccionesUD.AccionesUD.exception.GlobalException;

public class DuplicateUserException extends GlobalException {
    public DuplicateUserException(String message) {
        super(message);
    }
}
