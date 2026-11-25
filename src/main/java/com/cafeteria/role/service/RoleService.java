package com.cafeteria.role.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cafeteria.role.entity.RoleEntity;
import com.cafeteria.role.repository.RoleRepository;

@Service
public class RoleService {

	private final RoleRepository roleRepository;
	
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	public List<RoleEntity> findAll(){
		return roleRepository.findAll();
	}
}
