package com.whr.dms.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.whr.dms.exceptions.ParameterCheckException;
import com.whr.dms.models.TLikeOption;
import com.whr.dms.service.LikeService;

@Controller
@SessionAttributes("option")
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
	@RequestMapping(value = "/option/new", method = RequestMethod.GET)
	public String noticeCreatePage(Model m){
		TLikeOption option = new TLikeOption();
		
		m.addAttribute("option", option);
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
	 * @throws IOException 
	 */
	@RequestMapping(value = "/option/new", method = RequestMethod.POST)
	public String processCreateForm( @ModelAttribute("option") @Valid TLikeOption option, 
			BindingResult bind, SessionStatus status, MultipartFile pic, HttpServletRequest request) throws ParameterCheckException, IOException {
		
		if(pic == null || pic.isEmpty()) {
			bind.rejectValue("picture", null, "请选择一张图片");
		}
		if (bind.hasErrors()) {
			return "like/manage/createOrUpdate";
		}
		
		String uploadFolder = request.getSession().getServletContext()
				.getRealPath("/") + "upload\\images\\like\\";
		
		ls.addLikeOption(option, pic.getInputStream(), uploadFolder);
		status.setComplete();
		return "redirect:/like/manage/optList";
	}
}
