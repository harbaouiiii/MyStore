package com.example.mydemo.controllers;

import com.example.mydemo.dtos.AuthenticationResponseDTO;
import com.example.mydemo.dtos.LoginRequestDTO;
import com.example.mydemo.dtos.RegisterRequestDTO;
import com.example.mydemo.exceptions.EmailExistsException;
import com.example.mydemo.exceptions.UsernameExistsException;
import com.example.mydemo.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Register")
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO){
        try{
            return ResponseEntity.ok(authenticationService.register(registerRequestDTO));
        } catch (UsernameExistsException | EmailExistsException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Login")
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(authenticationService.login(loginRequestDTO));
    }

}
