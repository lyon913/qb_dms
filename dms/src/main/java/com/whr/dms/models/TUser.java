package com.whr.dms.models;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 * @author Yaner
 * 
 * @since 2012-7-15
 * 
 * @Description 用户实体
 */
@Entity
public class TUser extends BaseAuditEntity implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7477170402920751916L;

	/**
	 * 登录名
	 */
	@Column(unique = true)
	@NotNull
	@Size(max = 32)
	private String loginName;

	/**
	 * 密码
	 */
	@NotNull
	@Size(min = 4, max = 16)
	private String password;

	/**
	 * 姓名
	 */
	@Size(max = 20)
	private String name;

	/**
	 * 所属科室
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns(@JoinColumn(name="departmentId"))
	private TDepartment department;
	
	/**
	 * 用于记录用户登录系统时的ip，
	 * 不保存到用户表中
	 */
	@Transient
	private String loginIpAddress;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TDepartment getDepartment() {
		return department;
	}

	public void setDepartment(TDepartment department) {
		this.department = department;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return department.getRoles();
	}

	@Override
	public String getUsername() {
		return loginName;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getLoginIpAddress() {
		return loginIpAddress;
	}

	public void setLoginIpAddress(String loginIpAddress) {
		this.loginIpAddress = loginIpAddress;
	}

}
