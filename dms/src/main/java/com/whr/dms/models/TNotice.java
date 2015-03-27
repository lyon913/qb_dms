package com.whr.dms.models;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class TNotice extends BaseAuditEntity {

	private static final long serialVersionUID = -1971019525569776456L;

	@NotNull
	@NotBlank
	@Size(max = 250)
	private String title;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Size(max = 10000000)
	private String content;

	@Size(max = 50)
	private String author;

	private Long noticetypeId;
	private boolean published;

	@Temporal(TemporalType.TIMESTAMP)
	private Date publishDate;

	@Temporal(TemporalType.DATE)
	private Date noticeDate;
	private Long parentNoticeTypeId;

	private boolean state;

	private boolean emergencyState;
	
	public Long getNoticetypeId() {
		return noticetypeId;
	}

	public void setNoticetypeId(Long noticetypeId) {
		this.noticetypeId = noticetypeId;
	}
	
	@Override
	@PrePersist
	public void updateAuditInfo() {

		if (this.getId() == null) {
			audit(true);
		} else {
			audit(false);
		}

		// 发布时间
		this.publishDate = new Date();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public Date getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}

	public Long getParentNoticeTypeId() {
		return parentNoticeTypeId;
	}

	public void setParentNoticeTypeId(Long parentNoticeTypeId) {
		this.parentNoticeTypeId = parentNoticeTypeId;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public boolean isEmergencyState() {
		return emergencyState;
	}

	public void setEmergencyState(boolean emergencyState) {
		this.emergencyState = emergencyState;
	}
}
