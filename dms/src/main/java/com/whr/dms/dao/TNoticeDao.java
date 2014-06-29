package com.whr.dms.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.whr.dms.models.TNotice;

@Repository
public interface TNoticeDao extends JpaRepository<TNotice, Long> {
	@Query("from TNotice n where n.parentNoticeTypeId=:parentNoticeTypeId and n.published = true")
	public Page<TNotice> getPublishedNoticeList(@Param("parentNoticeTypeId")long parentNoticeTypeId,Pageable pageable);
	
	@Query("from TNotice n where n.parentNoticeTypeId=:parentNoticeTypeId")
	public Page<TNotice> getAllNoticeList(@Param("parentNoticeTypeId")long parentNoticeTypeId,Pageable pageable);
	
	@Query("from TNotice n where n.parentNoticeTypeId=:parentNoticeTypeId and n.published = :published and n.id in(select nd.noticeId from TNotice_TDepartment nd where nd.departmentId=:departmentId)")
	public Page<TNotice> getNoticePageByDepartment(@Param("departmentId")Long departmentId,@Param("published")Boolean published,@Param("parentNoticeTypeId")long parentNoticeTypeId,Pageable pageable);
	
	@Query("from TNotice n where n.title like :nameKey and n.parentNoticeTypeId = :parentNoticeTypeId")
	public Page<TNotice> getAllNoticeByNameLike(@Param("nameKey")String nameKey,@Param("parentNoticeTypeId")Long parentNoticeTypeId,Pageable pageable);
	
	@Query("from TNotice n where n.title like :namekey and n.published = :published and n.parentNoticeTypeId=:parentNoticeTypeId")
	public Page<TNotice> getNoticeByNameLike(@Param("namekey")String nameKey,@Param("parentNoticeTypeId")Long parentNoticeTypeId,@Param("published")Boolean published,Pageable pageable);
	
	@Query("from TNotice n where n.title like :namekey and n.noticeDate=:noticeDate and n.published = :published and n.parentNoticeTypeId=:parentNoticeTypeId")
	public Page<TNotice> getNoticeByNoticedate(@Param("namekey")String nameKey,@Param("noticeDate")Date noticeDate,@Param("parentNoticeTypeId")Long parentNoticeTypeId,@Param("published")Boolean published,Pageable pageable);
	
	@Query("from TNotice n where n.title like :namekey and n.noticeDate=:noticeDate and n.parentNoticeTypeId=:parentNoticeTypeId")
	public Page<TNotice> getAllNoticeByNoticedate(@Param("namekey")String nameKey,@Param("noticeDate")Date noticeDate,@Param("parentNoticeTypeId")Long parentNoticeTypeId,Pageable pageable);
	
	@Query("from TNotice n where n.title like :namekey and n.parentNoticeTypeId=:parentNoticeTypeId and n.published = :published and n.id in(select nd.noticeId from TNotice_TDepartment nd where nd.departmentId=:departmentId)")
	public Page<TNotice> getNoticeListByDepartment(@Param("namekey")String nameKey,@Param("departmentId")Long departmentId,@Param("parentNoticeTypeId")Long parentNoticeTypeId,@Param("published")Boolean published,Pageable pageable);
	
	@Query("from TNotice n where n.title like :namekey and n.parentNoticeTypeId=:parentNoticeTypeId and n.id in(select nd.noticeId from TNotice_TDepartment nd where nd.departmentId=:departmentId)")
	public Page<TNotice> getAllNoticeListByDepartment(@Param("namekey")String nameKey,@Param("departmentId")Long departmentId,@Param("parentNoticeTypeId")Long parentNoticeTypeId,Pageable pageable);
	
	
	@Query("from TNotice n where n.title like :namekey and n.published = :published and n.noticetypeId=:noticetypeId and n.parentNoticeTypeId=:parentNoticeTypeId")
	public Page<TNotice> getNoticeListByNoticetype(@Param("namekey")String nameKey,@Param("noticetypeId")Long noticetypeId,@Param("parentNoticeTypeId")Long parentNoticeTypeId,@Param("published")Boolean published,Pageable pageable);
	
	@Query("from TNotice n where n.title like :namekey and n.noticetypeId=:noticetypeId and n.parentNoticeTypeId=:parentNoticeTypeId")
	public Page<TNotice> getAllNoticeListByNoticetype(@Param("namekey")String nameKey,@Param("noticetypeId")Long noticetypeId,@Param("parentNoticeTypeId")Long parentNoticeTypeId,Pageable pageable);
	
	@Query("from TNotice n where n.title like :namekey and n.parentNoticeTypeId=:parentNoticeTypeId and n.published = :published and n.noticetypeId=:noticetypeId and n.id in(select nd.noticeId from TNotice_TDepartment nd where nd.departmentId=:departmentId)")
	public Page<TNotice> getNoticeListByDepartmentAndType(@Param("namekey")String nameKey,@Param("departmentId")Long departmentId,@Param("noticetypeId")Long noticetypeId,@Param("parentNoticeTypeId")Long parentNoticeTypeId,@Param("published")Boolean published,Pageable pageable);
	
