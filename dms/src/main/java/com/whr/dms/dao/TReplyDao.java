package com.whr.dms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.whr.dms.models.TReply;

@Repository
public interface TReplyDao extends JpaRepository<TReply, Long>{
	@Query("from TReply s where s.suggestionId=:id")
	public List<TReply> getReplyList(@Param("id")long suggestionId);
}
