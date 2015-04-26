package com.whr.dms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.whr.dms.models.TLike;

public interface TLikeDao extends JpaRepository<TLike, Long> {
	
	/**
	 * 把点赞ID写入意见簿likeId字段
	 * @param suggId
	 * @param likeId
	 */
	@Modifying
	@Query("update TSuggestion s set s.likeId = :likeId where s.id = :suggId")
	public void updateLikeIdOnTSuggestion(@Param("suggId")long suggId, @Param("likeId")long likeId);
	
}
