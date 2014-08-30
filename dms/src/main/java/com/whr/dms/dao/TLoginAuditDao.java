package com.whr.dms.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.whr.dms.models.TLoginAudit;

@Repository
public interface TLoginAuditDao extends JpaRepository<TLoginAudit, Long> {

	/**
	 * 获取网站访问总量
	 * @return
	 */
	@Query("select count(*) from TLoginAudit")
	public Long getLoginCounts();
	
	/**
	 * 一个时间段内的访问量
	 */
	@Query("select count(*) from TLoginAudit where loginTime between :startDate and :endDate")
	public Long getLoginCoutns(@Param("startDate")Date startDate,@Param("endDate")Date endDate);
}
