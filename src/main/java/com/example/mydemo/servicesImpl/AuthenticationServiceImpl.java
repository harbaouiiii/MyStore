package com.example.mydemo.servicesImpl;

import com.example.mydemo.auth.AuthenticationResponse;
import com.example.mydemo.auth.LoginRequest;
import com.example.mydemo.auth.RegisterRequest;
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
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var user = User.builder()
                .userName(registerRequest.getUserName())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    loginRequest.getUserName(),
                    loginRequest.getPassword()
            )
        );
        var user = userRepository.findByUserName(loginRequest.getUserName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.
                builder()
                .token(jwtToken)
                .build();
    }
}
