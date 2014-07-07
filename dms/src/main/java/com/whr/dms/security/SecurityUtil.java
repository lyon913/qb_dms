package com.whr.dms.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * spring security 工具类
 * 
 * @author Lyon
 *
 */
public class SecurityUtil {

	/**
	 * 获取当前登录用户
	 * 
	 * @return
	 */
	public static UserDetails getCurrentUserDetials() {
		return (UserDetails) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
	}

}
