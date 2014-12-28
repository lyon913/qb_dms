package com.whr.dms.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whr.dms.dao.TAttachmentDao;
import com.whr.dms.models.AttachmentType;
import com.whr.dms.models.TAttachment;
@Service
public class AttachmentServiceImpl implements AttachmentService {
	@Autowired
	private TAttachmentDao adao;
	
	@Override
	public List<TAttachment> listAttachments(long foreignKey,
			AttachmentType tableName) {
		// 附件列表
				List<TAttachment> list = adao.getAttaches(foreignKey, tableName);
				return list;
	}
	
	@Override
	@Transactional
	public void addAttachment(TAttachment attach, InputStream is,
			String uploadDir) throws Exception {
		//get upload dir
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String relativePath = df.format(new Date()) + "\\";
		String path = uploadDir + relativePath;
		File folder = new File(path);
		if(!folder.exists()){
			folder.mkdirs();
		}
		
		String uuid = UUID.randomUUID().toString();
		File outputFile = new File(path + uuid);
		BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(outputFile));
		
		//read buffer
		byte[] buffer = new byte[1024];
		while(is.read(buffer) > -1){
			os.write(buffer);
		}
		is.close();
		os.close();
		
		try {
			attach.setPath(relativePath + uuid);
			adao.save(attach);
		} catch (Exception e) {
			if (outputFile.exists()) {
				outputFile.delete();
			}
			throw e;
		}
		
	}
	@Override
	public TAttachment downloadAttachment(long attachmentId) {
		TAttachment attachment = adao.findOne(attachmentId);
		return attachment;
	}
	@Override
	@Transactional
	public void deleteAttachment(long attachId) {
		adao.delete(attachId);
		
	}
	@Override
	public TAttachment getAttachment(long attachmentId) {
		
		return adao.findOne(attachmentId);
	}



}
