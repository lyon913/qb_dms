package com.whr.dms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.whr.dms.models.TUser;

@Repository
public interface TUserDao extends JpaRepository<TUser, Long> {
	@Query("from TUser u where u.loginName = ?1")
	public TUser getUserByLoginName(String loginName);
	
}
