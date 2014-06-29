package com.whr.dms.models;

import javax.persistence.Entity;

@Entity
public class TNotice_TDepartment extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7160096688722229615L;

	private Long noticeId;
	private Long departmentId;
	public Long getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(Long noticeId) {
		this.noticeId = noticeId;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
}
