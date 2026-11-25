package com.cafeteria.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cafeteria.role.entity.UserEntity;
import com.cafeteria.user.dto.UserResponse;
import com.cafeteria.user.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository repository;
	
	public UserService(UserRepository repository) {
		this.repository = repository;
	}
	
	public List<UserResponse> findAll(){
		
		return repository.findAll()
				.stream()
				.map(user -> new UserResponse(
						user.getId(),
						user.getUsername(),
						user.getFirstName(),
						user.getLasltName(),
						user.getRole().getCode()
						))
				.toList();
	}
}
