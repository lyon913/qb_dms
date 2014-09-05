package com.whr.dms.security;

public enum RoleType {
	ROLE_USER("ROLE_USER","普通用户","所有用户都具备此权限"),
	
	ROLE_ADMIN("ROLE_ADMIN","超级管理员","管理及分配所有用户及权限"),
	
	ROLE_NOTICE_MANAGER("ROLE_NOTICE_MANAGER","通知管理","医院通知的发布、删除等事项管理"),
	
	ROLE_PUBLICNEWS_MANAGER("ROLE_PUBLICNEWS_MANAGER","院务政务管理","院务政务公开信息的发布、删除等事项管理"),
	
	ROLE_FILE_MANAGER("ROLE_FILE_MANAGER","文件管理","共享文件、文件夹等内容的增加、删除管理"),
	
	ROLE_SOFTWARE_MANAGER("ROLE_SOFTWARE_MANAGER","软件管理","共享软件的上传、删除等管理"),
	
	ROLE_SUGGESTION_MANAGER("ROLE_SUGGESTION_MANAGER","意见簿管理","意见簿的回复、公开、删除等管理"),
	
	ROLE_PRESIDENT_MANAGER("ROLE_PRESIDENT_MANAGER","院长信箱管理","院长信箱的回复、公开、删除等管理"),
	
	ROLE_HOSPITAL_MANAGER("ROLE_HOSPITAL_MANAGER","医院管理层","医院管理栏目的发表、回复等操作");
	
	private String name;
	private String displayName;
	private String describe;
	
	private RoleType(String name, String displayName, String describe) {
		this.name = name;
		this.displayName = displayName;
		this.describe = describe;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	
}
