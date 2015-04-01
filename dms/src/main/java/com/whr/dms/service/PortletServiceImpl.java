package com.whr.dms.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.whr.dms.dao.TFileDao;
import com.whr.dms.dao.TNoticeDao;
import com.whr.dms.dao.TSoftwareDao;
import com.whr.dms.dao.TSuggestionDao;
import com.whr.dms.models.TFile;
import com.whr.dms.models.TNotice;
import com.whr.dms.models.TSoftware;
import com.whr.dms.models.TSuggestion;

@Service
public class PortletServiceImpl implements PortletService {
	@Resource
	TFileDao fDao;
	
	@Resource
	TNoticeDao nDao;
	
	@Resource
	TSuggestionDao sDao;
	
	@Resource
	TSoftwareDao softDao;

	@Override
	public List<TFile> getLatestFileList(long departmentId,int num) {
		Sort sort = new Sort(Direction.DESC, "createTime");
		Pageable page = new PageRequest(0, num, sort);
		Page<TFile> result = fDao.getFilePageByDepartment(departmentId, page);
		return result.getContent();
	}

	@Override
	public List<TNotice> getLatestNoticeList(long departmentId,int num) {
		Sort sort = new Sort(Direction.DESC, "publishDate");
		Pageable page = new PageRequest(0, num, sort);
		return nDao.getNoticePageByDepartment(departmentId, true, 1, page).getContent();
	}

	@Override
	public List<TSuggestion> getLatestSuggestion(int num) {
		Sort sort = new Sort(Direction.DESC, "suggestionDate");
		Pageable page = new PageRequest(0, num, sort);
		return sDao.findAll(page).getContent();
	}

	@Override
	public List<TNotice> getLatestPubNews(int num) {
		Sort sort = new Sort(Direction.DESC, "publishDate");
		Pageable page = new PageRequest(0, num, sort);
		return nDao.getPublishedNoticeList(2,page).getContent();
	}

	@Override
	public List<TSoftware> getLatestSoftware(int num) {
		Sort sort = new Sort(Direction.DESC, "createTime");
		Pageable page = new PageRequest(0, num, sort);
		Page<TSoftware> result = softDao.findAll(page);
		return result.getContent();
	}

	@Override
	public List<TFile> getLatestFileList(int num) {
		Sort sort = new Sort(Direction.DESC, "createTime");
		Pageable page = new PageRequest(0, num, sort);
		Page<TFile> result = fDao.getFilePage(page);
		return result.getContent();
	}

	@Override
	public Page<TNotice> getEmergencyNotices(long departmentId,Date d,Pageable pageable) {
		// TODO Auto-generated method stub
		return nDao.getNoticeListByEmergencyState(departmentId,d, pageable);
	}
	
}
