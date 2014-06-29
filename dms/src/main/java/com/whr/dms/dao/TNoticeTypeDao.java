package com.whr.dms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.whr.dms.models.TNoticeType;

@Repository
public interface TNoticeTypeDao extends JpaRepository<TNoticeType, Long> {
	@Query("from TNoticeType nt where nt.parentId=:parentId")
	public List<TNoticeType> findListByParentId(@Param("parentId")Long parentId);
}
