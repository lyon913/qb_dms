package com.whr.dms.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UploadUtils {

	private static Logger log = LoggerFactory.getLogger(UploadUtils.class);

	public static String saveUploadFile(InputStream is, String baseDir)
			throws IOException {
		// get upload dir

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String relativePath = df.format(new Date()) + "\\";
		String path = baseDir + relativePath;
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
			byte[] buffer = new byte[1024];
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
}
