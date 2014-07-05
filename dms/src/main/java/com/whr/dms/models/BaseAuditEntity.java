package com.whr.dms.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.whr.dms.security.SecurityUtil;

/**
 * 
 * @author Yaner
 *
 * @since 2012-7-15
 *
 * @Description 所有实体的父类，抽象出ID属性
 */
@MappedSuperclass
public class BaseAuditEntity extends BaseEntity implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5059683780221568525L;

	/**
	 * 记录创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	@Size(max = 32)
	private String creatorLoginName;

	@Size(max = 20)
	private String creatorName;

	@Size(max = 20)
	private String creatorIp;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdate;

	@Size(max = 32)
	private String lastUpdaterLoginName;

	@Size(max = 20)
	private String lastUpdaterName;

	@Size(max = 20)
	private String lastUpdaterIp;

	@PrePersist
	public void updateAuditInfo() {

		if (this.getId() == null) {
			audit(true);
		} else {
			audit(false);
		}
	}

	/**
	 * 记录创建或更新时设置审计信息
	 * 
	 * @param isCreate 是否为新创建记录
	 */
	protected void audit(boolean isCreate) {
		TUser u = (TUser) SecurityUtil.getCurrentUserDetials();
		Date now = new Date();
		if (isCreate) {
			this.createTime = now;
			this.creatorLoginName = u.getLoginName();
			this.creatorName = u.getName();
			this.creatorIp = u.getLoginIpAddress();
		}

		this.lastUpdate = now;
		this.lastUpdaterLoginName = u.getLoginName();
		this.lastUpdaterName = u.getName();
		this.lastUpdaterIp = u.getLoginIpAddress();

	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreatorLoginName() {
		return creatorLoginName;
	}

	public void setCreatorLoginName(String creatorLoginName) {
		this.creatorLoginName = creatorLoginName;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getCreatorIp() {
		return creatorIp;
	}

	public void setCreatorIp(String creatorIp) {
		this.creatorIp = creatorIp;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getLastUpdaterLoginName() {
		return lastUpdaterLoginName;
	}

	public void setLastUpdaterLoginName(String lastUpdaterLoginName) {
		this.lastUpdaterLoginName = lastUpdaterLoginName;
	}

	public String getLastUpdaterName() {
		return lastUpdaterName;
	}

	public void setLastUpdaterName(String lastUpdaterName) {
		this.lastUpdaterName = lastUpdaterName;
	}

	public String getLastUpdaterIp() {
		return lastUpdaterIp;
	}

	public void setLastUpdaterIp(String lastUpdaterIp) {
		this.lastUpdaterIp = lastUpdaterIp;
	}

}
