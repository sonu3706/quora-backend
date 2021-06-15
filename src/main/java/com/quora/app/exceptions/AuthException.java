package com.quora.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class AuthException {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class UserNotFound extends RuntimeException {
        public UserNotFound(String message) {
            super(message);
        }
    }
    @ResponseStatus(HttpStatus.CONFLICT)
    public static class PasswordMisMatch extends RuntimeException {
        public PasswordMisMatch(String message) {
            super(message);
        }
    }
    @ResponseStatus(HttpStatus.CONFLICT)
    public static class UserAlreadyExists extends RuntimeException {
        public UserAlreadyExists(String message) {
            super(message);
        }
    }

    public static class PasswordSame extends RuntimeException {
        public PasswordSame(String message) {
            super(message);
        }
    }
}
