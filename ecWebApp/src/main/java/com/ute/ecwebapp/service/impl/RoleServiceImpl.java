package com.ute.ecwebapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ute.ecwebapp.entity.RoleEntity;
import com.ute.ecwebapp.exception.RoleNotFoundException;
import com.ute.ecwebapp.repository.RoleRepository;
import com.ute.ecwebapp.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public RoleEntity getById(Integer roleId) {
		return roleRepository.findById(roleId).orElseThrow(() -> new RoleNotFoundException(roleId));
	}

	@Override
	public RoleEntity getByName(String roleName) {
		return roleRepository.findByroleName(roleName).orElseThrow(() -> new RoleNotFoundException(roleName));
	}
}
