package com.whr.dms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whr.dms.dao.TNoticeTypeDao;
import com.whr.dms.models.TNoticeType;

@Service
public class NoticeTypeServiceImpl implements NoticeTypeService{

	@Autowired
	TNoticeTypeDao ntdao;
	
	@Override
	@Transactional(readOnly=true)
	public List<TNoticeType> getAllList() {
		return ntdao.findAll();
	}

	@Override
	public List<TNoticeType> getAllList(Long parentId) {
		return ntdao.findListByParentId(parentId);
	}

}
