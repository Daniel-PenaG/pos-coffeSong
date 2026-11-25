package com.cafeteria.auth.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cafeteria.auth.dto.LoginRequest;
import com.cafeteria.auth.dto.LoginResponse;
import com.cafeteria.role.entity.UserEntity;
import com.cafeteria.secutity.jwt.JwtService;
import com.cafeteria.user.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest request) {

        Optional<UserEntity> userOptional =
                userRepository.findByUsername(
                        request.getUsername());

        if (userOptional.isEmpty()) {
            throw new RuntimeException(
                    "Usuario no encontrado");
        }

        UserEntity user = userOptional.get();

        boolean validPassword =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword());

        if (!validPassword) {
            throw new RuntimeException(
                    "Contraseña incorrecta");
        }

        String accessToken =
                jwtService.generateToken(
                        user.getUsername());

        return new LoginResponse(
                accessToken,
                user.getUsername(),
                user.getRole().getCode()
        );
    }
}