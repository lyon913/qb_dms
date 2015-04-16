package com.whr.dms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.whr.dms.models.TLikeRecord;

public interface TLikeRecordDao extends JpaRepository<TLikeRecord, Long> {
	
	@Query("select count(r) from TLikeRecord r where r.likeId=:likeId and r.optionId = :optionId")
	public long countByOptionId(@Param("likeId") long likeId,@Param("optionId") long optionId);
	
	@Query("select count(r) from TLikeRecord r where r.likeId = :likeId and r.userId = :userId")
	public long countByUserId(@Param("likeId") long likeId, @Param("userId") long userId);
	
	@Query("from TLikeRecord r where r.likeId = :likeId")
	public List<TLikeRecord> findBylikeId(@Param("likeId") Long likeId);
}
