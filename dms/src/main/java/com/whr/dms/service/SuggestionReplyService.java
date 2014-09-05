package com.whr.dms.service;

import java.util.List;

import com.whr.dms.exceptions.ParameterCheckException;
import com.whr.dms.models.TReply;


public interface SuggestionReplyService {
	public TReply getById(long id);
	
	/**
	 * 保存意见回复
	 * @param suggetion
	 */
	public void saveSuggestionReply(TReply reply);
	
	/**
	 * 修改回复内容
	 * @param suggestion
	 */
	public void updateSuggestionReply(TReply reply);

	/**
	 * 删除回复
	 * @param suggestionId
	 * @throws ParameterCheckException 
	 */
	public void deleteSuggestionReply(long replyId) throws ParameterCheckException;
	
	/**
	 * 获取回复列表，并按发布时间倒序排列；支持分页
	 * @param page
	 * @param size
	 * @return
	 */
	public List<TReply> getSuggestionReply(long suggestionId);
}
