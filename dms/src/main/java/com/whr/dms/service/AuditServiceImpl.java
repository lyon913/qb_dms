package com.whr.dms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whr.dms.dao.TLoginAuditDao;
import com.whr.dms.models.TLoginAudit;
import com.whr.dms.models.TUser;
import com.whr.dms.utils.Utils;

@Service
public class AuditServiceImpl implements AuditService {
	
	@Autowired
	private TLoginAuditDao laDao;

	@Override
	@Transactional
	public void logUserLogin(TUser user) {
		laDao.save(new TLoginAudit(user));
	}

	@Override
	public Long getLoginCounts() {
		//获取网站访问量统计
		Long counts = laDao.getLoginCounts();
		return counts;
	}

	@Override
	public Long getLoginCountsCurDay() {
		// 获取当天访问量
				Long counts = laDao.getLoginCoutns(Utils.getTodayDate(), Utils.getTomorrowDate());
		return counts;
	}

}
