package com.example.wheater_app_project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MissingUsernameOrPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ErrorDTO handleMissingUsernameOrPasswordException(RuntimeException e) {
        return new ErrorDTO(e.getMessage());
    }

    @ExceptionHandler(InvalidLoginCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ErrorDTO handleInvalidLoginCredentialsException(RuntimeException e) {
        return new ErrorDTO(e.getMessage());
    }

    @ExceptionHandler(AuthorizationFailureException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ErrorDTO handleAuthorizationFailureException(RuntimeException e){
        return new ErrorDTO(e.getMessage());
    }

    @ExceptionHandler(InvalidRegistrationRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ErrorDTO handleInvalidRegistrationException(RuntimeException e){
        return new ErrorDTO(e.getMessage());
    }

    @ExceptionHandler(UsernameTakenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ErrorDTO handleUsernameTakenException(RuntimeException e){
        return new ErrorDTO(e.getMessage());
    }

    @ExceptionHandler(WrongObjectMappingException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private ErrorDTO handleWrongObjectMappingException(IOException e){
        return new ErrorDTO(e.getMessage());
    }

    @ExceptionHandler(RequestInterruptedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ErrorDTO handleWrongObjectMappingException(InterruptedException e){
        return new ErrorDTO(e.getMessage());
    }
}
