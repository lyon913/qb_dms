package com.whr.dms.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	 * 选择的项ID
	 */
	@NotNull
	private Long optionId;
	
	/**
	 * 投票者ID
	 */
	@NotNull
	private Long userId;
	
	/**
	 * 投票者姓名
	 */
	@Size(max = 20)
	private String userName;

	
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
