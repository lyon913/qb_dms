package com.whr.dms.models;

public enum SuggestionState {
	/**
	 * 已经删除的
	 */
	Deleted("已删除","已删除"),
	
	/**
	 * 公开的
	 */
	Public("已公开","已公开"),
	
	/**
	 * 未公开的
	 */
	Private("待审核","未公开"),
	
	/**
	 * 审核不通过
	 */
	Rejected("审核不通过","审核不通过");
	
	private String displayName;
	private String pName;
	
	private SuggestionState(String name,String pName) {
		this.displayName = name;
		this.pName = pName;
	}
	
	public String getDisplayName() {
		return this.displayName;
	}

	public String getpName() {
		return pName;
	}
}
