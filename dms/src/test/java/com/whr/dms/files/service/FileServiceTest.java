package com.whr.dms.files.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.whr.dms.exceptions.ParameterCheckException;
import com.whr.dms.models.TFolder;
import com.whr.dms.service.FileService;
import com.whr.dms.web.form.FolderTreeNode;

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
	
	@Test
	public void formatFolderTest() {
		List<TFolder> list = fs.findAllFolders();
		FolderTreeNode n = new FolderTreeNode(list);
		List<FolderTreeNode> formated = n.loadFormatedList();
		for(FolderTreeNode f : formated) {
			System.out.println(f.getName());
		}
	}
}
