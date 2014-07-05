package com.whr.dms.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.whr.dms.models.TUser;
import com.whr.dms.service.AuditService;

/**
 * 登陆成功后置处理器
 * 
 * @author Lyon
 *
 */
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private AuditService as;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		// 记录登陆成功的用户信息
		TUser user = (TUser) authentication.getPrincipal();
		as.UserLoginAudit(user);
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
