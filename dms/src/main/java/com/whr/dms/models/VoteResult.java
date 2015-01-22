package com.whr.dms.models;

import java.util.List;


public class VoteResult {
	private Long voteId;
	private String title;
	private Long total;
	private List<VoteRecordCount> counts;

	public Long getVoteId() {
		return voteId;
	}

	public void setVoteId(Long voteId) {
		this.voteId = voteId;
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

	public List<VoteRecordCount> getCounts() {
		return counts;
	}

	public void setCounts(List<VoteRecordCount> counts) {
		this.counts = counts;
	}
}
