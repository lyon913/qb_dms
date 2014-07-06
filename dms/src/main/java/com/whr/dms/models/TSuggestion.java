package com.whr.dms.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class TSuggestion extends BaseAuditEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4621811594843778107L;
	/**
	 * 意见簿标题
	 */
	@NotNull
	@Size(min = 1, max = 255)
	private String suggestionTitle;
	/**
	 * 作者编号
	 */
	private Long authorId;
	/**
	 * 日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date suggestionDate;
	/**
	 * 意见簿内容
	 */
	@NotNull
	@Size(min = 1, max = 5000)
	private String suggestionContent;

	/**
	 * 作者
	 */
	@Size(max = 100)
	private String author;

	/**
	 * 意见类型
	 */
	@NotNull
	@Size(max = 10)
	@Enumerated(EnumType.STRING)
	private SuggestionType type;

	/**
	 * 意见簿状态
	 */
	@NotNull
	@Size(max = 10)
	@Enumerated(EnumType.STRING)
	private SuggestionState state;
	

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSuggestionTitle() {
		return suggestionTitle;
	}

	public void setSuggestionTitle(String suggestionTitle) {
		this.suggestionTitle = suggestionTitle;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public Date getSuggestionDate() {
		return suggestionDate;
	}

	public void setSuggestionDate(Date suggestionDate) {
		this.suggestionDate = suggestionDate;
	}

	public String getSuggestionContent() {
		return suggestionContent;
	}

	public void setSuggestionContent(String suggestionContent) {
		this.suggestionContent = suggestionContent;
	}

	public SuggestionType getType() {
		return type;
	}

	public void setType(SuggestionType type) {
		this.type = type;
	}

	public SuggestionState getState() {
		return state;
	}

	public void setState(SuggestionState state) {
		this.state = state;
	}
}
