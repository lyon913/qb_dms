package com.whr.dms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whr.dms.dao.TReplyDao;
import com.whr.dms.dao.TSuggestionDao;
import com.whr.dms.exceptions.ParameterCheckException;
import com.whr.dms.models.SuggestionType;
import com.whr.dms.models.TReply;
import com.whr.dms.models.TSuggestion;
import com.whr.dms.security.RoleType;
import com.whr.dms.security.SecurityUtil;

@Service
public class SuggestionReplyServiceImpl implements SuggestionReplyService {

	@Autowired
	private TReplyDao rdao;
	@Autowired
	private TSuggestionDao sdao;
	
	@Override
	@Transactional(readOnly = true)
	public TReply getById(long id) {
		return rdao.findOne(id);
	}

	@Override
	@Transactional
	public void saveSuggestionReply(TReply reply) {
		rdao.save(reply);
	}

	@Override
	@Transactional
	public void updateSuggestionReply(TReply reply) {
		rdao.save(reply);
	}

	@Override
	@Transactional
	public void deleteSuggestionReply(long replyId) throws ParameterCheckException {
		TReply r = rdao.findOne(replyId);
		
		if (r == null) {
			throw new ParameterCheckException("未找到记录");
		}
		
		TSuggestion s = sdao.findOne(r.getSuggestionId());
		// 是否是作者本人
		boolean isMe = SecurityUtil.isMe(r.getAuthorId());

		// 是否为业务管理员
		boolean isManager = SecurityUtil.hasRole(getNeededRole(s.getType()));

		if (isMe || isManager) {
			// 有操作权
			rdao.delete(replyId);
		} else {
			// 拒绝
			throw new AccessDeniedException("没有操作权限。");
		}
		
	}

	@Override
	@Transactional
	public List<TReply> getSuggestionReply(long suggestionId) {
		return rdao.getReplyList(suggestionId);
	}
	
	/**
	 * 根据意见簿类型（意见簿、院长信箱、医院管理）查找需要的角色
	 * 
	 * @param type
	 * @return
	 */
	private RoleType getNeededRole(SuggestionType type) {
		if (SuggestionType.Suggestion.equals(type)) {
			return RoleType.ROLE_SUGGESTION_MANAGER;
		} else if (SuggestionType.President.equals(type)) {
			return RoleType.ROLE_PRESIDENT_MANAGER;
		} else if (SuggestionType.Management.equals(type)) {
			return RoleType.ROLE_HOSPITAL_MANAGER;
		}
		throw new RuntimeException("意见簿类型匹配错误");
	}

}
