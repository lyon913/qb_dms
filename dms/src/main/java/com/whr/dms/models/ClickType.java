package com.whr.dms.models;

public enum ClickType {
	/**
	 * 通知
	 */
	TNotice("通知"),
	
	/**
	 * 文件
	 */
	TFile("文件");
	
	
	
	private String displayName;
	
	private ClickType(String name) {
		this.displayName = name;
	}
	
	public String getDisplayName() {
		return this.displayName;
	}
	
}
