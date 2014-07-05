package com.whr.dms.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.whr.dms.models.TDepartment;
import com.whr.dms.models.TNotice_TDepartment;

public interface DepartmentManager {
	/**
	 * 根据ID获取科室
	 * @param id
	 * @return
	 */
	public TDepartment getById(long id);
	
	/**
	 * 获取全部科室列表，按名称排序
	 * @return
	 */
	public List<TDepartment> getAllDepartments();
	
	/**
	 * 保存、更新科室信息
	 * @param d
	 */
	public void saveDepartment(TDepartment d);
	
	/**
	 * 删除科室
	 * @param id
	 */
	public void deleteDepartment(long id);
	
	
	/**
	 * 获取某通知所涉及的部门
	 * @return
	 */
	public List<TNotice_TDepartment> getDepartmentsByNotice(Long noticeId);
	
	/**
	 * 获取列表
	 * @param page
	 * @return
	 */
	public Page<TDepartment> getDepartments(Pageable page);
}
