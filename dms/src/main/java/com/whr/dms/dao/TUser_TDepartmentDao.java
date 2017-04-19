package com.whr.dms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.whr.dms.models.TUser_TDepartment;

@Repository
public interface TUser_TDepartmentDao extends
		JpaRepository<TUser_TDepartment, Long> {

	@Query("from TUser_TDepartment ud where ud.user.id = ?1")
	public List<TUser_TDepartment> findByUserId(Long userId);
	
	@Modifying
	@Query("delete from TUser_TDepartment ud where ud.user.id = :uid")
	public void deleteByUserId(@Param("uid") long uid);
}
