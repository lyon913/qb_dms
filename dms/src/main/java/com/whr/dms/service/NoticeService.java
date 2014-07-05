package com.whr.dms.service;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.whr.dms.models.TNotice;
import com.whr.dms.models.TNoticeAttachment;
import com.whr.dms.models.TNotice_TDepartment;

public interface NoticeService {
	
	public TNotice getById(long id);
	/**
	 * 保存通知
	 * @param notice
	 */
	public void saveNotice(TNotice notice,Set<Long> departIds);
	
	public void saveNotice(TNotice notice);
	
	/**
	 * 设置发布状态
	 * @param noticeId
	 * @param published
	 */
	public void setPublished(long noticeId, boolean published);
	
	/**
	 * 删除通知
	 * @param noticeId
	 */
	public void deleteNotice(long noticeId);
	
	/**
	 * 获取已发布通知列表；支持分页
	 * @param page
	 * @param parentId
	 * @return
	 */
	public Page<TNotice> getPublishedNotices(Pageable page,Long parentId);
	/**
	 * 获取各科室已发布通知列表；支持分页
	 * @param page
	 * @param departmentId
	 * @param parentId
	 * @return
	 */
	public Page<TNotice> getPublishedNoticesByDepartment(Pageable page,long departmentId,Long parentId);
	
	/**
	 * 获取全部通知列表；支持分页
	 * @param page
	 * @param parentId
	 * @return
	 */
	public Page<TNotice> getAllNotices(Pageable page,Long parentId);
	/**
	 * 根据关键字查找通知
	 * @param key
	 * @param published 0为未发布，1为发布，2为全部
	 * @return
	 */
	public Page<TNotice> searchAllNotices(String key,long published,Long parentId,Pageable page);
	/**
	 * 根据关键字、发布时间查找
	 * @param key
	 * @param noticeDate
	 * @param published 0为未发布，1为发布，2为全部
	 * @return
	 */
	public Page<TNotice> searchAllNoticesByNoticedate(String key,Date noticeDate,long published,Long parentId,Pageable page);
	/**
	 * 根据关键字，通知类别查找
	 * @param key
	 * @param noticetypeId
	 * @param published 0为未发布，1为发布，2为全部
	 * @return
	 */
	public Page<TNotice> searchAllNoticesByNoticetype(String key, long noticetypeId,long published,Long parentId,Pageable page);
	/**
	 * 根据关键字，通知类别，发布时间查找
	 * @param key
	 * @param noticetypeId
	 * @param noticeDate
	 * @param published 0为未发布，1为发布，2为全部
	 * @return
	 */
	public Page<TNotice> searchAllNoticesByTypeAndDate(String key,long noticetypeId,Date noticeDate,long published,Long parentId,Pageable page);
	/**
	 * 根据关键字，科室查找
	 * @param key
	 * @param departmentId
	 * @param published 0为未发布，1为发布，2为全部
	 * @return
	 */
	public Page<TNotice> searchAllNoticesByDepartmentId(String key,long departmentId,long published,Long parentId,Pageable page);
	/**
	 * 根据关键字，科室，发布时间查找
	 * @param key
	 * @param departmentId
	 * @param noticeDate
	 * @param published 0为未发布，1为发布，2为全部
	 * @return
	 */
	public Page<TNotice> searchAllNoticesByDepartmentAndDate(String key,long departmentId,Date noticeDate,long published,Long parentId,Pageable page);
	/**
	 * 根据关键字，科室，通知类别查找
	 * @param key
	 * @param departmentId
	 * @param noticetypeId
	 * @param published 0为未发布，1为发布，2为全部
	 * @return
	 */
	public Page<TNotice> searchAllNoticesByDepartmentAndType(String key,long departmentId,long noticetypeId,long published,Long parentId,Pageable page);
	/**
	 * 根据关键字，科室，通知类别，发布时间查找
	 * @param key
	 * @param departmentId
	 * @param noticetypeId
	 * @param noticeDate
	 * @param published 0为未发布，1为发布，2为全部
	 * @return
	 */
	public Page<TNotice> searchAllNotices(String key,long departmentId,long noticetypeId,Date noticeDate,long published,Long parentId,Pageable page);

	
	/**
	 * 在发布的通知中查找
	 * @param key
	 * @param page
	 * @return
	 */
	public Page<TNotice> searchPublishedNotices(String key,Long parentId,Pageable pageable);

	
	/**
	 * 根据通知id查找被通知科室
	 * @param noticeId
	 * @return
	 */
	public List<TNotice_TDepartment> findNoticeDepartment(long noticeId);
	
	public Page<TNotice> searchAllPublicNews(String key,Long noticetypeId,Date publishDate,Long parentId,Boolean published,Pageable pageable);
	
	public void addAttachment(TNoticeAttachment attach, InputStream is,String uploadDir) throws Exception ;
	
	public List<TNoticeAttachment> getNoticeAttachments(long noticeId);


	public TNoticeAttachment downLoadNoticeAttachment(long noticeattachmentId);
	
	public void deleteAttachment(long noticeAttachmentId);
}
