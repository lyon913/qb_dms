package com.whr.dms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.whr.dms.models.TLike;

public interface TLikeDao extends JpaRepository<TLike, Long> {
	
	@Query("from TLike l where l.fk = :fk")
	public TLike findByTypeFk(@Param("fk")long fk);
}
