package com.example.mydemo.servicesImpl;

import com.example.mydemo.dtos.AuthenticationResponseDTO;
import com.example.mydemo.dtos.LoginRequestDTO;
import com.example.mydemo.dtos.RegisterRequestDTO;
import com.example.mydemo.config.JwtService;
import com.example.mydemo.entities.Role;
import com.example.mydemo.entities.User;
import com.example.mydemo.repositories.UserRepository;
import com.example.mydemo.services.AuthenticationService;
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
