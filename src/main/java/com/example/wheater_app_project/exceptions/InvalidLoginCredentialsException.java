package com.example.wheater_app_project.exceptions;

public class InvalidLoginCredentialsException extends RuntimeException {

    public InvalidLoginCredentialsException() {
    }

    public InvalidLoginCredentialsException(String message) {
        super(message);
    }
}
