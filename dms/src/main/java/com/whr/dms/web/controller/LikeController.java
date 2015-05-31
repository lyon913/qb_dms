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
import org.springframework.web.bind.annotation.PathVariable;
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
	 * 处理新增点赞选项表单
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
		
		ls.saveLikeOption(option, pic.getInputStream(), uploadFolder);
		status.setComplete();
		return "redirect:/like/manage/optList";
	}
	
	@RequestMapping(value = "/option/{id}/edit", method = RequestMethod.GET)
	public String initEditForm(@PathVariable long id, Model m){
		TLikeOption option = ls.findOptionById(id);
		if(option == null) {
			throw new RuntimeException("未找到记录");
		}
		m.addAttribute("option", option);
		return "like/manage/createOrUpdate";
	}
	
	@RequestMapping(value = "/option/{id}/edit", method = RequestMethod.POST)
	public String processEditForm(@ModelAttribute("option") @Valid TLikeOption option, 
			BindingResult bind, SessionStatus status, MultipartFile pic, HttpServletRequest request) throws IOException {

		if (bind.hasErrors()) {
			return "like/manage/createOrUpdate";
		}
		
		String uploadFolder = request.getSession().getServletContext()
				.getRealPath("/") + "upload\\images\\like\\";
		
		if(pic == null || pic.isEmpty()) {
			ls.saveLikeOptionWithoutPicture(option);
		}else {
			ls.saveLikeOption(option, pic.getInputStream(), uploadFolder);
		}
		
		status.setComplete();
		return "redirect:/like/manage/optList";
	}
	
	@RequestMapping(value = "/option/{id}/delete", method = RequestMethod.GET)
	public String deleteOption(@PathVariable long id, Model m){
		TLikeOption option = ls.findOptionById(id);
		if(option == null) {
			throw new RuntimeException("未找到记录");
		}
		ls.deleteOptionCascade(id);
		return "redirect:/like/manage/optList";
	}
	
	@RequestMapping(value = "/show/{type}/{fk}", method = RequestMethod.GET)
	public String show(@PathVariable String type,@PathVariable long fk, Model m) throws ParameterCheckException {
		m.addAttribute("type", type);
		m.addAttribute("fk", fk);
		m.addAttribute("result", ls.getLikeCount(type, fk));
		return "like/show";
	}
	
	@RequestMapping(value = "/vote/{type}/{fk}/{optionId}", method = RequestMethod.GET)
	public String vote(@PathVariable String type,@PathVariable long fk, @PathVariable long optionId) throws ParameterCheckException {
		ls.like(type, fk, optionId);
		return "redirect:/like/show/{type}/{fk}";
	}
}
