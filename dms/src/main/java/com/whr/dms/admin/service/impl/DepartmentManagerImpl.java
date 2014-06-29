package com.whr.dms.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whr.dms.admin.service.DepartmentManager;
import com.whr.dms.dao.TDepartmentDao;
import com.whr.dms.dao.TNotice_TDepartmentDao;
import com.whr.dms.models.TDepartment;
import com.whr.dms.models.TNotice_TDepartment;

@Service
public class DepartmentManagerImpl implements DepartmentManager {
	
	@Resource
	TDepartmentDao dDao;
	
	TNotice_TDepartmentDao ddao;

	@Override
	public TDepartment getById(long id) {
		return dDao.findOne(id);
	}

	@Override
	public List<TDepartment> getAllDepartments() {
		return dDao.findAll();
	}

	@Override
	@Transactional
	public void saveDepartment(TDepartment d) {
		dDao.save(d);
	}

	@Override
	@Transactional
	public void deleteDepartment(long id) {
		dDao.delete(id);
	}

	@Override
	public List<TNotice_TDepartment> getDepartmentsByNotice(Long noticeId) {
		return ddao.getDepartmentByNotice(noticeId);
	}

	@Override
	public Page<TDepartment> getDepartments(Pageable page) {
		return dDao.getDepartments(page);
	}

}
