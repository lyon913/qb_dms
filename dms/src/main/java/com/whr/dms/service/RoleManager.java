package com.whr.dms.service;

import java.util.List;

import com.whr.dms.security.RoleType;

public interface RoleManager {
	public List<RoleType> findAllRoles();
	
	public RoleType getRoleType(String roleName);
}
