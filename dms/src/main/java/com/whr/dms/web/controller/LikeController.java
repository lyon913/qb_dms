package com.whr.dms.web.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.whr.dms.exceptions.ParameterCheckException;
import com.whr.dms.models.SuggestionType;
import com.whr.dms.models.TLikeOption;
import com.whr.dms.models.TSuggestion;
import com.whr.dms.models.TUser;
import com.whr.dms.models.TVote;
import com.whr.dms.models.TVoteOption;
import com.whr.dms.models.VoteResult;
import com.whr.dms.security.SecurityUtil;
import com.whr.dms.service.LikeService;

@Controller
@RequestMapping("/like")
@SessionAttributes(value = { "like", "option" })
public class LikeController {
	@Autowired
	private LikeService ls;

	
	/**
	 * 点赞选项列表
	 * 
	 * @param p
	 * @param m
	 * @return
	 */
	@RequestMapping("/manage/optMana")
	public String optList(Model m) {
		
		List<TLikeOption> opt = ls.findAll();
		m.addAttribute("opt", opt);
		
		return "manage/optMana";
	}

	
	/**
	 * 处理新增投票表单
	 * 
	 * @param vote
	 * @param status
	 * @param bind
	 * @return 跳转到编辑页
	 * @throws ParameterCheckException 
	 */
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String processCreateForm( String[] opt,
			BindingResult bind, SessionStatus status, Model m) throws ParameterCheckException {
		if (bind.hasErrors()) {
			m.addAttribute("opt", opt);
			return "manage/optMana";
		}

		
		return "redirect:/like/manage/optMana";
	}
}
