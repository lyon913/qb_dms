package com.whr.dms.web.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.whr.dms.exceptions.ParameterCheckException;
import com.whr.dms.models.SuggestionState;
import com.whr.dms.models.SuggestionType;
import com.whr.dms.models.TReply;
import com.whr.dms.models.TSuggestion;
import com.whr.dms.models.TUser;
import com.whr.dms.security.RoleType;
import com.whr.dms.security.SecurityUtil;
import com.whr.dms.service.SuggestionReplyService;
import com.whr.dms.service.SuggestionService;
import com.whr.dms.web.form.AssessForm;

@Controller
@SessionAttributes({ "s", "assForm" })
@RequestMapping("/suggestion")
public class SuggestionController {

	@Autowired
	SuggestionService suggServ;
	@Autowired
	SuggestionReplyService srservice;

	/**
	 * 初始化创建意见表单
	 * 
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String initCreateForm(Model m) {
		TSuggestion s = new TSuggestion();
		s.setType(SuggestionType.Suggestion);
		s.setState(SuggestionState.Private);
		TUser u = SecurityUtil.getCurrentUser();
		s.setAuthor(u.getName());
		s.setAuthorId(u.getId());
		s.setSuggestionDate(new Date());
		m.addAttribute("s", s);
		return "suggestion/createOrUpdate";
	}

	/**
	 * 处理创建意见表单
	 * 
	 * @param s
	 * @param bind
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String processCreateForm(@ModelAttribute("s") @Valid TSuggestion s,
			BindingResult bind, SessionStatus status, RedirectAttributes ra) {
		if (bind.hasErrors()) {
			return "suggestion/createOrUpdate";
		}

		suggServ.saveSuggestion(s);
		status.setComplete();
		//return "redirect:/suggestion/list/my";
		ra.addFlashAttribute("afterSave", true);
		return "redirect:/suggestion/"+s.getId();
	}

	/**
	 * 初始化修改意见表单
	 * 
	 * @param id
	 * @param m
	 * @return
	 * @throws ParameterCheckException
	 */
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String initUpdateForm(@PathVariable long id, Model m)
			throws ParameterCheckException {
		TSuggestion s = suggServ.findById(id);
		if (s == null) {
			throw new ParameterCheckException("未找到此记录");
		}

		if (!SecurityUtil.isMe(s.getAuthorId())) {
			throw new ParameterCheckException("只允许意见作者本人修改。");
		}

		if (!SuggestionState.Private.equals(s.getState())) {
			throw new ParameterCheckException("该意见已经过审核，不允许修改。");
		}
		m.addAttribute("s", s);
		return "suggestion/createOrUpdate";
	}

	/**
	 * 处理意见簿修改表单
	 * 
	 * @param s
	 * @param bind
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public String processUpdateForm(@ModelAttribute("s") @Valid TSuggestion s,
			BindingResult bind, SessionStatus status) {
		if (bind.hasErrors()) {
			return "suggestion/createOrUpdate";
		}

		suggServ.saveSuggestion(s);
		status.setComplete();
		return "redirect:/suggestion/list/my";
	}

	/**
	 * 查看意见表单
	 * 
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String showForm(@PathVariable long id, Model m) {
		TSuggestion s = suggServ.findById(id);
		m.addAttribute("suggestion", s);
		List<TReply> rlist = srservice.getSuggestionReply(id);
		m.addAttribute("replyList", rlist);
		return "suggestion/readSuggestion";
	}

	/**
	 * 删除意见表单
	 * 
	 * @param id
	 * @param m
	 * @return
	 * @throws ParameterCheckException
	 */
	@RequestMapping(value = "/{id}/del/{returnType}", method = RequestMethod.GET)
	public String delete(@PathVariable long id, @PathVariable int returnType)
			throws ParameterCheckException {
		TSuggestion s = suggServ.findById(id);
		if (s == null) {
			throw new ParameterCheckException("未找到该条记录");
		}

		if (!SecurityUtil.isMe(s.getAuthorId())
				&& !SecurityUtil.hasRole(RoleType.ROLE_SUGGESTION_MANAGER)) {
			throw new ParameterCheckException("只有作者本人或者有权限的用户才能删除");
		}

		if (SuggestionState.Deleted.equals(s.getState())) {
			throw new ParameterCheckException("该意见已被删除，本次删除操作无效。");
		}
		suggServ.deleteSuggestion(id);

		String returnUrl = "/suggestion/list/my";
		if (returnType == 2) {
			returnUrl = "/suggestion/manage/list/all";
		}
		if (returnType == 3) {
			returnUrl = "/suggestion/manage/list/private";
		}
		return "redirect:" + returnUrl;
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
			@RequestParam(required = false) String key, Model m) {
		long userId = SecurityUtil.getUserId();
		Page<TSuggestion> result = suggServ.findUserSuggesions(userId, key,
				SuggestionType.Suggestion, p);
		m.addAttribute("result", result);
		m.addAttribute("key", key);
		return "suggestion/mySuggestionList";
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
			@RequestParam(required = false) String key, Model m) {

		Page<TSuggestion> result = suggServ.findSuggestion(key,
				SuggestionType.Suggestion, SuggestionState.Public, p);
		m.addAttribute("result", result);
		m.addAttribute("key", key);
		return "suggestion/publicList";
	}

