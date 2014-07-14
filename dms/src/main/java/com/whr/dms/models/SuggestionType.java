package com.whr.dms.models;

public enum SuggestionType {
	/**
	 * 意见簿
	 */
	Suggestion("意见簿"),
	
	/**
	 * 院长信箱
	 */
	President("院长信箱"),
	
	/**
	 * 医院管理
	 */
	Managment("医院管理");
	
	private String displayName;
	
	private SuggestionType(String name) {
		this.displayName = name;
	}
	
	public String getDisplayName() {
		return this.displayName;
	}
	
	

}
