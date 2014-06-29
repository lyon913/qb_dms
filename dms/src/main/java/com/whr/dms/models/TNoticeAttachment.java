package com.whr.dms.models;

import javax.persistence.Entity;

@Entity
public class TNoticeAttachment extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1687910525766369068L;

	private Long noticeId;
	private String name;
	private String path;
	private Long size;

	public Long getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Long noticeId) {
		this.noticeId = noticeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

}
