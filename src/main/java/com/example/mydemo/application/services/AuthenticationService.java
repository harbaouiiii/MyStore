package com.example.mydemo.application.services;

import com.example.mydemo.application.dtos.AuthenticationResponseDTO;
import com.example.mydemo.application.dtos.LoginRequestDTO;
import com.example.mydemo.application.dtos.RegisterRequestDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationService {
    AuthenticationResponseDTO register(RegisterRequestDTO registerRequestDTO);
    AuthenticationResponseDTO login(LoginRequestDTO loginRequestDTO);
}
