package com.whr.dms.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class TNoticeType extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9042202885544830322L;

	@NotNull
	@Size(max=100)
	private String noticetype;
	@Size(max=500)
	private String noticememo;
	private Long noticeorder;
	private Long parentId;
	public String getNoticetype() {
		return noticetype;
	}
	public void setNoticetype(String noticetype) {
		this.noticetype = noticetype;
	}
	public String getNoticememo() {
		return noticememo;
	}
	public void setNoticememo(String noticememo) {
		this.noticememo = noticememo;
	}
	public Long getNoticeorder() {
		return noticeorder;
	}
	public void setNoticeorder(Long noticeorder) {
		this.noticeorder = noticeorder;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
}
