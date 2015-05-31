package com.whr.dms.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * 点赞主表
 * @author wyan
 *
 */
@Entity
public class TLike extends BaseAuditEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6908808665340015872L;
	
	
	/**
	 * 关联的点赞内容表ID;
	 */
	@NotNull
	private Long fk;


	public Long getFk() {
		return fk;
	}


	public void setFk(Long fk) {
		this.fk = fk;
	}

}
