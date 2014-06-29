package com.whr.dms.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author Yaner
 *
 * @since 2012-7-16
 *
 * @Description
 * 系统功能
 */
@Entity
public class TFunction extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4279551426600045998L;
	
	@NotNull
	@Size(min = 1, max = 50)
	private String name;
	
	@NotNull
	@Size(min = 1, max = 100)
	private String displayName;
	
	private Integer displayOrder;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private FunctionType type;
	
	@Size(max = 500)
	private String entryUrl;
	
	@Size(max = 500)
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public FunctionType getType() {
		return type;
	}

	public void setType(FunctionType type) {
		this.type = type;
	}

	public String getEntryUrl() {
		return entryUrl;
	}

	public void setEntryUrl(String entryUrl) {
		this.entryUrl = entryUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

}
