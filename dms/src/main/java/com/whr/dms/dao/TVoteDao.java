package com.whr.dms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.whr.dms.models.TVote;

public interface TVoteDao extends JpaRepository<TVote, Long> {
	
	/**
	 * 把投票ID写入意见簿voteId字段
	 * @param suggId
	 * @param voteId
	 */
	@Modifying
	@Query("update TSuggestion s set s.voteId = :voteId where s.id = :suggId")
	public void updateVoteIdOnTSuggestion(@Param("suggId")long suggId, @Param("voteId")long voteId);
	
	/**
	 * 更新意见簿voteId为null
	 * @param voteId
	 */
	@Modifying
	@Query("update TSuggestion s set s.voteId = null where s.voteId = :voteId")
	public void deleteVoteIdOnTSuggestion(@Param("voteId")long voteId);
}
