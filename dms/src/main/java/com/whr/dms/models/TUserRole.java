package com.whr.dms.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
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
	private TUser user;
	
	/**
	 * 角色名
	 */
	@NotEmpty
	@Size(max = 50)
	@Enumerated(EnumType.STRING)
	private RoleType role;

	/**
	 * 返回角色名（接口方法）
	 */
	@Override
	public String getAuthority() {
		return role.name();
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

}
