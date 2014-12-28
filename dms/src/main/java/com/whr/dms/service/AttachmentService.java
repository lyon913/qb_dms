package com.whr.dms.service;

import java.io.InputStream;
import java.util.List;

import com.whr.dms.models.AttachmentType;
import com.whr.dms.models.TAttachment;

public interface AttachmentService {
	/**
	 * 列出对应的附件
	 * @param suggsId
	 * @param tableName
	 * @return
	 */
	public List<TAttachment> listAttachments(long foreignKey,AttachmentType tableName);
	
	public void addAttachment(TAttachment attach, InputStream is,String uploadDir) throws Exception;
	/**
	 * 下载附件
	 * @param attachment
	 */
	public TAttachment downloadAttachment(long attachmentId);
	
	/**
	 * 删除附件
	 * @param attachId
	 */
	public void deleteAttachment(long attachId);
	
	/**
	 * 获取附件
	 * @param attachmentId
	 * @return
	 */
	public TAttachment getAttachment(long attachmentId);

}
