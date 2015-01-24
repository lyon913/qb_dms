package com.whr.dms.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 投票主题
 * @author Lyon
 *
 */
@Entity
public class TVote extends BaseAuditEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6908808665340015872L;

	/**
	 * 投票主题
	 */
	@NotNull
	@Size(min = 1, max = 30)
	private String title;

	/**
	 * 投票发起人id
	 */
	@NotNull
	private Long authorId;
	
	/**
	 * 发起人名字
	 */
	@NotEmpty
	@Size(max = 20)
	private String authorName;
	
	/**
	 * 是否为多选
	 */
	@NotNull
	private Boolean isMulti;
	
	/**
	 * 是否记名投票
	 */
	@NotNull
	private Boolean isOpen;
	
	
	/**
	 * 最大可选项目数
	 */
	@NotNull
	@Digits(integer = 1, fraction = 0, message = "请输入一个1-9的整数")
	private Integer maxVotes;
	
	
	/**
	 * 结束日期
	 */
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	
	/**
	 * 投票选项
	 */
	@OneToMany(mappedBy = "vote", cascade = CascadeType.PERSIST)
	@OrderBy(value = "id asc")
	private List<TVoteOption> options;

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Boolean getIsMulti() {
		return isMulti;
	}

	public void setIsMulti(Boolean isMulti) {
		this.isMulti = isMulti;
	}

	public Boolean getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}

	public Integer getMaxVotes() {
		return maxVotes;
	}

	public void setMaxVotes(Integer maxVotes) {
		this.maxVotes = maxVotes;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<TVoteOption> getOptions() {
		return options;
	}

	public void setOptions(List<TVoteOption> options) {
		this.options = options;
	}

}
