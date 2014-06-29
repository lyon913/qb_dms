package com.whr.dms.models;

import javax.persistence.Entity;

@Entity
public class TFile_TDepartment extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9009603772237780394L;
	
	private Long fileId;
	private Long departmentId;

	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

}
