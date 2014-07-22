package com.whr.dms.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;

import com.whr.dms.security.RoleType;

/**
 * 用户角色表
 * @author Lyon
 *
 */
@Entity
public class TUserRole extends BaseEntity implements GrantedAuthority{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9059889841842551568L;
	
	/**
	 * 用户
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "userId")
	private TUser user;
	
	/**
	 * 角色名
	 */
	@NotNull
	@Enumerated(EnumType.STRING)
	private RoleType role;

	/**
	 * 返回角色名（接口方法）
	 */
	@Override
	public String getAuthority() {
		return role.getName();
	}

	public TUser getUser() {
		return user;
	}

	public void setUser(TUser user) {
		this.user = user;
	}

	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return this.getRole().getName();
	}

}
