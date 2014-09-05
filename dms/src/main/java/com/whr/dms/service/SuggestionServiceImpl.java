package com.whr.dms.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.whr.dms.dao.TReplyDao;
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
	@Autowired
	private TSuggestionDao sdao;
	
	@Autowired
	private TReplyDao rdao;

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
	public void deleteSuggestion(long suggestionId)
			throws ParameterCheckException {
		TSuggestion s = sdao.findOne(suggestionId);
		if (s == null) {
			throw new ParameterCheckException("未找到记录");
		}
		// 是否是作者本人
		boolean isMe = SecurityUtil.isMe(s.getAuthorId());

		// 是否为业务管理员
		boolean isManager = SecurityUtil.hasRole(getNeededRole(s.getType()));

		if (isMe || isManager) {
			// 有操作权
			s.setState(SuggestionState.Deleted);
			sdao.save(s);
		} else {
			// 拒绝
			throw new AccessDeniedException("没有操作权限。");
		}

	}

	/**
	 * 查询某状态下的意见列表
	 */
	@Override
	public Page<TSuggestion> findSuggestion(String key,SuggestionType type,
			SuggestionState state, Pageable page) {
		if(StringUtils.hasText(key)){
			key = "%" + key + "%";
		}else{
			key = "%";
		}
		Page<TSuggestion> sp = sdao.search(key, type, state, page);
		return sp;
	}

	@Override
	public Page<TSuggestion> findAllSuggestions(String key,
			SuggestionType type, Pageable page) {
		// TODO Auto-generated method stub
		if(StringUtils.hasText(key)){
			key = "%" + key + "%";
		}else{
			key = "%";
		}
		Page<TSuggestion> sp = sdao.findAll(key, type, page);
		return sp;
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
		} else if (SuggestionType.Managment.equals(type)) {
			return RoleType.ROLE_HOSPITAL_MANAGER;
		}
		throw new RuntimeException("意见簿类型匹配错误");
	}

	@Override
	public Page<TSuggestion> findUserSuggesions(Long userId, String title,
			SuggestionType type, Pageable p) {
		if(StringUtils.hasText(title)) {
			title = "%"+title+"%";
		}else {
			title = "%";
		}
		return sdao.findByUserId(userId, title, type, p);
	}

	@Transactional
	@Override
	public void assessSuggestions(long suggsId, boolean checked) throws ParameterCheckException {
		TSuggestion s = sdao.findOne(suggsId);
		
		if(s == null) {
			throw new ParameterCheckException("未找到记录");
		}
		
		if(SuggestionState.Deleted.equals(s.getState())) {
			throw new ParameterCheckException("记录已被删除，无法操作");
		}
		
		if (checked) {
			s.setState(SuggestionState.Public);
		}else {
			s.setState(SuggestionState.Private);
		}
		
		sdao.save(s);
		
	}

	@Transactional
	@Override
	public void reply(long suggsId, String reply, boolean checked) throws ParameterCheckException {
		TSuggestion s = sdao.findOne(suggsId);
		
		if(s == null) {
			throw new ParameterCheckException("未找到记录");
		}
		
		if(SuggestionState.Deleted.equals(s.getState())) {
			throw new ParameterCheckException("记录已被删除，无法操作");
		}
		
		//保存审核结果
		if (checked) {
			s.setState(SuggestionState.Public);
		}else {
			s.setState(SuggestionState.Private);
		}
		sdao.save(s);
		
		//如果同时提交了回复内容则保存回复
		if(StringUtils.hasText(reply)){
			TReply r = new TReply(suggsId,SecurityUtil.getCurrentUser(),reply);
			rdao.save(r);
		}

		
	}

	/**
	 * 保存回复内容，不用审核
	 */
	@Override
	public void reply(long suggsId, String reply)
			throws ParameterCheckException {
		//保存回复内容
		TReply r =  new TReply(suggsId,SecurityUtil.getCurrentUser(),reply);
		rdao.save(r);
		
	}

}
