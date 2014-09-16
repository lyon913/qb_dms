package com.whr.dms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.whr.dms.models.AttachmentType;
import com.whr.dms.models.TAttachment;

public interface TAttachmentDao extends JpaRepository<TAttachment,Long> {

	@Query("from TAttachment where foreignTable = :tablename and foreignKey = :key")
	public List<TAttachment> getAttaches(@Param("key")long key,@Param("tablename")AttachmentType tablename);
}
