package com.whr.dms.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 公共附件表
 * @author Lyon
 *
 */
@Entity
public class TAttachment extends BaseAuditEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8299414295845087345L;
	
	/**
	 * 附件所属主外键表
	 */
	@NotNull
	@Enumerated(EnumType.STRING)
	private AttachmentType foreignTable;
	
	/**
	 * 附件所属外键值
	 */
	@NotNull
	private Long foreignKey;
	
	/**
	 * 附件名
	 */
	@NotNull
	@Size(max = 200)
	private String name;
	
	/**
	 * 附件存储路径
	 */
	@NotNull
	@Size(max = 200)
	private String path;
	
	/**
	 * 附件大小
	 */
	@NotNull
	private Long size;

	public AttachmentType getForeignTable() {
		return foreignTable;
	}

	public void setForeignTable(AttachmentType foreignTable) {
		this.foreignTable = foreignTable;
	}

	public Long getForeignKey() {
		return foreignKey;
	}

	public void setForeignKey(Long foreignKey) {
		this.foreignKey = foreignKey;
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
