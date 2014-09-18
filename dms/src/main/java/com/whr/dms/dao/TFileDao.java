package com.whr.dms.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.whr.dms.models.TFile;

public interface TFileDao extends JpaRepository<TFile, Long> {
	
	@Query("select count(*) from TFile f where f.parentId = :pid and f.name = :name")
	public Long getFilesCountByName(@Param("name")String name , @Param("pid")long parentId);
	
	@Query("from TFile f where f.parentId = ?1 order by f.createTime desc")
	public Page<TFile> getFileListByFolder(long folderId,Pageable page);
	
	@Query("from TFile f where f.name like ?1")
	public Page<TFile> findByNameKey(String key,Pageable pageable);
	
	@Query("from TFile f where f.id in(select fd.fileId from TFile_TDepartment fd where fd.departmentId=:departmentId)")
	public Page<TFile> getFilePageByDepartment(@Param("departmentId")Long departmentId,Pageable pageable);

	@Query("from TFile f where f.parentId = :folderId and f.id in(select fd.fileId from TFile_TDepartment fd where fd.departmentId=:departmentId) order by f.createTime desc")
	public List<TFile> getFileListByFolder(@Param("departmentId")long departmentId,@Param("folderId")long folderId);
	
	@Query("from TFile f")
	public Page<TFile> getFilePage(Pageable pageable);
	
	@Modifying
	@Query("update TFile f set f.parentId = :targetFolderId where f.id in( :filesId)")
	public int moveFiles(@Param("filesId")List<Long> filesId, @Param("targetFolderId")long targetFolderId);
}
