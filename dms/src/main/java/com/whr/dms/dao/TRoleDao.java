package com.whr.dms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whr.dms.models.TRole;

@Repository
public interface TRoleDao extends JpaRepository<TRole, Long> {

}
