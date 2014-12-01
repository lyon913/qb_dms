package com.whr.dms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whr.dms.dao.TDepartmentDao;
import com.whr.dms.dao.TUserDao;
import com.whr.dms.dao.TUserRoleDao;
import com.whr.dms.exceptions.ParameterCheckException;
import com.whr.dms.models.TDepartment;
import com.whr.dms.models.TUser;
import com.whr.dms.models.TUserRole;

@Service
public class UserManagerImpl implements UserManager {
	@Autowired
	TUserDao uDao;

	@Autowired
	TUserRoleDao urDao;

	@Autowired
	TDepartmentDao dDao;

	@Override
	public List<TUser> findUserList() {
		Sort sort = new Sort("department.name", "name");
		return uDao.findAll(sort);
	}

	@Override
	public TUser findUserById(long id) {
		return uDao.findOne(id);
	}

	@Override
	public TUser findTUserByLoginName(String loginName) {
		return uDao.getUserByLoginName(loginName);
	}

	@Override
	@Transactional
	public void saveUser(TUser u) throws ParameterCheckException {
		if (u != null) {
			//判断用户名是否存在
			TUser userExists = uDao.getUserByLoginName(u.getLoginName());
			if (userExists != null) {
				if(u.getId() == null) {
					throw new ParameterCheckException("用户已存在");
				}
				if(u.getId() != null && !u.getId().equals(userExists.getId())) {
					throw new ParameterCheckException("用户已存在");
				}
				
			}
			
			if (u.getId() != null) {
				//修改已存在的用户
				//删除之前关联的角色
				urDao.deleteByUserId(u.getId());
			}

			// 设置新保存的角色关联关系
			if (u.getRoles() != null && u.getRoles().size() > 0) {
				for (TUserRole ur : u.getRoles()) {
					ur.setUser(u);
				}
			}
			
			//级联保存用户和角色
			uDao.save(u);
		}
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

	@Override
	@Transactional
	public void resetPwd(long userId) {
		TUser u = uDao.findOne(userId);
		u.setPassword("1234");
		uDao.save(u);	
	}

}
