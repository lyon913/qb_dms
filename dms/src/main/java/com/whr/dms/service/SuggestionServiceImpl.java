package com.whr.dms.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whr.dms.dao.TSuggestionDao;
import com.whr.dms.models.TSuggestion;

@Service
public class SuggestionServiceImpl implements SuggestionService {
	@Resource
	private TSuggestionDao sdao;

	@Override
	@Transactional(readOnly = true)
	public TSuggestion getById(long id) {
		return sdao.findOne(id);
	}

	@Override
	@Transactional
	public void saveSuggestion(TSuggestion suggestion) {
		sdao.save(suggestion);
	}

	@Override
	@Transactional
	public void updateSuggestion(TSuggestion suggestion) {
		sdao.save(suggestion);
	}

	@Override
	@Transactional
	public void deleteSuggestion(long suggestionId) {
		sdao.delete(suggestionId);
	}

	@Override
	@Transactional(readOnly=true)
	public Page<TSuggestion> getSuggestion(Pageable page) {
		return sdao.getSuggestion(page);
	}

	@Override
	@Transactional(readOnly=true)
	public List<TSuggestion> searchAllSuggestions(String key) {
		return sdao.getSuggestionByNameLike("%" + key + "%");
	}
}
