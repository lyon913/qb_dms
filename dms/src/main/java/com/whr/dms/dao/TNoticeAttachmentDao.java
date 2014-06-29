package com.whr.dms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.whr.dms.models.TNoticeAttachment;

@Repository
public interface TNoticeAttachmentDao extends JpaRepository<TNoticeAttachment, Long>{
	
	@Query("from TNoticeAttachment a where a.noticeId = ?1")
	public List<TNoticeAttachment> findByNoticeId(Long noticeId);

}
