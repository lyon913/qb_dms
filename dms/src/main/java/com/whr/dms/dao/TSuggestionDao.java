package com.whr.dms.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.whr.dms.models.TSuggestion;

@Repository
public interface TSuggestionDao extends JpaRepository<TSuggestion, Long> {
	@Query("from TSuggestion s where s.suggestionTitle=:suggestionTitle")
	public List<TSuggestion> getListByTitle(@Param("suggestionTitle")String suggestionTitle);
	
	@Query("from TSuggestion s")
	public Page<TSuggestion> getSuggestion(Pageable pageable);
	
	@Query("from TSuggestion s where s.suggestionTitle like ?1")
	public List<TSuggestion> getSuggestionByNameLike(String nameKey);
}
