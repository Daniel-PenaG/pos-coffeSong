package com.cafeteria.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cafeteria.auth.dto.LoginRequest;
import com.cafeteria.auth.dto.LoginResponse;
import com.cafeteria.auth.service.AuthService;

@RestController
public class AuthController {
	
	 private final AuthService authService;

	    public AuthController(AuthService authService) {
	        this.authService = authService;
	}
	    
	@PostMapping("/api/auth/login")
	public LoginResponse login(@RequestBody LoginRequest request) {
		return authService.login(request);
	}
}
