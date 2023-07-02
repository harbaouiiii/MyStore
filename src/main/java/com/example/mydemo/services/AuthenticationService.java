package com.example.mydemo.services;

import com.example.mydemo.dtos.AuthenticationResponseDTO;
import com.example.mydemo.dtos.LoginRequestDTO;
import com.example.mydemo.dtos.RegisterRequestDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationService {
    AuthenticationResponseDTO register(RegisterRequestDTO registerRequestDTO);
    AuthenticationResponseDTO login(LoginRequestDTO loginRequestDTO);
}
