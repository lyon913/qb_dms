package com.whr.dms.models;

import java.util.List;

public class LikeResult {

	private Long likeId;
	private String title;
	private Long total;
	private List<LikeRecordCount> counts;

	public Long getLikeId() {
		return likeId;
	}

	public void setLikeId(Long likeId) {
		this.likeId = likeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<LikeRecordCount> getCounts() {
		return counts;
	}

	public void setCounts(List<LikeRecordCount> counts) {
		this.counts = counts;
	}
}
