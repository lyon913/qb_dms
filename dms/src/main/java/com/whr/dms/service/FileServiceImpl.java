package com.whr.dms.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whr.dms.dao.TFileDao;
import com.whr.dms.dao.TFile_TDepartmentDao;
import com.whr.dms.dao.TFolderDao;
import com.whr.dms.dao.TUserDao;
import com.whr.dms.exceptions.ParameterCheckException;
import com.whr.dms.models.TFile;
import com.whr.dms.models.TFile_TDepartment;
import com.whr.dms.models.TFolder;
import com.whr.dms.models.TUser;
import com.whr.dms.security.RoleType;
import com.whr.dms.security.SecurityUtil;

/**
 * 文件夹、文件服务实现类
 * @author Lyon
 *
 */
@Service
public class FileServiceImpl implements FileService {

	@Resource
	private TFolderDao folderDao;
	
	@Resource
	private TFileDao fileDao;
	
	@Resource
	private TUserDao uDao;
	
	@Resource
	private TFile_TDepartmentDao fdDao;
	
	//@Resource
	//private Config cfg;

	@Override
	@Transactional(readOnly = true)
	public TFolder getRootFolder() {
		List<TFolder> list = folderDao.getFoldersByParentId(new Long(-1));
		if(list.size() == 1){
			return list.get(0);
		}
		throw new RuntimeException("根节点数量不正确。");
	}

	@Override
	@Transactional(readOnly = true)
	public List<TFolder> getSubDirs(Long parentId) {
		return folderDao.getFoldersByParentId(parentId);
	}

	@Override
	@Transactional(readOnly = true)
	public Long getSubDirsCount(Long folderId) {
		return folderDao.getSubDirsCount(folderId);
	}
	

	@Override
	@Transactional(readOnly = true)
	public Long getSubFilesCount(Long folderId) {
		return folderDao.getSubFilesCount(folderId);
	}


	@Override
	public boolean isFolderEmpty(Long folderId) {
		return (getSubDirsCount(folderId) + getSubFilesCount(folderId)) == 0;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isFolderNameValid(Long parentId, String folderName) {
		return folderDao.getFolderCountByName(folderName, parentId) == 0;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isFileNameValid(Long parentId, String fileName) {
		return fileDao.getFilesCountByName(fileName, parentId) == 0;
	}


	@Override
	@Transactional
	public void createFolder(TFolder folder) throws ParameterCheckException {
		if (folderDao.getFolderCountByName(folder.getName(), folder.getParentId())==0) {
			folderDao.save(folder);
		} else {
			throw new ParameterCheckException("文件夹名已经存在。");
		}

	}

	@Transactional
	@Override
	public void deleteFolder(Long folderId) throws ParameterCheckException {
		if(folderId == null){
			throw new ParameterCheckException("参数异常：folderId == Null");
		}
		TFolder folder = folderDao.findOne(folderId);
		if(folder.getParentId() == -1){
			throw new ParameterCheckException("根目录不能删除。");
		}
		if(folderDao.getSubDirsCount(folderId)==0 && folderDao.getSubFilesCount(folderId)==0){
			folderDao.delete(folder);
		}else{
			throw new ParameterCheckException("文件夹非空，不能删除。");
		}

	}

	@Transactional
	@Override
	public void uploadFile(TFile file,InputStream is, String savePath, Set<Long> sharedDepartementId)
			throws Exception {
		
		//check if name is exists
		if(!(fileDao.getFilesCountByName(file.getName(), file.getParentId()) == 0)){
			throw new ParameterCheckException("文件已经存在，请更改文件名后重新上传。");
		}
		
		//get upload dir
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String relativePath = df.format(new Date()) + "\\";
		String path = savePath + relativePath;
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
			file.setFilePath(relativePath + uuid);
			fileDao.save(file);
			
			for (long departmentId : sharedDepartementId) {
				TFile_TDepartment fd = new TFile_TDepartment();
				fd.setFileId(file.getId());
				fd.setDepartmentId(departmentId);
				fdDao.save(fd);
			}

		} catch (Exception e) {
			if (outputFile.exists()) {
				outputFile.delete();
			}
			throw e;
		}
	}

	@Override
	public Page<TFile> listFiles(Long folderId,Pageable page) {
		return fileDao.getFileListByFolder(folderId,page);
	}

	@Override
	@Transactional
	public void deleteFile(Long fileId,String savePath) {
		TFile tfile = fileDao.findOne(fileId);
		//check permissions
	
		//check if admin
		boolean isAdmin = SecurityUtil.hasRole(RoleType.ROLE_ADMIN.getName());
		//check if author
		boolean isAuthor = SecurityUtil.isMe(tfile.getAuthorId());
		
		//判断是否有权操作
		if(!isAdmin && !isAuthor){
			throw new AccessDeniedException("你无权删除此文件。");
		}
		
		File file = new File(savePath + tfile.getFilePath());
		if(file.exists()){
			if(!file.delete()){
				throw new RuntimeException("删除文件失败。");
			}
		}
		fileDao.delete(fileId);
	}

	@Override
	@Transactional(readOnly = true)
	public TFile downLoadFile(Long fileId) {
		//check permissions
		String username = SecurityUtil.getCurrentUserDetials().getUsername();
		if(fdDao.getPersmissionCount(username, fileId)==0){
			throw new AccessDeniedException("你无权访问此文件。");
		}
		
		return fileDao.findOne(fileId);
	}

	@Override
	public Page<TFile> search(String nameKey,Pageable pageable) {
		return fileDao.findByNameKey("%"+nameKey+"%",pageable);
	}

	@Override
	public boolean hasAccessPermission(String loginName, long fileId) {
		return fdDao.getPersmissionCount(loginName, fileId)>0;
	}

}
