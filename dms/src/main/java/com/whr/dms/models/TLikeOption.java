package com.whr.dms.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 点赞选项
 * @author Wyan
 *
 */
@Entity
public class TLikeOption extends BaseAuditEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2358090678546895279L;


	
	/**
	 * 点赞项目名
	 */
	@NotNull
	@Size(max = 20)
	private String title;
	/**
	 * 项目对应的图片
	 */
	@NotNull
	@Size(max=200)
	private String picture;


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
}
