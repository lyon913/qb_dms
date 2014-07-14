package com.whr.dms.models;

public enum SuggestionState {
	/**
	 * 已经删除的
	 */
	Deleted("已删除"),
	
	/**
	 * 公开的
	 */
	Public("已公开"),
	
	/**
	 * 未公开的
	 */
	Private("未公开");
	
	private String displayName;
	
	private SuggestionState(String name) {
		this.displayName = name;
	}
	
	public String getDisplayName() {
		return this.displayName;
	}
	
	

}
