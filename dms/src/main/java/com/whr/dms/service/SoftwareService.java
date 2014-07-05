package com.whr.dms.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.whr.dms.exceptions.ParameterCheckException;
import com.whr.dms.models.TFolder;
import com.whr.dms.models.TSoftware;

public interface SoftwareService {
	public TSoftware getById(long id);
	
	/**
	 * 获取软件列表，并按发布时间倒序排列；支持分页
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<TSoftware> getSoftware(Pageable page);
	public List<TSoftware> getSoftwareByNameLike(String key);
	/**
	 * 上传文件 （检查文件名是否合法） （检查权限：write）
	 * 
	 * @param folderId
	 */
	public void uploadFile(TSoftware file, InputStream is,String savePath) throws Exception;

	/**
	 * 删除文件（检查权限：write）
	 * 
	 * @param fileId
	 * @return
	 */
	public void deleteSoftware(Long softwareId,String savePath);
	/**
	 * 获取下载文件流（检查权限：read）
	 * 
	 * @param fileId
	 * @return
	 */
	public TSoftware downLoadSoftware(Long softwareId);
	
	public Page<TSoftware> listSoftware(long parentId,Pageable pageable);
	
	/**
	 * 根据文件名查找文件（模糊查询）
	 * @param nameKey
	 * @return
	 */
	public Page<TSoftware> search(String nameKey,Pageable pageable);
	
	/**
	 * 获取子节点
	 * 
	 * @param parentId
	 * @return
	 */
	public List<TFolder> getSubDirs(Long parentId);


	/**
	 * 创建文件夹（需要检查名称是否合法）
	 * 
	 * @param folder
	 */
	public void createFolder(TFolder folder) throws ParameterCheckException;

	/**
	 * 删除文件夹（需要检查文件夹是否为空）
	 * 
	 * @param id
	 */
	public void deleteFolder(Long folderId) throws ParameterCheckException;

	/**
	 * 获取根节点
	 * 
	 * @return
	 */
	public TFolder getRootFolder();

	/**
	 * 获取子文件夹数
	 * 
	 * @param parentId
	 * @return
	 */
	public Long getSubDirsCount(Long folderId);

	/**
	 * 获取子文件数
	 * 
	 * @param parentId
	 * @return
	 */
	public Long getSubFilesCount(Long parentId);

	/**
	 * 文件夹是否为空（无子文件夹和子文件）
	 * 
	 * @param folderId
	 * @return
	 */
	public boolean isFolderEmpty(Long folderId);

	/**
	 * 文件夹名是否有效
	 * 
	 * @param parentId
	 * @param folderName
	 * @return
	 */
	public boolean isFolderNameValid(Long parentId, String folderName);

	/**
	 * 文件名是否有效
	 * 
	 * @param parentId
	 * @param fileName
	 * @return
	 */
	public boolean isFileNameValid(Long folderId, String fileName);


}
