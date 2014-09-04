package com.whr.dms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.whr.dms.security.RoleType;

@Service
public class RoleManagerImpl implements RoleManager {

	@Override
	public List<RoleType> findAllRoles() {
		List<RoleType> rList = new ArrayList<RoleType>();
		for(RoleType rt : RoleType.values()) {
			if(!rt.equals(RoleType.ROLE_USER))
				rList.add(rt);
		}
		return rList;
	}
	
	@Override
	public List<String> findAllRoleNames() {
		List<String> rList = new ArrayList<String>();
		for(RoleType rt : RoleType.values()) {
			if(!rt.equals(RoleType.ROLE_USER))
				rList.add(rt.getName());
		}
		return rList;
	}

	@Override
	public RoleType getRoleType(String roleName) {
		return RoleType.valueOf(roleName);
	}



}
