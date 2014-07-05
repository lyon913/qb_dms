package com.whr.dms.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.whr.dms.dao.TRoleDao;
import com.whr.dms.models.TRole;

@Service
public class RoleManagerImpl implements RoleManager {
	@Resource
	private TRoleDao dao;

	@Override
	public List<TRole> findAllRoles() {
		return dao.findAll();
	}

	@Override
	public TRole getById(long id) {
		return dao.findOne(id);
	}

}
