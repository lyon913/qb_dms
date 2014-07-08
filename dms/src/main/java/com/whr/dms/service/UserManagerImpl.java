package com.whr.dms.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whr.dms.dao.TDepartmentDao;
import com.whr.dms.dao.TUserDao;
import com.whr.dms.exceptions.ParameterCheckException;
import com.whr.dms.models.TDepartment;
import com.whr.dms.models.TUser;

@Service
public class UserManagerImpl implements UserManager {
	@Resource
	TUserDao uDao;
	
	@Resource
	TDepartmentDao dDao;

	@Override
	public List<TUser> getUserList() {
		Sort sort = new Sort("department.name","name");
		return uDao.findAll(sort);
	}

	@Override
	public TUser getUserById(long id) {
		return uDao.findOne(id);
	}

	@Override
	public TUser getTUserByLoginName(String loginName) {
		return uDao.getUserByLoginName(loginName);
	}

	@Override
	@Transactional
	public void saveUser(TUser u) throws ParameterCheckException {
		if(u.getId() == null) {
			TUser userExists = uDao.getUserByLoginName(u.getLoginName());
			if(userExists != null) {
				throw new ParameterCheckException("用户已存在");
			}
		}
		uDao.save(u);
	}

	@Override
	@Transactional
	public void deleteUser(long id) {
		uDao.delete(id);
	}

	@Override
	@Transactional
	public void setDepartment(long userId, long departmentId) {
		TUser u = uDao.findOne(userId);
		TDepartment d = dDao.findOne(departmentId);
		u.setDepartment(d);
		uDao.save(u);
	}

}
