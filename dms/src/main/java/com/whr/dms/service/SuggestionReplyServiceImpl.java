package com.whr.dms.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whr.dms.dao.TReplyDao;
import com.whr.dms.models.TReply;

@Service
public class SuggestionReplyServiceImpl implements SuggestionReplyService {

	@Resource
	private TReplyDao rdao;
	
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
	public void deleteSuggestionReply(long replyId) {
		rdao.delete(replyId);
	}

	@Override
	@Transactional
	public List<TReply> getSuggestionReply(long suggestionId) {
		return rdao.getReplyList(suggestionId);
	}

}
