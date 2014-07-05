package com.whr.dms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whr.dms.dao.TLoginAuditDao;
import com.whr.dms.models.TLoginAudit;
import com.whr.dms.models.TUser;

@Service
public class AuditServiceImpl implements AuditService {
	
	@Autowired
	private TLoginAuditDao laDao;

	@Override
	@Transactional
	public void UserLoginAudit(TUser user) {
		laDao.save(new TLoginAudit(user));

	}

}
