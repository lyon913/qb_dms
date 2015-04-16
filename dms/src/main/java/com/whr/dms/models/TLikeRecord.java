package com.whr.dms.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 点赞记录
 * @author Wyan
 *
 */
@Entity
public class TLikeRecord extends BaseAuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5976824076942785066L;
	
	/**
	 * 点赞主表ID
	 */
	@NotNull
	private Long likeId;
	
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

	
	public Long getLikeId() {
		return likeId;
	}

	public void setLikeId(Long likeId) {
		this.likeId = likeId;
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
