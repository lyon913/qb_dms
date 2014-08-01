package com.whr.dms.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.whr.dms.models.SuggestionState;
import com.whr.dms.models.SuggestionType;
import com.whr.dms.models.TSuggestion;

@Repository
public interface TSuggestionDao extends JpaRepository<TSuggestion, Long> {

	@Query("from TSuggestion s where s.suggestionTitle like :title and type = :type and state = :state")
	public Page<TSuggestion> search(@Param("title") String title,
			@Param("type") SuggestionType type,
			@Param("state") SuggestionState state, Pageable pageable);
}
