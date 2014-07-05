package com.whr.dms.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.whr.dms.models.TUser;
import com.whr.dms.security.SecurityUtil;
import com.whr.dms.service.PortletService;
import com.whr.dms.service.UserManager;

@Controller
public class HomeController {
	@Resource
	PortletService ps;
	
	@Resource
	UserManager um;
	
	public TUser getUser(){
		String username = SecurityUtil.getCurrentUserDetials().getUsername();
		TUser u = um.getTUserByLoginName(username);
		return u;
	}
	
	@RequestMapping("/home")
	public ModelAndView homePage(ModelAndView mv){
		TUser u = getUser();
		long departmentId = u.getDepartment().getId();
		mv.addObject("latestFiles", ps.getLatestFileList(10));
		mv.addObject("latestNotices", ps.getLatestNoticeList(departmentId,10));
		mv.addObject("latestSuggestions", ps.getLatestSuggestion(10));
		mv.addObject("latestPubNews",ps.getLatestPubNews(10));
		mv.addObject("latestSoftwares",ps.getLatestSoftware(10));
		mv.setViewName("home");
		return mv;
	}
	
	@RequestMapping("/about")
	public String about(){
		return "about_us";
	}
}
