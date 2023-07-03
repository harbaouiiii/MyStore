package com.example.mydemo.application.servicesImpl;

import com.example.mydemo.application.dtos.AuthenticationResponseDTO;
import com.example.mydemo.application.dtos.LoginRequestDTO;
import com.example.mydemo.application.dtos.RegisterRequestDTO;
import com.example.mydemo.config.JwtService;
import com.example.mydemo.persistance.entities.Role;
import com.example.mydemo.persistance.entities.User;
import com.example.mydemo.application.exceptions.EmailExistsException;
import com.example.mydemo.application.exceptions.UsernameExistsException;
import com.example.mydemo.persistance.repositories.UserRepository;
import com.example.mydemo.application.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponseDTO register(RegisterRequestDTO registerRequestDTO) {
        if(Boolean.TRUE.equals(userRepository.existsByUserName(registerRequestDTO.getUserName()))){
            throw new UsernameExistsException();
        }
        if(Boolean.TRUE.equals(userRepository.existsByEmail(registerRequestDTO.getEmail()))){
            throw new EmailExistsException();
        }
        var user = User.builder()
                .userName(registerRequestDTO.getUserName())
                .firstName(registerRequestDTO.getFirstName())
                .lastName(registerRequestDTO.getLastName())
                .email(registerRequestDTO.getEmail())
                .password(passwordEncoder.encode(registerRequestDTO.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDTO
                .builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponseDTO login(LoginRequestDTO loginRequestDTO) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    loginRequestDTO.getUserName(),
                    loginRequestDTO.getPassword()
            )
        );
        var user = userRepository.findByUserName(loginRequestDTO.getUserName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDTO.
                builder()
                .token(jwtToken)
                .build();
    }
}
