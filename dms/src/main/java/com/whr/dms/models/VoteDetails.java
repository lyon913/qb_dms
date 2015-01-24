package com.whr.dms.models;

import java.util.List;
import java.util.Map;

public class VoteDetails {
	private Long voteId;
	private String title;
	private Map<TVoteOption, List<String>> details;

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

	public Map<TVoteOption, List<String>> getDetails() {
		return details;
	}

	public void setDetails(Map<TVoteOption, List<String>> details) {
		this.details = details;
	}

}
