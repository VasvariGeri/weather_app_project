package com.example.wheater_app_project.exceptions;

import java.io.IOException;

public class WrongObjectMappingException extends RuntimeException {

    public WrongObjectMappingException() {
    }

    public WrongObjectMappingException(String message) {
        super(message);
    }
}
