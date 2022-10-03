package com.example.wheater_app_project.services;

import com.example.wheater_app_project.dtos.UserRegistrationDTO;
import com.example.wheater_app_project.exceptions.*;
import com.example.wheater_app_project.models.User;
import com.example.wheater_app_project.repositories.UserRepository;
import com.example.wheater_app_project.security.AuthenticationRequest;
import com.example.wheater_app_project.security.AuthenticationResponse;
import com.example.wheater_app_project.security.JwtUtil;
import com.example.wheater_app_project.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private MyUserDetailsService userDetailsService;
    private JwtUtil jwtTokenUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, MyUserDetailsService userDetailsService, JwtUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    private void validateLoginCredentials(AuthenticationRequest authenticationRequestDTO) {
        if (authenticationRequestDTO.getUsername() == null
                || authenticationRequestDTO.getUsername().isBlank()
                || authenticationRequestDTO.getPassword() == null
                || authenticationRequestDTO.getPassword().isBlank()) {
            throw new MissingUsernameOrPasswordException(
                    "Field username and/or field password was empty!");
        }
    }

    private void authenticateRequest(AuthenticationRequest authenticationRequestDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequestDTO.getUsername(), authenticationRequestDTO.getPassword()));
        } catch (BadCredentialsException e) {
            throw new InvalidLoginCredentialsException("Username and/or password was incorrect!");
        }
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        validateLoginCredentials(authenticationRequest);
        authenticateRequest(authenticationRequest);
        final UserDetails userDetails =
                userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return new AuthenticationResponse(jwt);
    }

    @Override
    public void createNewUser(UserRegistrationDTO userRegistrationDTO){
        validateNewUserRegistrationRequest(userRegistrationDTO);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);
        String password = encoder.encode(userRegistrationDTO.getPassword());
        User user = new User(userRegistrationDTO.getUsername(), password);
        userRepository.save(user);
    }

    private void validateNewUserRegistrationRequest(UserRegistrationDTO userRegistrationDTO){
        if ((userRegistrationDTO.getUsername() == null || userRegistrationDTO.getUsername().isBlank())
                && (userRegistrationDTO.getPassword() == null || userRegistrationDTO.getPassword().isBlank())){
            throw new InvalidRegistrationRequestException("Username and password fields are empty!");
        } else if (userRegistrationDTO.getUsername() == null || userRegistrationDTO.getUsername().isBlank()) {
            throw new InvalidRegistrationRequestException("Username field is empty!");
        } else if (userRegistrationDTO.getPassword() == null || userRegistrationDTO.getPassword().isBlank()) {
            throw new InvalidRegistrationRequestException("Password field is empty!");
        } else if (doesUsernameExist(userRegistrationDTO)) {
            throw new UsernameTakenException("Username is already taken!");
        }
    }

    private boolean doesUsernameExist(UserRegistrationDTO userRegistrationDTO){
        return userRepository.findByUsername(userRegistrationDTO.getUsername()).isPresent();
    }
}
