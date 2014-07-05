package com.whr.dms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whr.dms.dao.TUserDao;
import com.whr.dms.models.TUser;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	TUserDao uDao;

	@Override
	@Transactional
	public void changePW(String loginName, String oldPW, String newPW) {
		TUser user = uDao.getUserByLoginName(loginName);
		if(user.getPassword().equals(oldPW)){
			user.setPassword(newPW);
			uDao.save(user);
			return;
		}
		throw new AccessDeniedException("密码验证错误");
	}

}
