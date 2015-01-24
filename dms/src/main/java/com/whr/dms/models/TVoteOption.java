package com.whr.dms.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 投票选项
 * @author Lyon
 *
 */
@Entity
public class TVoteOption extends BaseAuditEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2358090678546895279L;


	/**
	 * 投票项目名
	 */
	@NotNull
	@Size(max = 20)
	private String title;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns(@JoinColumn(name = "voteId"))
	private TVote vote;


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public TVote getVote() {
		return vote;
	}


	public void setVote(TVote vote) {
		this.vote = vote;
	}
	
}
