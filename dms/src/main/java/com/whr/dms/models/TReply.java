package com.whr.dms.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class TReply extends BaseAuditEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3242281122441267876L;
	/**
	 * 意见编号
	 */
	private Long suggestionId;

	/**
	 * 回复者编号
	 */
	private Long authorId;
	/**
	 * 回复日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date replyDate;
	/**
	 * 回复内容
	 */
	@NotNull
	@Size(min=1,max=5000)
	private String replyContent;
	
	/**
	 * 回复者
	 */
	@Size(max = 100)
	private String author;
	
	public void setAuthor(String author) {
		this.author = author;
	}
	public Long getSuggestionId() {
		return suggestionId;
	}
	public void setSuggestionId(Long suggestionId) {
		this.suggestionId = suggestionId;
	}
	public Long getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
	public String getAuthor() {
		return author;
	}
	public Date getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
}
