package com.whr.dms.models;

public enum SuggestionType {
	/**
	 * 公开意见簿
	 */
	Public("公开意见簿"),
	
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
