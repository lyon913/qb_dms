package com.whr.dms.files.controller;

import org.springframework.web.multipart.MultipartFile;

public class UploadForm {
	private Long parentId;

	private MultipartFile file;
	
	private MultipartFile[] files;

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public MultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}


}
