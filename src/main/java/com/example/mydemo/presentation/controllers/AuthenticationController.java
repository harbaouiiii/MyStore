package com.example.mydemo.presentation.controllers;

import com.example.mydemo.application.dtos.AuthenticationResponseDTO;
import com.example.mydemo.application.dtos.LoginRequestDTO;
import com.example.mydemo.application.dtos.RegisterRequestDTO;
import com.example.mydemo.application.exceptions.EmailExistsException;
import com.example.mydemo.application.exceptions.UsernameExistsException;
import com.example.mydemo.application.services.AuthenticationService;
import com.example.mydemo.utils.Summary;
import com.example.mydemo.utils.Tags;
import com.example.mydemo.utils.Urls;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@CrossOrigin(origins = Urls.ORIGINS_URL)
@Tag(name = Tags.AUTHENTICATION)
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(summary = Summary.REGISTER)
    @PostMapping(Urls.REGISTER_URL)
    public ResponseEntity<AuthenticationResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO){
        try{
            return ResponseEntity.ok(authenticationService.register(registerRequestDTO));
        } catch (UsernameExistsException | EmailExistsException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = Summary.LOGIN)
    @PostMapping(Urls.LOGIN_URL)
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(authenticationService.login(loginRequestDTO));
    }

}
