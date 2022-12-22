package com.ute.ecwebapp.service;

import com.ute.ecwebapp.config.RoleName;
import com.ute.ecwebapp.entity.RoleEntity;

public interface RoleService {
	RoleEntity getById(Integer roleId);

	RoleEntity getByName(RoleName roleName);
}
