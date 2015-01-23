package com.whr.dms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.whr.dms.models.TVoteRecord;

public interface TVoteRecordDao extends JpaRepository<TVoteRecord, Long> {
	@Query("select count(r) from TVoteRecord r where r.voteId = :voteId")
	public long countByVoteId(@Param("voteId") long voteId);
	
	@Query("select count(r) from TVoteRecord r where r.optionId = :optionId")
	public long countByOptionId(@Param("optionId") long optionId);
	
	@Query("select count(r) from TVoteRecord r where r.voteId = :voteId and r.userId = :userId")
	public long countByUserId(@Param("voteId") long voteId, @Param("userId") long userId);
}