	/**
	 * 待审核列表
	 * 
	 * @param p
	 * @param m
	 * @return
	 */
	@RequestMapping("/manage/list/private")
	public String privateList(
			@PageableDefault(page = 0, size = 20, sort = { "suggestionDate" }, direction = Direction.DESC) Pageable p,
			@RequestParam(required = false) String key, Model m) {
		Page<TSuggestion> result = suggServ.findSuggestion(key,
				SuggestionType.Suggestion, SuggestionState.Private, p);
		m.addAttribute("result", result);
		m.addAttribute("key", key);
		return "suggestion/manage/privateList";
	}

	/**
	 * 审核不通过列表
	 * 
	 * @param p
	 * @param m
	 * @return
	 */
	@RequestMapping("/manage/list/rejected")
	public String rejectedList(
			@PageableDefault(page = 0, size = 20, sort = { "suggestionDate" }, direction = Direction.DESC) Pageable p,
			@RequestParam(required = false) String key, Model m) {
		Page<TSuggestion> result = suggServ.findSuggestion(key,
				SuggestionType.Suggestion, SuggestionState.Rejected, p);
		m.addAttribute("result", result);
		m.addAttribute("key", key);
		return "suggestion/manage/rejectedList";
	}
	
	/**
	 * 初始化意见簿管理表单 即：初始化意见簿审核、回复的页面
	 * 
	 * @param p
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/manage/assess/{id}", method = RequestMethod.GET)
	public String initAssessForm(@PathVariable long id, Model m) {
		TSuggestion s = suggServ.findById(id);
		List<TReply> replyList = srservice.getSuggestionReply(id);
		AssessForm a = new AssessForm(s);
		m.addAttribute("suggestion", s);
		m.addAttribute("assForm", a);
		m.addAttribute("replyList", replyList);
		return "suggestion/manage/assess";
	}

	/**
	 * 处理审核表单
	 * 
	 * @param id
	 * @param form
	 * @param m
	 * @return
	 * @throws ParameterCheckException
	 */
	@RequestMapping(value = "/manage/assess/{id}", method = RequestMethod.POST)
	public String processAssessForm(@PathVariable long id, AssessForm form,
			Model m) throws ParameterCheckException {
		suggServ.reply(id, form.getReply(), form.isChecked());
		return "redirect:/suggestion/manage/list/private";
	}

	/**
	 * 全部意见列表（管理端），不包含已删除的记录
	 * 
	 * @param p
	 * @param key
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/manage/list/all")
	public String allList(
			@PageableDefault(page = 0, size = 20, sort = { "suggestionDate" }, direction = Direction.DESC) Pageable p,
			@RequestParam(required = false) String key, Model m) {
		Page<TSuggestion> result = suggServ.findAllSuggestions(key,
				SuggestionType.Suggestion, p);
		m.addAttribute("result", result);
		m.addAttribute("key", key);
		return "suggestion/manage/allList";
	}

	/**
	 * 删除回复
	 * @param id
	 * @return
	 * @throws ParameterCheckException 
	 */
	@RequestMapping(value = "/reply/{id}/delete", method = RequestMethod.GET)
	public @ResponseBody String deleteReply(@PathVariable long id) throws ParameterCheckException {
		srservice.deleteSuggestionReply(id);
		String result = "OK";
		return result;
	}

	/**
	 * 保存回复
	 * 
	 * @param id
	 * @param form
	 * @param m
	 * @return
	 * @throws ParameterCheckException
	 */
	@RequestMapping(value = "/{id}/reply", method = RequestMethod.POST)
	public String processReplyForm(@PathVariable long id,String reply) throws ParameterCheckException {
		suggServ.reply(id, reply);
		return "redirect:/suggestion/"+id;
	}
}
