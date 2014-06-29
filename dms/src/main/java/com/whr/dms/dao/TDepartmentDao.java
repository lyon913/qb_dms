package com.whr.dms.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.whr.dms.models.TDepartment;

@Repository
public interface TDepartmentDao extends JpaRepository<TDepartment, Long> {
	@Query("from TDepartment d")
	public Page<TDepartment> getDepartments(Pageable pageable);
}
