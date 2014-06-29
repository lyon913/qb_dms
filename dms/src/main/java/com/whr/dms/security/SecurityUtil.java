package com.whr.dms.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtil {
	
	public static UserDetails getCurrentUserDetials(){
		return (UserDetails) SecurityContextHolder.getContext()
			    .getAuthentication()
			    .getPrincipal();
	}

}
