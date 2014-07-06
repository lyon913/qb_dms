package com.whr.dms.models;

import java.io.Serializable;

import javax.persistence.Entity;
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
	@Size(max = 20)
	private String foreignTable;
	
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

}
