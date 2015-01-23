package com.whr.dms.service;

import com.whr.dms.exceptions.ParameterCheckException;
import com.whr.dms.models.TVote;
import com.whr.dms.models.VoteResult;

public interface VoteService {
	
	/**
	 * 新增投票主题
	 * @param vote
	 * @param suggId
	 */
	public void addVote(TVote vote, long suggId);
	
	/**
	 * 
	 * 删除投票
	 * @param voteId
	 */
	public void removeVote(long voteId);
	
//	/**
//	 * 增加选项
//	 * @param option
//	 */
//	public void saveVoteOption(long voteId, TVoteOption option);
//	
//	/**
//	 * 删除选项
//	 */
//	public void removeVoteOption(long optionId);
	
	/**
	 * 投票
	 * @param voteId
	 * @param optionId
	 * @param userId
	 * @throws ParameterCheckException 
	 */
	public void vote(long voteId, long[] optionId, long userId) throws ParameterCheckException;
	

	/**
	 * 根据id查找TVote
	 * @param voteId
	 * @return
	 */
	public TVote findById(long id);
	
	
	/**
	 * 获取投票结果统计
	 * @param voteId
	 * @return
	 * @throws ParameterCheckException 
	 */
	public VoteResult getVoteResult(long voteId) throws ParameterCheckException;

}
