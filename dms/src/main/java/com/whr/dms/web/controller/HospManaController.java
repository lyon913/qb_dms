package com.whr.dms.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.AccessDeniedException;
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
import org.springframework.web.multipart.MultipartFile;

import com.whr.dms.exceptions.ParameterCheckException;
import com.whr.dms.models.AttachmentType;
import com.whr.dms.models.SuggestionState;
import com.whr.dms.models.SuggestionType;
import com.whr.dms.models.TAttachment;
import com.whr.dms.models.TReply;
import com.whr.dms.models.TSuggestion;
import com.whr.dms.models.TUser;
import com.whr.dms.security.RoleType;
import com.whr.dms.security.SecurityUtil;
import com.whr.dms.service.SuggestionReplyService;
import com.whr.dms.service.SuggestionService;
import com.whr.dms.utils.UploadUtils;

@Controller
@SessionAttributes({ "s", "assForm" })
@RequestMapping("/hospMana")
public class HospManaController {
	@Autowired
	SuggestionService suggServ;
	@Autowired
	SuggestionReplyService srservice;

	/**
	 * 初始化创建医院管理意见
	 * 
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String initCreateForm(Model m) {
		TSuggestion s = new TSuggestion();
		s.setType(SuggestionType.Management);
		s.setState(SuggestionState.Public);
		TUser u = SecurityUtil.getCurrentUser();
		s.setAuthor(u.getName());
		s.setAuthorId(u.getId());
		s.setSuggestionDate(new Date());
		m.addAttribute("s", s);
		return "hospMana/createOrUpdate";
	}

	/**
	 * 处理创建医院管理意见表单
	 * 
	 * @param s
	 * @param bind
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String processCreateForm(@ModelAttribute("s") @Valid TSuggestion s, 
			HttpSession session, BindingResult bind, SessionStatus status) {
		if (bind.hasErrors()) {
			return "hospMana/createOrUpdate";
		}

			suggServ.saveSuggestion(s);
		
		status.setComplete();
		return "redirect:/hospMana/" + s.getId()+"/afterSave";
	}

	/**
	 * 初始化修改医院管理意见表单
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
			throw new AccessDeniedException("只有作者本人才能修改");
		}
		m.addAttribute("s", s);
		return "hospMana/createOrUpdate";
	}

	/**
	 * 处理医院管理修改表单
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
			return "hospMana/createOrUpdate";
		}

		suggServ.saveSuggestion(s);
		status.setComplete();
		return "redirect:/hospMana/list/my";
	}

	/**
	 * 查看医院管理意见表单
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
		return "hospMana/readSuggestion";
	}

	/**
	 * 查看医院管理意见表单
	 * 
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/{id}/afterSave", method = RequestMethod.GET)
	public String afterSaveForm(@PathVariable long id, Model m) {
		TSuggestion s = suggServ.findById(id);
		m.addAttribute("suggestion", s);
		return "hospMana/afterSuggestionSave";
	}
	
	
	/**
	 * 删除医院管理意见表单
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
			throw new ParameterCheckException("未找到此记录");
		}

		if (!SecurityUtil.isMe(s.getAuthorId())
				&& !SecurityUtil.hasRole(RoleType.ROLE_ADMIN)) {
			throw new AccessDeniedException("只有作者本人或者管理员才能删除");
		}

		if (SuggestionState.Deleted.equals(s.getState())) {
			throw new AccessDeniedException("帖子已经删除！");
		}
		suggServ.deleteSuggestion(id);
		String returnUrl = "";
		if(returnType==2){
			returnUrl = "/hospMana/list/public";
		}
		else if(returnType==1){
			returnUrl = "hospMana/list/my";
		}
		
		return "redirect:" + returnUrl;
	}

	/**
	 * 用户本人的医院管理意见列表
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
				SuggestionType.Management, p);
		m.addAttribute("result", result);
		m.addAttribute("key", key);
		return "hospMana/mySuggestionList";
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
				SuggestionType.Management, SuggestionState.Public, p);
		m.addAttribute("result", result);
		m.addAttribute("key", key);
		return "hospMana/publicList";
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
		return "redirect:/hospMana/"+id;
	}
	
	
	

}
