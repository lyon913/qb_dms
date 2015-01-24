package com.whr.dms.service;

import java.util.Date;

import com.whr.dms.exceptions.ParameterCheckException;
import com.whr.dms.models.TVote;
import com.whr.dms.models.VoteDetails;
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
	
	/**
	 * 投票
	 * @param voteId
	 * @param optionId
	 * @param userId
	 * @throws ParameterCheckException 
	 */
	public void vote(long voteId, long[] optionId, long userId, String userName) throws ParameterCheckException;
	

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
	
	/**
	 * 获取记名投票的明细信息
	 * @param voteId
	 * @throws ParameterCheckException 
	 */
	public VoteDetails getVoteDetails(long voteId) throws ParameterCheckException;

	/**
	 * 是否过期
	 * @param endDate
	 * @return
	 */
	boolean isExpired(Date endDate);

}
