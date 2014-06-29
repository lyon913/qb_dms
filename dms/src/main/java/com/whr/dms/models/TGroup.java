package com.whr.dms.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author Yaner
 * 
 * @since 2012-7-16
 * 
 * @Description 权限组
 */
@Entity
public class TGroup extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1696194421090673104L;

	/**
	 * 组名
	 */
	@Size(max = 50)
	@NotNull
	private String name;

	/**
	 * 描述
	 */
	@Size(max = 100)
	private String description;

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
}
