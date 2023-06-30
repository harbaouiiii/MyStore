package com.example.mydemo.services;

import com.example.mydemo.auth.AuthenticationResponse;
import com.example.mydemo.auth.LoginRequest;
import com.example.mydemo.auth.RegisterRequest;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest registerRequest);
    AuthenticationResponse login(LoginRequest loginRequest);
}
