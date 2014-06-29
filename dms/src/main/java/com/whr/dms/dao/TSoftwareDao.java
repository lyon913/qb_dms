package com.whr.dms.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.whr.dms.models.TSoftware;

@Repository
public interface TSoftwareDao extends JpaRepository<TSoftware, Long>{
	@Query("from TSoftware s")
	public Page<TSoftware> getSoftware(Pageable pageable);
	
	@Query("from TSoftware s where s.name like ?1")
	public List<TSoftware> getSoftwareByNameLike(String key);
	
	@Query("select count(*) from TSoftware s where s.parentId = :pid and s.name = :name")
	public Long getSoftwaresCountByName(@Param("name")String name , @Param("pid")long parentId);
	

	@Query("from TSoftware s where s.parentId = ?1 order by s.createTime desc")
	public Page<TSoftware> getSoftwareListByParentId(long parentId,Pageable pageable);
	
	@Query("from TSoftware s where s.name like ?1")
	public Page<TSoftware> findByNameKey(String key,Pageable pageable);
	
	@Query("select count(*) from TSoftware s where s.parentId = :pid and s.name = :name")
	public Long getSoftwareCountByName(@Param("name")String name , @Param("pid")long parentId);
	
	
}
