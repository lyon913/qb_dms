package com.whr.dms.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class TSoftware extends BaseAuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -849463629486199368L;
	@NotNull
	@Size(min = 1, max = 255)
	private String name;

	@Size(max = 500)
	private String description;

	@Size(max = 50)
	private String author;

	@NotNull
	private Long parentId;

	@NotNull
	@Size(min = 1, max = 1000)
	private String filePath;
	
	@NotNull
	private Long size;
	
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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}


}
