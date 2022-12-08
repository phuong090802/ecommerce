package com.ute.ecwebapp.service;

import com.ute.ecwebapp.entity.RoleEntity;

public interface RoleService {
	RoleEntity getById(Integer roleId);

	RoleEntity getByName(String roleName);
}
