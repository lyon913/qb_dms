package com.whr.dms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.whr.dms.models.TFile_TDepartment;

@Repository
public interface TFile_TDepartmentDao extends JpaRepository<TFile_TDepartment, Long> {
	@Query("select count(*) from TFile_TDepartment fd where fd.fileId = :fileId and fd.departmentId in(select u.department.id from TUser u where u.loginName = :loginName)")
	public Long getPersmissionCount(@Param("loginName") String loginName,@Param("fileId") Long fileId);
}
