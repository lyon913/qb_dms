package com.whr.dms.web.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	 * 处理创建院长信箱意见表单
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
			return "president/createPresident";
		}

		suggServ.saveSuggestion(s);
		status.setComplete();
		return "president/list";
	}

	/**
	 * 初始化修改院长信箱意见表单
	 * 
	 * @param id
	 * @param m
	 * @return
	 * @throws ParameterCheckException
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String initUpdateForm(@PathVariable long id, Model m)
			throws ParameterCheckException {
		TSuggestion s = suggServ.findById(id);
		if (s == null) {
			throw new ParameterCheckException("未找到此记录");
		}

		if (!SecurityUtil.isMe(s.getAuthorId())) {
			throw new AccessDeniedException("只有作者本人才能修改");
		}

		if (SuggestionState.Public.equals(s.getState())) {
			throw new AccessDeniedException("意见已经过审核，不能修改。");
		}
		m.addAttribute("s", s);
		return "president/createPresident";
	}

	/**
	 * 处理院长信箱修改表单
	 * 
	 * @param s
	 * @param bind
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String processUpdateForm(TSuggestion s, BindingResult bind,
			SessionStatus status) {
		if (bind.hasErrors()) {
			return "president/createPresident";
		}

		suggServ.saveSuggestion(s);
		status.setComplete();
		return "president/createPresident";
	}

	/**
	 * 待审核列表
	 * 
	 * @param p
	 * @param m
	 * @return
	 */
	@RequestMapping("/assess/list")
	public String privateList(@PageableDefault(page = 0, size = 20) Pageable p,
			Model m) {
		Page<TSuggestion> result = suggServ.findSuggestion(
				SuggestionType.President, SuggestionState.Private, p);
		m.addAttribute("result", result);
		return "president/assessList";
	}

	/**
	 * 初始化院长信箱管理表单 即：初始化院长信箱审核、回复的页面
	 * 
	 * @param p
	 * @param m
	 * @return
	 */
	@RequestMapping("/assess/{id}")
	public String initAssessForm(long id, Model m) {
		TSuggestion s = suggServ.findById(id);
		m.addAttribute("s", s);
		return "president/assessList";
	}
	
	@RequestMapping(value = "/public", method = RequestMethod.GET)
	public String publicList() {
		return "president/public";
	}
}
