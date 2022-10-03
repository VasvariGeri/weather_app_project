package com.example.wheater_app_project.services;

import com.example.wheater_app_project.dtos.UserRegistrationDTO;
import com.example.wheater_app_project.models.User;
import com.example.wheater_app_project.security.AuthenticationRequest;
import com.example.wheater_app_project.security.AuthenticationResponse;

public interface UserService {

    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);

    void createNewUser(UserRegistrationDTO userRegistrationDTO);
}
