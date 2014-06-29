package com.whr.dms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.whr.dms.models.TFolder;

@Repository
public interface TFolderDao extends JpaRepository<TFolder, Long> {
	
	@Query("select count(*) from TFolder f where f.parentId = :pid and f.name = :name")
	public Long getFolderCountByName(@Param("name")String name,@Param("pid")long parentId);
	
	@Query("from TFolder f where f.parentId = ?1 order by f.name")
	public List<TFolder> getFoldersByParentId(long parentId);
	
	@Query("select count(*) from TFolder f where f.parentId = ?1")
	public Long getSubDirsCount(long folderId);
	
	@Query("select count(*) from TFile f where f.parentId = ?1")
	public Long getSubFilesCount(long folderId);
	
	@Query("select count(*) from TSoftware s where s.parentId = ?1")
	public Long getSubSoftwaresCount(long folderId);
}
