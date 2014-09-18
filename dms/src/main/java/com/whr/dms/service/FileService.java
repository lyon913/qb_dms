package com.whr.dms.service;

import java.io.InputStream;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.whr.dms.exceptions.ParameterCheckException;
import com.whr.dms.models.TFile;
import com.whr.dms.models.TFolder;

/**
 * 文件夹、文件业务接口
 * 
 * @author Lyon
 * 
 */
public interface FileService {
	/**
	 * 获取根节点
	 * 
	 * @return
	 */
	public TFolder getRootFolder();

	/**
	 * 获取子节点
	 * 
	 * @param parentId
	 * @return
	 */
	public List<TFolder> getSubDirs(Long parentId);

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
	 * 上传文件 （检查文件名是否合法） （检查权限：write）
	 * 
	 * @param folderId
	 */
	public void uploadFile(TFile file, InputStream is, String savePath, Set<Long> sharedDepartementId) throws Exception;

	/**
	 * 取出文件夹下所有文件（检查权限：list）
	 * 
	 * @param folderId
	 * @return
	 */
	public Page<TFile> listFiles(Long folderId,Pageable page);

	/**
	 * 删除文件（检查权限：write）
	 * 
	 * @param fileId
	 * @return
	 */
	public void deleteFile(Long fileId,String savePath);

	/**
	 * 获取下载文件流（检查权限：read）
	 * 
	 * @param fileId
	 * @return
	 */
	public TFile downLoadFile(Long fileId);
	
	/**
	 * 根据文件名查找文件（模糊查询）
	 * @param nameKey
	 * @return
	 */
	public Page<TFile> search(String nameKey,Pageable pageable);
	
	/**
	 * 是否有文件访问权限
	 * @param loginName
	 * @param fileId
	 * @return
	 */
	public boolean hasAccessPermission(String loginName, long fileId);
	
	/**
	 * 获取所有folder
	 * @return
	 */
	public List<TFolder> findAllFolders();
	
	/**
	 * 移动文件到新的文件夹下
	 * @param filesId 要移动的文件id数组
	 * @param folderId 移动的目标文件夹id
	 * @throws ParameterCheckException 
	 */
	public void moveFiles(long[] filesId, long folderId) throws ParameterCheckException;

}
