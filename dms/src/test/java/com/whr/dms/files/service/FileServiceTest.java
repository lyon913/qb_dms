package com.whr.dms.files.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.whr.dms.exceptions.ParameterCheckException;
import com.whr.dms.models.TFolder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/root-context.xml")
public class FileServiceTest {
	@Resource
	FileService fs;
	
	@Test
	public void createFolderTest() throws ParameterCheckException {
		TFolder f = new TFolder();
		f.setName("test");
		f.setParentId(new Long(-1));
		fs.createFolder(f);

	}
}
