package com.whr.dms.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author Yaner
 *
 * @since 2012-7-16
 *
 * @Description
 * 文件夹
 */
@Entity
public class TFolder extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6501301206357186965L;

	/**
	 * 名称
	 */
	@NotNull
	@Size(min = 1,max = 255)
	private String name;
	
	/**
	 * 描述
	 */
	@Size(max = 500)
	private String description;

	/**
	 * 创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date createTime;
	

	/**
	 * 作者
	 */
	@Size(max = 50)
	private String author;
	
	/**
	 * 父文件夹
	 */
	@NotNull
	private Long parentId;


	@PrePersist
	public void setUpdateTime() {

		// 创建时间
		if (this.id == null) {
			this.createTime = new Date();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
}
