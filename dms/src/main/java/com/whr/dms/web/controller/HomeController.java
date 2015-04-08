package com.whr.dms.web.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.whr.dms.models.TNotice;
import com.whr.dms.models.TUser;
import com.whr.dms.security.SecurityUtil;
import com.whr.dms.service.AuditService;
import com.whr.dms.service.PortletService;
import com.whr.dms.service.UserManager;

@Controller
public class HomeController {
	@Resource
	PortletService ps;
	
	@Resource
	AuditService as;
	
	@Resource
	UserManager um;
	
	public TUser getUser(){
		String username = SecurityUtil.getCurrentUserDetials().getUsername();
		TUser u = um.findTUserByLoginName(username);
		return u;
	}
	
	@RequestMapping("/main")
	public String mainPage(Model m){
		TUser u = getUser();
		long departmentId = u.getDepartment().getId();
		Date d = new Date();
		Calendar c = new GregorianCalendar();
		c.setTime(d);
		c.add(Calendar.DATE, -3); //往前推三天。
		d = c.getTime();
		m.addAttribute("emergencyCounts",ps.getEmergencyNoticeCount(departmentId, d));
		return "main";
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
		mv.addObject("totalCounts",as.getLoginCounts());
		mv.addObject("curDayCounts", as.getLoginCountsCurDay());
		mv.setViewName("home/home");
		return mv;
	}
	
	@RequestMapping("/emergency")
	public ModelAndView emergencyPage(ModelAndView mv){
		TUser u = getUser();
		long departmentId = u.getDepartment().getId();
		Date d = new Date();
		Calendar c = new GregorianCalendar();
		c.setTime(d);
		c.add(Calendar.DATE, -3); //往前推三天。
		d = c.getTime();
		Sort sort = new Sort(Direction.DESC, "createTime");
		Pageable page = new PageRequest(0, 10, sort);
		Page<TNotice> result = ps.getEmergencyNotices(departmentId, d, page);
		mv.addObject("result",result);	
		mv.setViewName("notice/emergencyNoticeList");
		return mv;
	}
	
	@RequestMapping("/about")
	public String about(){
		return "home/about_us";
	}
}
