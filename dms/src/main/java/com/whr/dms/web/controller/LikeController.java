package com.whr.dms.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.whr.dms.models.SuggestionType;
import com.whr.dms.models.TLikeOption;
import com.whr.dms.models.TNotice;
import com.whr.dms.models.TNoticeType;
import com.whr.dms.models.TSuggestion;
import com.whr.dms.models.TUser;
import com.whr.dms.models.TVote;
import com.whr.dms.models.TVoteOption;
import com.whr.dms.models.VoteResult;
import com.whr.dms.security.SecurityUtil;
import com.whr.dms.service.LikeService;
import com.whr.dms.web.ajax.JsonResponse;

@Controller
@RequestMapping("/like")
public class LikeController {
	@Autowired
	private LikeService ls;

	
	/**
	 * 点赞选项列表
	 * 
	 * @param m
	 * @return
	 */
	@RequestMapping("/manage/optList")
	public String optList(Model m) {
		
		List<TLikeOption> opt = ls.findAll();
		m.addAttribute("opt", opt);
		
		return "like/manage/optionList";
	}

	/**
	 * 新建点赞选项页面
	 * @return
	 */
	@RequestMapping(value = "/create")
	public String noticeCreatePage(Model m){
		
		return "like/manage/createOrUpdate";
	}
	

	/**
	 * 处理新增点赞表单
	 * 
	 * @param TLikeOption
	 * @param status
	 * @param bind
	 * @return 跳转到编辑页
	 * @throws ParameterCheckException 
	 */
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String processCreateForm( TLikeOption opt,
			BindingResult bind, Model m) throws ParameterCheckException {
		if (bind.hasErrors()) {
			m.addAttribute("opt", opt);
			return "manage/optMana";
		}
		TLikeOption o = new TLikeOption();
		if(opt.getId()!=null){
			o.setId(opt.getId());
		}
		o.setTitle(opt.getTitle());
		o.setPicture("无图片");
		ls.addLikeOption(o);
		return "redirect:/like/manage/optMana";
	}
}
