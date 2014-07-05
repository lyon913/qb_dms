package com.whr.dms.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.whr.dms.security.SecurityUtil;
import com.whr.dms.service.AccountService;
import com.whr.dms.web.ajax.JsonResponse;

@Controller
@RequestMapping("/account")
public class AccountController {
	private static Logger log = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	AccountService as;
	
	@RequestMapping("/changePWPage")
	public String changePWPage(){
		return "account/changePW";
	}
	
	@RequestMapping(value = "/checkPassword", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse checkPassword(String passwordCheck){
		String password = SecurityUtil.getCurrentUserDetials().getPassword();
		if(password.equals(passwordCheck)){
			return new JsonResponse(true, null, null);
		}
		return new JsonResponse(false, "密码无效", null);
	}
	
	@RequestMapping(value = "/changePW",method = RequestMethod.POST)
	public ModelAndView changePW(String oldPW,String newPW,ModelAndView mv){
		String msg = "";
		try {
			if (newPW != null && newPW.length()>=4) {
				String loginName = SecurityUtil.getCurrentUserDetials().getUsername();
				as.changePW(loginName, oldPW, newPW);
				mv.setViewName("account/changePWSuccess");
				return mv;
			}else{
				msg = "密码不能小于4个字符";
			}
		} catch (Throwable e) {
			log.debug(e.getMessage(), e);
			msg = e.getMessage();
		}
		mv.getModel().put("error", msg);
		mv.setViewName("account/changePW");
		return mv;
	}

}
