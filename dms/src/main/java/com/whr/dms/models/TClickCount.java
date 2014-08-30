package com.whr.dms.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Entity
public class TClickCount extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4347058202709653877L;

	/**
	 * type 存的是表名
	 */
	@NotNull
	@Enumerated(EnumType.STRING)
	private ClickType clickType;
	/**
	 * 存的是具体表的id
	 */
	@NotNull
	private Long referenceId;
	
	/**
	 * 点击次数
	 */
	@NotNull
	private long counts;

	public long getCounts() {
		return counts;
	}

	public void setCounts(long counts) {
		this.counts = counts;
	}

	public ClickType getClickType() {
		return clickType;
	}

	public void setClickType(ClickType clickType) {
		this.clickType = clickType;
	}

	public Long getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(Long referenceId) {
		this.referenceId = referenceId;
	}
	
}
