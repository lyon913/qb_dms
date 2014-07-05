package com.whr.dms.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author Yaner
 *
 * @since 2012-7-15
 *
 * @Description 所有实体的父类，抽象出ID属性
 */
@Entity
public class TLoginAudit extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2155888797200227882L;

	/**
	 * 用户id
	 */
	@NotNull
	private Long userId;

	/**
	 * 登陆名
	 */
	@NotNull
	@Size(max = 32)
	private String loginName;

	/**
	 * 姓名
	 */
	private String userName;

	/**
	 * 登陆时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date longinTime;

	/**
	 * 登陆ip地址
	 */
	@Size(max = 50)
	private String ipAddress;
	
	public TLoginAudit() {
		super();
	}
	
	public TLoginAudit(TUser user) {
		super();
		this.userId = user.getId();
		this.userName = user.getName();
		this.loginName = user.getLoginName();
		this.longinTime = new Date();
		this.ipAddress = user.getLoginIpAddress();
	}


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getLonginTime() {
		return longinTime;
	}

	public void setLonginTime(Date longinTime) {
		this.longinTime = longinTime;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

}
