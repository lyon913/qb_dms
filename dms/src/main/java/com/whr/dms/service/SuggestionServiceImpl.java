package com.whr.dms.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whr.dms.dao.TSuggestionDao;
import com.whr.dms.exceptions.ParameterCheckException;
import com.whr.dms.models.SuggestionState;
import com.whr.dms.models.SuggestionType;
import com.whr.dms.models.TReply;
import com.whr.dms.models.TSuggestion;
import com.whr.dms.security.RoleType;
import com.whr.dms.security.SecurityUtil;

@Service
public class SuggestionServiceImpl implements SuggestionService {
	@Resource
	private TSuggestionDao sdao;

	@Override
	@Transactional(readOnly = true)
	public TSuggestion findById(long id) {
		return sdao.findOne(id);
	}

	@Override
	@Transactional
	public void saveSuggestion(TSuggestion suggestion) {
		suggestion.setSuggestionDate(new Date());
		sdao.save(suggestion);
	}

	@Override
	@Transactional
	public void updateSuggestion(TSuggestion suggestion) {
		sdao.save(suggestion);
	}

	@Override
	@Transactional
	public void deleteSuggestion(long suggestionId) throws ParameterCheckException {
		TSuggestion s = sdao.findOne(suggestionId);
		if(s == null) {
			throw new ParameterCheckException("未找到记录");
		}
		//是否是作者本人
		boolean isMe = SecurityUtil.isMe(s.getAuthorId());
		
		//是否为业务管理员
		String needRole = getNeededRole(s.getType());
		boolean isManager = SecurityUtil.hasRole(needRole);
		
		if(isMe || isManager) {
			//有操作权
			s.setState(SuggestionState.Deleted);
			sdao.save(s);
		}else {
			//拒绝
			throw new AccessDeniedException("没有操作权限。");
		}
		

	}

	@Override
	public Page<TSuggestion> findSuggestion(SuggestionType type,
			SuggestionState state, Pageable page) {
		return null;
	}

	@Override
	public List<TSuggestion> searchAllSuggestions(String key,
			SuggestionType type, SuggestionState state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void replyPublic(long suggestionId, TReply reply) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void replyPrivate(long suggestionId, TReply reply) {
		// TODO Auto-generated method stub
		
	}
	
	
	/**
	 * 根据意见簿类型（意见簿、院长信箱、医院管理）查找需要的角色
	 * @param type
	 * @return
	 */
	private String getNeededRole(SuggestionType type) {
		if(SuggestionType.Suggestion.equals(type)) {
			return RoleType.ROLE_SUGGESTION_MANAGER.getName();
		}else if(SuggestionType.President.equals(type)) {
			return RoleType.ROLE_PRESIDENT_MANAGER.getName();
		}else if(SuggestionType.Managment.equals(type)) {
			return RoleType.ROLE_HOSPITAL_MANAGER.getName();
		}
		throw new RuntimeException("意见簿类型匹配错误");
	}

}
