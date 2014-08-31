package com.whr.dms.service;

import com.whr.dms.models.TUser;

public interface AuditService {
	/**
	 * 记录用户登录审计信息
	 * @param user
	 */
	public void logUserLogin(TUser user);

	/**
	 * 获取用户登录总数
	 * @return
	 */
	public Long getLoginCounts();
	
	/**
	 * 获取用户当天登录次数
	 * @return
	 */
	public Long getLoginCountsCurDay();
}
