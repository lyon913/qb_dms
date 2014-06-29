package com.whr.dms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.whr.dms.models.TNotice_TDepartment;

@Repository
public interface TNotice_TDepartmentDao extends
		JpaRepository<TNotice_TDepartment, Long> {
	@Transactional
	@Modifying
	@Query("delete from TNotice_TDepartment nd where nd.noticeId = ?1")
	public void deleteByNoticeId(long noticeId);

	@Query("from TNotice_TDepartment nd where nd.noticeId = ?1")
	public List<TNotice_TDepartment> findByNoticeId(Long noticeId);

	@Query("from TNotice_TDepartment nd where nd.noticeId=:noticeId")
	public List<TNotice_TDepartment> getDepartmentByNotice(
			@Param("noticeId") Long noticeId);

}
