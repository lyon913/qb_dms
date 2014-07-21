package com.whr.dms.service;



import java.util.List;

import javax.security.sasl.AuthenticationException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.whr.dms.exceptions.ParameterCheckException;
import com.whr.dms.models.SuggestionState;
import com.whr.dms.models.SuggestionType;
import com.whr.dms.models.TReply;
import com.whr.dms.models.TSuggestion;

public interface SuggestionService {
	public TSuggestion getById(long id);
	/**
	 * 保存意见
	 * @param suggetion
	 */
	public void saveSuggestion(TSuggestion suggestion);
	
	/**
	 * 修改意见内容
	 * @param suggestion
	 */
	public void updateSuggestion(TSuggestion suggestion);

	/**
	 * 删除意见
	 * 
	 * @param suggestionId
	 * @throws ParameterCheckException
	 * @throws AuthenticationException
	 */
	public void deleteSuggestion(long suggestionId) throws ParameterCheckException;
	
	/**
	 * 获取意见列表，并按发布时间倒序排列；支持分页
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<TSuggestion> findSuggestion(SuggestionType type,SuggestionState state, Pageable page);

	/**
	 * 在意见中查找
	 * @param key
	 * @param page
	 * @return
	 */
	public List<TSuggestion> searchAllSuggestions(String key,SuggestionType type,SuggestionState state);
	
	/**
	 * 保存回复，并公开意见
	 * @param suggestionId
	 * @param reply
	 */
	public void replyPublic(long suggestionId, TReply reply);
	
	/**
	 * 保存回复，但不公开意见
	 * @param suggestionId
	 * @param reply
	 */
	public void replyPrivate(long suggestionId, TReply reply);
	
	
}
