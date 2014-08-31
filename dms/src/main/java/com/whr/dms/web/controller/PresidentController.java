package com.whr.dms.web.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.whr.dms.exceptions.ParameterCheckException;
import com.whr.dms.models.SuggestionState;
import com.whr.dms.models.SuggestionType;
import com.whr.dms.models.TSuggestion;
import com.whr.dms.models.TUser;
import com.whr.dms.security.SecurityUtil;
import com.whr.dms.service.SuggestionReplyService;
import com.whr.dms.service.SuggestionService;

@Controller
@SessionAttributes({ "s" })
@RequestMapping("/president")
public class PresidentController {
	@Autowired
	SuggestionService suggServ;
	@Autowired
	SuggestionReplyService srservice;

	/**
	 * 初始化创建院长信箱表单
	 * 
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String initCreateForm(Model m) {
		TSuggestion s = new TSuggestion();
		s.setType(SuggestionType.President);
		s.setState(SuggestionState.Private);
		TUser u = SecurityUtil.getCurrentUser();
		s.setAuthor(u.getName());
		s.setAuthorId(u.getId());
		s.setSuggestionDate(new Date());
		m.addAttribute("s", s);
		return "president/createOrUpdate";
	}
	
	/**
	 * 处理创建院长信箱表单
	 * 
	 * @param s
	 * @param bind
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String processCreateForm(@ModelAttribute("s") @Valid TSuggestion s,
			BindingResult bind, SessionStatus status) {
		if (bind.hasErrors()) {
			return "president/createOrUpdate";
		}

		suggServ.saveSuggestion(s);
		status.setComplete();
		return "redirect:/president/list/my";
	}
	
	/**
	 * 用户本人的意见列表
	 * 
	 * @param p
	 * @param m
	 * @return
	 */
	@RequestMapping("/list/my")
	public String myList(
			@PageableDefault(page = 0, size = 20, sort = { "suggestionDate" }, direction = Direction.DESC) Pageable p,
			@RequestParam(required = false)String key, Model m) {
		long userId = SecurityUtil.getUserId();
		Page<TSuggestion> result = suggServ.findUserSuggesions(userId, key, SuggestionType.President, p);
		m.addAttribute("result", result);
		m.addAttribute("key", key);
		return "president/myPresidentList";
	}

	/**
	 * 意见公共列表
	 * 
	 * @param p
	 * @param m
	 * @return
	 */
	@RequestMapping("/list/public")
	public String publicList(
			@PageableDefault(page = 0, size = 20, sort = { "suggestionDate" }, direction = Direction.DESC) Pageable p,
			@RequestParam(required = false)String key, Model m) {
	
		Page<TSuggestion> result = suggServ.findSuggestion(key,SuggestionType.President, SuggestionState.Public, p);
		m.addAttribute("result", result);
		m.addAttribute("key", key);
		return "president/publicList";
	}
}
