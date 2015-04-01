package com.whr.dms.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.whr.dms.models.TFile;
import com.whr.dms.models.TNotice;
import com.whr.dms.models.TSoftware;
import com.whr.dms.models.TSuggestion;

public interface PortletService {
	
	/**
	 * 获取最新上传的文件
	 * @param num
	 * @return
	 */
	public List<TFile> getLatestFileList(long departmentId,int num);
	
	/**
	 * 获取最新上传的文件
	 * @param num
	 * @return
	 */
	public List<TFile> getLatestFileList(int num);
	
	/**
	 * 获取最新发布的通知
	 * @param num
	 * @return
	 */
	public List<TNotice> getLatestNoticeList(long departmentId,int num);
	
	/**
	 * 获取最新意见
	 * @param num
	 * @return
	 */
	public List<TSuggestion> getLatestSuggestion(int num);
	
	/**
	 * 获取最新院务政务信息
	 * @param num
	 * @return
	 */
	public List<TNotice> getLatestPubNews(int num);
	
	/**
	 * 获取最新的软件信息
	 * @param num
	 * @return
	 */
	public List<TSoftware> getLatestSoftware(int num);
	
	/**
	 * 获取紧急通知
	 * @param departmentId
	 * @param d
	 * @param pageable
	 * @return
	 */
	public Page<TNotice> getEmergencyNotices(long departmentId,Date d,Pageable pageable); 
}
