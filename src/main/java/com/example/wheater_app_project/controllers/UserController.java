package com.example.wheater_app_project.controllers;

import com.example.wheater_app_project.dtos.UserRegistrationDTO;
import com.example.wheater_app_project.security.AuthenticationRequest;
import com.example.wheater_app_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        return ResponseEntity.ok(userService.authenticate(authenticationRequest));
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registerNewUser(@RequestBody UserRegistrationDTO userRegistrationDTO){
        userService.createNewUser(userRegistrationDTO);
        return ResponseEntity.ok().build();
    }

}
