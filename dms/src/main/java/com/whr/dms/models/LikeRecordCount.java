package com.whr.dms.models;


public class LikeRecordCount {
	private long likeId;
	private Long optionId;
	private String optionTitle;
	private long count;
	
	public LikeRecordCount() {
		
	}
	
	public LikeRecordCount(long likeId,long optionId, long count) {
		this.likeId = likeId;
		this.optionId = optionId;
		this.count = count;
	}
	
	public Long getOptionId() {
		return optionId;
	}
	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}
	public String getOptionTitle() {
		return optionTitle;
	}
	public void setOptionTitle(String optionTitle) {
		this.optionTitle = optionTitle;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}

	public long getLikeId() {
		return likeId;
	}

	public void setLikeId(long likeId) {
		this.likeId = likeId;
	}

}
