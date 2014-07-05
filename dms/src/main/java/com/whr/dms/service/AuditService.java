package com.whr.dms.service;

import com.whr.dms.models.TUser;

public interface AuditService {
	/**
	 * 记录用户登录审计信息
	 * @param user
	 */
	public void UserLoginAudit(TUser user);

}
