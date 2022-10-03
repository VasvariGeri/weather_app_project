package com.example.wheater_app_project.exceptions;

public class UsernameTakenException extends RuntimeException {

    public UsernameTakenException() {
    }

    public UsernameTakenException(String message) {
        super(message);
    }
}
