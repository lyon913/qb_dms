package com.whr.dms.models;

public enum AttachmentType {
	
	/**
	 * 医院管理
	 */
	TSuggestion("医院管理");
	
	private String displayName;
	
	private AttachmentType(String name) {
		this.displayName = name;
	}
	
	public String getDisplayName() {
		return this.displayName;
	}
}
