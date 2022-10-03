package com.example.wheater_app_project.exceptions;

public class MissingUsernameOrPasswordException extends RuntimeException {

    public MissingUsernameOrPasswordException() {
    }

    public MissingUsernameOrPasswordException(String message) {
        super(message);
    }
}
