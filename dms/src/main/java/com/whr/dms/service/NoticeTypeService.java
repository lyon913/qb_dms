package com.whr.dms.service;

import java.util.List;

import com.whr.dms.models.TNoticeType;

public interface NoticeTypeService {

	List<TNoticeType> getAllList();
	List<TNoticeType> getAllList(Long parentId);
}
