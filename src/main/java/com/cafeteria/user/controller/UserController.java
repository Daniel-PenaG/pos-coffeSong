package com.cafeteria.user.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafeteria.role.entity.UserEntity;
import com.cafeteria.user.dto.UserResponse;
import com.cafeteria.user.service.UserService;

@RestController
public class UserController {

	private final UserService service;
	
	public UserController(UserService service) {
		this.service = service;
	}
	
	@GetMapping("/api/users")
	public List<UserResponse> findAll(){
		return service.findAll();
	}
}
