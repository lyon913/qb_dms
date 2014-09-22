package com.whr.dms.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UploadUtils {

	private static Logger log = LoggerFactory.getLogger(UploadUtils.class);

	/**
	 * 
	 * @param is
	 * @param getUploadFilePath
	 * @return
	 * @throws IOException
	 */
	public static String saveUploadFile(InputStream is, String uploadFilePath)
			throws IOException {
		// get upload dir

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String relativePath = df.format(new Date()) + "\\";
		String path = uploadFilePath + relativePath;
		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		String uuid = UUID.randomUUID().toString();
		File outputFile = new File(path + uuid);
		BufferedOutputStream os = null;
		try {
			os = new BufferedOutputStream(new FileOutputStream(outputFile));

			// read buffer
			byte[] buffer = new byte[4096];
			while (is.read(buffer) > -1) {
				os.write(buffer);
			}
			is.close();
			os.close();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw e;
		} finally {
			if(is != null) {
				is.close();
			}
			
			if(os != null) {
				os.close();
			}
		}

		return relativePath + uuid;
	}
	
	/**
	 * 获得文件上传的目录路径
	 * @param sess
	 * @return
	 */
	public static String getUploadFilePath(HttpSession sess) {
		String uploadPath = sess.getServletContext().getRealPath("/") + "upload\\files\\";
		return uploadPath;
	}
}
