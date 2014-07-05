package com.whr.dms.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.whr.dms.models.TRole;
import com.whr.dms.models.TUser;
import com.whr.dms.service.UserManager;

public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserManager um;
	
	@Autowired(required=true)
	private HttpServletRequest request;

	@Override
	public UserDetails loadUserByUsername(String loginName)
			throws UsernameNotFoundException {
		TUser tuser = um.getTUserByLoginName(loginName);
		if (tuser == null) {
			throw new UsernameNotFoundException("用户名不正确。");
		}
//		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//		for (TRole r : tuser.getDepartment().getRoles()) {
//			authorities.add(new SimpleGrantedAuthority(r.getName()));
//		}
//		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//		User u = new User(tuser.getLoginName(), tuser.getPassword(), authorities);
		
		TRole role = new TRole();
		role.setName("ROLE_USER");
		tuser.getDepartment().getRoles().add(role);
		
		//获取客户端ip地址
		String ip = request.getRemoteAddr();
		tuser.setLoginIpAddress(ip);
		return tuser;
	}

}
