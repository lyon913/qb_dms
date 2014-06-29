package com.whr.dms.suggestion.service;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
	 * @param suggestionId
	 */
	public void deleteSuggestion(long suggestionId);
	
	/**
	 * 获取意见列表，并按发布时间倒序排列；支持分页
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<TSuggestion> getSuggestion(Pageable page);

	/**
	 * 在意见中查找
	 * @param key
	 * @param page
	 * @return
	 */
	public List<TSuggestion> searchAllSuggestions(String key);
	
	
}
