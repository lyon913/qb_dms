package com.whr.dms.notice.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whr.dms.dao.TNoticeAttachmentDao;
import com.whr.dms.dao.TNoticeDao;
import com.whr.dms.dao.TNotice_TDepartmentDao;
import com.whr.dms.models.TNotice;
import com.whr.dms.models.TNoticeAttachment;
import com.whr.dms.models.TNotice_TDepartment;
import com.whr.dms.notice.service.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Resource
	TNoticeDao nDao;
	
	@Resource
	TNotice_TDepartmentDao ndDao;
	
	@Resource
	TNoticeAttachmentDao naDao;

	@Override
	@Transactional
	public void saveNotice(TNotice notice, Set<Long> departIds) {
		nDao.save(notice);
		if(notice.getId() != null){
			ndDao.deleteByNoticeId(notice.getId());
		}
		for (Long dId : departIds) {
			TNotice_TDepartment nd = new TNotice_TDepartment();
			nd.setNoticeId(notice.getId());
			nd.setDepartmentId(dId);
			ndDao.save(nd);
		}
	}

	@Override
	@Transactional
	public void setPublished(long noticeId, boolean published) {
		TNotice n = nDao.findOne(noticeId);
		n.setPublished(published);
		n.setPublishDate(new Date());
		nDao.save(n);
	}

	@Override
	@Transactional
	public void deleteNotice(long noticeId) {
		nDao.delete(noticeId);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<TNotice> getPublishedNotices(Pageable page,Long parentId) {
		return nDao.getPublishedNoticeList(parentId,page);
	}

	@Override
	@Transactional(readOnly = true)
	public TNotice getById(long id) {
		return nDao.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<TNotice> getAllNotices(Pageable page,Long parentId) {
		return nDao.getAllNoticeList(parentId, page);
	}





	
	@Override
	@Transactional(readOnly = true)
	public Page<TNotice> searchAllNotices(String key, long published,Long parentId,Pageable pageable) {
		Page<TNotice> page = null;
		if(published==0){
			page = nDao.getNoticeByNameLike("%"+key+"%",parentId, false, pageable);
		}else if(published==1){
			page = nDao.getNoticeByNameLike("%"+key+"%",parentId,true, pageable);
		}else if(published==2){
			page = nDao.getAllNoticeByNameLike("%"+key+"%",parentId, pageable);
		}
		
		return page;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<TNotice> searchAllNoticesByNoticedate(String key,
			Date noticeDate, long published,Long parentId,Pageable pageable) {
		Page<TNotice> page = null;
		if(published==0){
			page = nDao.getNoticeByNoticedate("%"+key+"%", noticeDate,parentId,false,pageable);
		}else if(published==1){
			page = nDao.getNoticeByNoticedate("%"+key+"%", noticeDate,parentId,true,pageable);
		}else if(published==2){
			page = nDao.getAllNoticeByNoticedate("%"+key+"%",noticeDate,parentId,pageable);
		}
		return page;
	}

	@Override
	@Transactional(readOnly=true)
	public Page<TNotice> searchAllNoticesByNoticetype(String key,
			long noticetypeId, long published,Long parentId,Pageable pageable) {
		Page<TNotice> page = null;
		if(published==0){
			page = nDao.getNoticeListByNoticetype("%"+key+"%", noticetypeId,parentId,false,pageable);
		}else if(published==1){
			page = nDao.getNoticeListByNoticetype("%"+key+"%", noticetypeId,parentId,true,pageable);
		}else if(published==2){
			page = nDao.getAllNoticeListByNoticetype("%"+key+"%", noticetypeId,parentId,pageable);
		}
		return page;
	}

	@Override
	@Transactional(readOnly=true)
	public Page<TNotice> searchAllNoticesByTypeAndDate(String key,
			long noticetypeId, Date noticeDate, long published,Long parentId,Pageable pageable) {
		Page<TNotice> page = null;
		if(published==0){
			page = nDao.getNoticeListByTypeAndDate("%"+key+"%", noticetypeId, noticeDate,parentId,false,pageable);
		}else if(published==1){
			page = nDao.getNoticeListByTypeAndDate("%"+key+"%", noticetypeId, noticeDate,parentId,true,pageable);
		}else if(published==2){
			page = nDao.getAllNoticeListByTypeAndDate("%"+key+"%", noticetypeId,noticeDate,parentId,pageable);
		}
		return page;
	}

	@Override
	@Transactional(readOnly=true)
	public Page<TNotice> searchAllNoticesByDepartmentId(String key,
			long departmentId, long published,Long parentId,Pageable pageable) {
		Page<TNotice> page = null;
		if(published==0){
			page = nDao.getNoticeListByDepartment("%"+key+"%", departmentId,parentId,false,pageable);
		}else if(published==1){
			page = nDao.getNoticeListByDepartment("%"+key+"%", departmentId,parentId,true,pageable);
		}else if(published==2){
			page = nDao.getAllNoticeListByDepartment("%"+key+"%", departmentId,parentId,pageable);
		}
		return page;
	}

	@Override
	@Transactional(readOnly=true)
	public Page<TNotice> searchAllNoticesByDepartmentAndDate(String key,
			long departmentId, Date noticeDate, long published,Long parentId,Pageable pageable) {
		Page<TNotice> page = null;
		if(published==0){
			page = nDao.getNoticeListByDepartmentAndDate("%"+key+"%", departmentId, noticeDate,parentId, false,pageable);
		}else if(published==1){
			page = nDao.getNoticeListByDepartmentAndDate("%"+key+"%", departmentId, noticeDate,parentId, true,pageable);
		}else if(published==2){
			page = nDao.getAllNoticeListByDepartmentAndDate("%"+key+"%", departmentId, noticeDate,parentId,pageable);
		}
		return page;
	}

	@Override
	public Page<TNotice> searchAllNoticesByDepartmentAndType(String key,
			long departmentId, long noticetypeId, long published,Long parentId,Pageable pageable) {
		Page<TNotice> page = null;
		if(published==0){
			page = nDao.getNoticeListByDepartmentAndType("%"+key+"%", departmentId, noticetypeId,parentId,false,pageable);
		}else if(published==1){
			page = nDao.getNoticeListByDepartmentAndType("%"+key+"%", departmentId, noticetypeId,parentId,true,pageable);
		}else if(published==2){
			page = nDao.getAllNoticeListByDepartmentAndType("%"+key+"%", departmentId, noticetypeId,parentId,pageable);
		}
		return page;
	}

	@Override
	public Page<TNotice> searchAllNotices(String key, long departmentId,
			long noticetypeId, Date noticeDate, long published,Long parentId,Pageable pageable) {
		Page<TNotice> page = null;
		if(published==0){
			page = nDao.getNoticeListByDepartmentAndType("%"+key+"%", departmentId, noticetypeId,parentId, false,pageable);
		}else if(published==1){
			page = nDao.getNoticeListByDepartmentAndType("%"+key+"%", departmentId, noticetypeId,parentId, true,pageable);
		}else if(published==2){
			page = nDao.getAllNoticeListByDepartmentAndType("%"+key+"%", departmentId, noticetypeId,parentId,pageable);
		}
		return page;
	}

	@Override
	public Page<TNotice> searchPublishedNotices(String key,Long parentId,Pageable pageable) {
		return nDao.getNoticeByNameLike("%"+key+"key",parentId,true,pageable);
	}

	@Override
	public List<TNotice_TDepartment> findNoticeDepartment(long noticeId) {
		return ndDao.findByNoticeId(noticeId);
	}

	@Override
	public Page<TNotice> getPublishedNoticesByDepartment(Pageable page,long departmentId,Long parentId) {
		return nDao.getNoticePageByDepartment(departmentId, true,parentId,page);
	}

	@Override
	public void saveNotice(TNotice notice) {
		nDao.save(notice);
	}

	@Override
	public Page<TNotice> searchAllPublicNews(String key, Long noticetypeId,
			Date publishDate, Long parentId,Boolean published, Pageable pageable) {
		Page<TNotice> page = null;
		Calendar c=Calendar.getInstance();
		c.set(1900, 1, 1);
		Date startDate = c.getTime();
		c.set(2100, 1,1);
		Date endDate = c.getTime();
		if(publishDate != null){
			startDate = publishDate;
			c.setTime(publishDate);
			c.add(Calendar.DATE, 1);
			endDate = c.getTime();
		}

		if(published==null){
			if(noticetypeId!=0){
				page = nDao.getNoticeListBySearch("%"+key+"%", noticetypeId,startDate,endDate, true,false,parentId, pageable);
			}
			else{
				page = nDao.getNoticeListBySearch("%"+key+"%", startDate, endDate, true, false,parentId, pageable);
			}
		}
		else if(published==false){
			if(noticetypeId!=0){
				page = nDao.getNoticeListBySearch("%"+key+"%", noticetypeId,startDate,endDate, false,false,parentId, pageable);
			}
			else{
				page = nDao.getNoticeListBySearch("%"+key+"%",startDate,endDate, false,false,parentId, pageable);
			}
		}else if(published==true){
			if(noticetypeId!=0){
				page = nDao.getNoticeListBySearch("%"+key+"%",noticetypeId,startDate,endDate, true,true,parentId, pageable);
			}else{
				page = nDao.getNoticeListBySearch("%"+key+"%",startDate,endDate, true,true,parentId, pageable);
			}
			
		}
		
		return page;
		
	}

	@Override
	@Transactional
	public void addAttachment(TNoticeAttachment attach, InputStream is,String uploadDir) throws Exception {
		
		//get upload dir
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String relativePath = df.format(new Date()) + "\\";
		String path = uploadDir + relativePath;
		File folder = new File(path);
		if(!folder.exists()){
			folder.mkdirs();
		}
		
		String uuid = UUID.randomUUID().toString();
		File outputFile = new File(path + uuid);
		BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(outputFile));
		
		//read buffer
		byte[] buffer = new byte[1024];
		while(is.read(buffer) > -1){
			os.write(buffer);
		}
		is.close();
		os.close();
		
		try {
			attach.setPath(relativePath + uuid);
			naDao.save(attach);
		} catch (Exception e) {
			if (outputFile.exists()) {
				outputFile.delete();
			}
			throw e;
		}
	}

	@Override
	public List<TNoticeAttachment> getNoticeAttachments(long noticeId) {
		return naDao.findByNoticeId(noticeId);
	}

	@Override
	public TNoticeAttachment downLoadNoticeAttachment(long noticeattachmentId) {
		return naDao.findOne(noticeattachmentId);
	}

	@Override
	@Transactional
	public void deleteAttachment(long noticeAttachmentId) {
		naDao.delete(noticeAttachmentId);
	}
}
