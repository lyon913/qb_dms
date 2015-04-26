package com.whr.dms.service;

import java.util.List;

import com.whr.dms.exceptions.ParameterCheckException;
import com.whr.dms.models.LikeRecordCount;
import com.whr.dms.models.TLike;
import com.whr.dms.models.TLikeOption;

public interface LikeService {
	
	/**
	 * 新增点赞主表记录
	 * @param like
	 * @param suggId
	 */
	public void addLike(TLike like, long suggId);
	
	
	/**
	 * 点赞
	 * @param likeId
	 * @param optionId
	 * @param userId
	 * @throws ParameterCheckException 
	 */
	public void like(long likeId, long[] optionId, long userId, String userName) throws ParameterCheckException;
	

	/**
	 * 根据id查找TLike
	 * @param likeId
	 * @return
	 */
	public TLike findById(long id);
	
	
	/**
	 * 获取点赞各项结果统计
	 * @param likeId
	 * @return
	 * @throws ParameterCheckException 
	 */
	public List<LikeRecordCount> getLikeCount(long likeId) throws ParameterCheckException;
	
	/**
	 * 获取点赞的明细信息
	 * @param likeId
	 * @throws ParameterCheckException 
	 */
//	public LikeDetails getLikeDetails(long likeId) throws ParameterCheckException;

	/**
	 * 找到TLikeOption
	 * 
	 * @return
	 */
	public List<TLikeOption> findAll();
	
	/**
	 * 保存选项
	 * @param opt
	 */
	public void addLikeOption(TLikeOption opt);
	
	/**
	 * 获取选项
	 * @param optionId
	 * @return
	 */
	public TLikeOption getOptionById(long optionId);

}
