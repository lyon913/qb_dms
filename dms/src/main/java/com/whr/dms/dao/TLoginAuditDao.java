package com.whr.dms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whr.dms.models.TLoginAudit;

@Repository
public interface TLoginAuditDao extends JpaRepository<TLoginAudit, Long> {

}
