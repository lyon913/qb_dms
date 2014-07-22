package com.whr.dms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.whr.dms.models.TUserRole;

@Repository
public interface TUserRoleDao extends JpaRepository<TUserRole, Long> {
	@Query("from TUserRole ur where ur.user.id = :uid")
	public TUserRole findByUserId(@Param("uid") long uid);
	
	@Modifying
	@Query("delete from TUserRole ur where ur.user.id = :uid")
	public void deleteByUserId(@Param("uid") long uid);
	
}