	@Query("from TNotice n where n.title like :namekey and n.parentNoticeTypeId=:parentNoticeTypeId and n.noticetypeId=:noticetypeId and n.id in(select nd.noticeId from TNotice_TDepartment nd where nd.departmentId=:departmentId)")
	public Page<TNotice> getAllNoticeListByDepartmentAndType(@Param("namekey")String nameKey,@Param("departmentId")Long departmentId,@Param("noticetypeId")Long noticetypeId,@Param("parentNoticeTypeId")Long parentNoticeTypeId,Pageable pageable);
	
	@Query("from TNotice n where n.title like :namekey and n.published = :published and n.noticetypeId=:noticetypeId and n.noticeDate=:noticeDate and n.parentNoticeTypeId=:parentNoticeTypeId")
	public Page<TNotice> getNoticeListByTypeAndDate(@Param("namekey")String nameKey,@Param("noticetypeId")Long noticetypeId,@Param("noticeDate")Date noticeDate,@Param("parentNoticeTypeId")Long parentNoticeTypeId,@Param("published")Boolean published,Pageable pageable);
	
	@Query("from TNotice n where n.title like :namekey and n.noticetypeId=:noticetypeId and n.noticeDate=:noticeDate and n.parentNoticeTypeId=:parentNoticeTypeId")
	public Page<TNotice> getAllNoticeListByTypeAndDate(@Param("namekey")String nameKey,@Param("noticetypeId")Long noticetypeId,@Param("noticeDate")Date noticeDate,@Param("parentNoticeTypeId")Long parentNoticeTypeId,Pageable pageable);
	
	@Query("from TNotice n where n.title like :namekey and n.parentNoticeTypeId=:parentNoticeTypeId and n.published = :published and n.noticeDate=:noticeDate and n.id in(select nd.noticeId from TNotice_TDepartment nd where nd.departmentId=:departmentId)")
	public Page<TNotice> getNoticeListByDepartmentAndDate(@Param("namekey")String nameKey,@Param("departmentId")Long departmentId,@Param("noticeDate")Date noticeDate,@Param("parentNoticeTypeId")Long parentNoticeTypeId,@Param("published")Boolean published,Pageable pageable);
	
	@Query("from TNotice n where n.title like :namekey and n.parentNoticeTypeId=:parentNoticeTypeId and n.noticeDate=:noticeDate and n.id in(select nd.noticeId from TNotice_TDepartment nd where nd.departmentId=:departmentId)")
	public Page<TNotice> getAllNoticeListByDepartmentAndDate(@Param("namekey")String nameKey,@Param("departmentId")Long departmentId,@Param("noticeDate")Date noticeDate,@Param("parentNoticeTypeId")Long parentNoticeTypeId,Pageable pageable);
	
	@Query("from TNotice n where n.title like :namekey and n.parentNoticeTypeId=:parentNoticeTypeId and n.published = :published and n.noticetypeId=:noticetypeId and n.noticeDate=:noticeDate and n.id in(select nd.noticeId from TNotice_TDepartment nd where nd.departmentId=:departmentId)")
	public List<TNotice> getNoticeListByAll(@Param("namekey")String nameKey,@Param("departmentId")Long departmentId,@Param("noticetypeId")Long noticetypeId,@Param("noticeDate")Date noticeDate,@Param("parentNoticeTypeId")Long parentNoticeTypeId,@Param("published")Boolean published);
	
	@Query("from TNotice n where n.title like :namekey and n.parentNoticeTypeId=:parentNoticeTypeId and n.noticetypeId=:noticetypeId and n.noticeDate=:noticeDate and n.id in(select nd.noticeId from TNotice_TDepartment nd where nd.departmentId=:departmentId)")
	public List<TNotice> getAllNoticeListByDepartmentAndDate(@Param("namekey")String nameKey,@Param("departmentId")Long departmentId,@Param("noticetypeId")Long noticetypeId,@Param("noticeDate")Date noticeDate,@Param("parentNoticeTypeId")Long parentNoticeTypeId);
	
	@Query("from TNotice n where n.title like :namekey  and n.parentNoticeTypeId=:parentNoticeTypeId and (n.published = :publishedLeft or n.published = :publishedRight) and n.noticetypeId = :noticetypeId and (n.publishDate >= :startDate and n.publishDate <= :endDate)")
	public Page<TNotice> getNoticeListBySearch(@Param("namekey")String nameKey,@Param("noticetypeId")Long noticetypeId,@Param("startDate")Date startDate,@Param("endDate")Date endDate,@Param("publishedLeft")Boolean publishedLeft,@Param("publishedRight")Boolean publishedRight,@Param("parentNoticeTypeId")Long parentNoticeTypeId, Pageable pageable);
	
	@Query("from TNotice n where n.title like :namekey and n.parentNoticeTypeId=:parentNoticeTypeId and (n.published = :publishedLeft or n.published = :publishedRight) and (n.publishDate >= :startDate and n.publishDate <= :endDate)")
	public Page<TNotice> getNoticeListBySearch(@Param("namekey")String nameKey,@Param("startDate")Date startDate,@Param("endDate")Date endDate,@Param("publishedLeft")Boolean publishedLeft,@Param("publishedRight")Boolean publishedRight,@Param("parentNoticeTypeId")Long parentNoticeTypeId, Pageable pageable);
	
}
