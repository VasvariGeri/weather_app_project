package com.example.wheater_app_project.exceptions;

public class InvalidRegistrationRequestException extends RuntimeException {

    public InvalidRegistrationRequestException() {
    }

    public InvalidRegistrationRequestException(String message) {
        super(message);
    }
}
