package com.whr.dms.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.whr.dms.exceptions.ParameterCheckException;
import com.whr.dms.models.LikeResult;
import com.whr.dms.models.TLikeOption;

public interface LikeService {
	
	
	/**
	 * 点赞
	 */
	public void like(String type, long fk ,long optionId) throws ParameterCheckException;
	

	
	/**
	 * 获取点赞各项结果统计
	 */
	public LikeResult getLikeCount(String type, long fk) throws ParameterCheckException;
	

	/**
	 * 找到TLikeOption
	 * 
	 * @return
	 */
	public List<TLikeOption> findAll();
	
	/**
	 * 保存选项
	 * @param opt
	 * @throws IOException 
	 */
	public void saveLikeOption(TLikeOption opt, InputStream pic, String uploadPath) throws IOException;
	
	/**
	 * 获取选项
	 * @param optionId
	 * @return
	 */
	public TLikeOption findOptionById(long optionId);


	/**
	 * 保存option，不包含图片上传
	 * @param opt
	 */
	void saveLikeOptionWithoutPicture(TLikeOption opt);


	/**
	 * 级联删除option和option所关联的投票
	 * @param optionId
	 */
	void deleteOptionCascade(long optionId);

}
