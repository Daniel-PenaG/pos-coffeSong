package com.cafeteria.role.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafeteria.role.entity.RoleEntity;
import com.cafeteria.role.service.RoleService;

@RestController
public class RoleController {

	private final RoleService roleService;
	
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@GetMapping("/api/roles")
	public List<RoleEntity> getRoles(){
		return roleService.findAll();
	}
}
