package com.whr.dms.software.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whr.dms.config.Config;
import com.whr.dms.dao.TFolderDao;
import com.whr.dms.dao.TSoftwareDao;
import com.whr.dms.exceptions.ParameterCheckException;
import com.whr.dms.models.TFolder;
import com.whr.dms.models.TSoftware;
import com.whr.dms.software.service.SoftwareService;

@Service
public class SoftwareServiceImpl implements SoftwareService{

	@Resource
	TSoftwareDao sdao;
	@Resource
	private TFolderDao folderDao;
	@Resource
	private Config cfg;
	@Override
	public TSoftware getById(long id) {
		return sdao.findOne(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Page<TSoftware> getSoftware(Pageable page) {
		return sdao.getSoftware(page);
	}

	@Override
	public List<TSoftware> getSoftwareByNameLike(String key) {
		return  sdao.getSoftwareByNameLike("%" + key + "%");
	}

	@Transactional
	@Override
	public void uploadFile(TSoftware file, InputStream is,String savePath) throws Exception {
		//check if name is exists
				if(!(sdao.getSoftwaresCountByName(file.getName(), file.getParentId()) == 0)){
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
					sdao.save(file);
				} catch (Exception e) {
					if (outputFile.exists()) {
						outputFile.delete();
					}
					throw e;
				}
	}

	@Override
	@Transactional
	public void deleteSoftware(Long softwareId,String savePath) {
		TSoftware software = sdao.findOne(softwareId);
		File file = new File(savePath + software.getFilePath());
		if(!file.delete()){
			throw new RuntimeException("删除文件失败。");
		}
		sdao.delete(softwareId);
	}

	@Override
	public TSoftware downLoadSoftware(Long softwareId) {
		return sdao.findOne(softwareId);
	}

	@Override
	public Page<TSoftware> listSoftware(long parentId,Pageable pageable) {
		return sdao.getSoftwareListByParentId(parentId,pageable);
	}

	@Override
	public Page<TSoftware> search(String nameKey,Pageable pageable) {
		return sdao.findByNameKey("%"+nameKey+"%",pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TFolder> getSubDirs(Long parentId) {
		return folderDao.getFoldersByParentId(parentId);
	}

	@Transactional
	@Override
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
		if(folder.getParentId() == -2){
			throw new ParameterCheckException("根目录不能删除。");
		}
		if(folderDao.getSubDirsCount(folderId)==0 && folderDao.getSubSoftwaresCount(folderId)==0){
			folderDao.delete(folder);
		}else{
			throw new ParameterCheckException("文件夹非空，不能删除。");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public TFolder getRootFolder() {
		List<TFolder> list = folderDao.getFoldersByParentId(new Long(-2));
		if(list.size() == 1){
			return list.get(0);
		}
		throw new RuntimeException("根节点数量不正确。");
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
		return sdao.getSoftwareCountByName(fileName, parentId) == 0;
	}

}
