package com.whr.dms.models;


public class VoteRecordCount {
	private Long optionId;
	private String optionTitle;
	private long count;
	private double percent;
	
	public VoteRecordCount() {
		
	}
	
	public VoteRecordCount(long optionId, long count) {
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
	public double getPercent() {
		return percent;
	}
	public void setPercent(double percent) {
		this.percent = percent;
	}

}
