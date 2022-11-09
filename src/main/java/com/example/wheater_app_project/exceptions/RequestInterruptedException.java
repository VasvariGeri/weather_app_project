package com.example.wheater_app_project.exceptions;

public class RequestInterruptedException extends RuntimeException {

    public RequestInterruptedException() {
    }

    public RequestInterruptedException(String message) {
        super(message);
    }
}
