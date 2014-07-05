package com.whr.dms.service;

import java.util.List;

import com.whr.dms.models.TRole;

public interface RoleManager {
	public List<TRole> findAllRoles();
	
	public TRole getById(long id);
}
