package com.example.wheater_app_project.exceptions;

public class AuthorizationFailureException extends RuntimeException {

    public AuthorizationFailureException() {
    }

    public AuthorizationFailureException(String message) {
        super(message);
    }
}
