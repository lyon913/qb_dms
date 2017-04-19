package com.whr.dms.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class TUser_TDepartment extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9009603772237780394L;
	
	/**
	 * 用户
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "userId")
	private TUser user;
	
	
	//用户可以看哪些部门的通知
	@JoinColumn(name = "departmentId")
	private TDepartment department;
	

	
	public TUser getUser() {
		return user;
	}
	public void setUser(TUser user) {
		this.user = user;
	}
//	public Long getDepartmentId() {
//		return departmentId;
//	}
//	public void setDepartmentId(Long departmentId) {
//		this.departmentId = departmentId;
//	}
	
	public TDepartment getDepartment() {
		return department;
	}
	public void setDepartment(TDepartment department) {
		this.department = department;
	}
	
}
