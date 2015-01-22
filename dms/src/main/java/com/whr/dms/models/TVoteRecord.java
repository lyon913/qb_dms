package com.whr.dms.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * 投票记录
 * @author Lyon
 *
 */
@Entity
public class TVoteRecord extends BaseAuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5976824076942785066L;
	
	/**
	 * 投票主题ID
	 */
	@NotNull
	private Long voteId;
	
	/**
	 * 投票选项ID
	 */
	@NotNull
	private Long optionId;
	
	/**
	 * 投票用户ID
	 */
	@NotNull
	private Long userId;

	
	public Long getVoteId() {
		return voteId;
	}

	public void setVoteId(Long voteId) {
		this.voteId = voteId;
	}

	public Long getOptionId() {
		return optionId;
	}

	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
