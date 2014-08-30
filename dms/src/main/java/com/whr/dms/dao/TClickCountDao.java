package com.whr.dms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.whr.dms.models.ClickType;
import com.whr.dms.models.TClickCount;

@Repository
public interface TClickCountDao extends JpaRepository<TClickCount,Long> {
	@Query("from TClickCount where clickType=:clickType and referenceId=:referenceId")
	public TClickCount getClickCount(@Param("clickType")ClickType clickType,@Param("referenceId")long referenceId);
}
