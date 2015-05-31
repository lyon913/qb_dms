package com.whr.dms.models;

public class LikeRecordCount {
	private Long optionId;
	private String optionTitle;
	private String picture;
	private long count;
	private double percent;

	public LikeRecordCount() {

	}

	public LikeRecordCount(long optionId, String optionTitle, String picture, long count) {
		this.optionId = optionId;
		this.optionTitle = optionTitle;
		this.picture = picture;
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

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
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
