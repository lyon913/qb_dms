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
	public TSuggestion findById(long id);

	/**
	 * 保存意见
	 * 
	 * @param suggetion
	 */
	public void saveSuggestion(TSuggestion suggestion);

	/**
	 * 修改意见内容
	 * 
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
	public void deleteSuggestion(long suggestionId)
			throws ParameterCheckException;

	/**
	 * 获取意见列表，并按发布时间倒序排列；支持分页
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<TSuggestion> findSuggestion(String key, SuggestionType type,
			SuggestionState state, Pageable page);

	/**
	 * 在意见中查找
	 * 
	 * @param key
	 * @param page
	 * @return
	 */
	public List<TSuggestion> searchAllSuggestions(String key,
			SuggestionType type, SuggestionState state);

	/**
	 * 保存回复，并公开意见
	 * 
	 * @param suggestionId
	 * @param reply
	 */
	public void replyPublic(long suggestionId, TReply reply);

	/**
	 * 保存回复，但不公开意见
	 * 
	 * @param suggestionId
	 * @param reply
	 */
	public void replyPrivate(long suggestionId, TReply reply);

	/**
	 * 查找指定用户的意见
	 * 
	 * @param userId
	 * @return
	 */
	public Page<TSuggestion> findUserSuggesions(Long userId, String title,
			SuggestionType type, Pageable p);

	/**
	 * 意见审核
	 * 
	 * @param suggsId
	 * @throws ParameterCheckException 
	 */
	public void assessSuggestions(long suggsId, boolean checked) throws ParameterCheckException;

	/**
	 * 回复意见，并可以通过该方法设置是否审核通过
	 * 
	 * @param suggsId
	 *            意见id
	 * @param reply
	 *            回复内容
	 * @param checked
	 *            是否通过审核
	 * @throws ParameterCheckException 
	 */
	public void reply(long suggsId, String reply, boolean checked) throws ParameterCheckException;

}
