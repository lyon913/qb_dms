package com.whr.dms.web.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whr.dms.models.TReply;
import com.whr.dms.models.TSuggestion;
import com.whr.dms.models.TUser;
import com.whr.dms.security.SecurityUtil;
import com.whr.dms.service.SuggestionReplyService;
import com.whr.dms.service.SuggestionService;
import com.whr.dms.service.UserManager;
import com.whr.dms.web.ajax.JsonResponse;

@Controller
public class SuggestionReplyController {
	
	public TUser getUser(){
		String username = SecurityUtil.getCurrentUserDetials().getUsername();
		TUser u = um.findTUserByLoginName(username);
		return u;
	}
	
	@Resource
	UserManager um;
	
	@Autowired
	SuggestionReplyService srservice;
	@Autowired
	SuggestionService sservice;
	
	@RequestMapping(value="/suggestion/suggestionReplyMana/{suggestionId}")
	public String tolist(@PathVariable long suggestionId,Model model){
		model.addAttribute(suggestionId);
		return "suggestion/suggestionReplyList";
	}
	@RequestMapping(value="/suggestion/suggestionReplyList")
	public @ResponseBody List<TReply> suggestionReplyList(long suggestionId) {
		//model.addAttribute("suggestionId",suggestionId);
		return srservice.getSuggestionReply(suggestionId);
	}
	

	@RequestMapping(value = "/suggestion/createSuggestionReply/{suggestionId}")
	public String replyCreatePage(@PathVariable long suggestionId,Model model){
		TSuggestion suggestion = sservice.findById(suggestionId);
		model.addAttribute("suggestion",suggestion);
		model.addAttribute("suggestionId",suggestionId);
		return "suggestion/createSuggestionReply";
	}
	

	
	
	@RequestMapping(value="/suggestion/suggestionReply",method = RequestMethod.POST)
	public @ResponseBody JsonResponse saveSuggestionReply(TReply reply){
		try{
			TUser user = getUser();
			reply.setAuthorId(user.getId());
			reply.setReplyDate(new Date());
			reply.setAuthor(user.getName());
			srservice.saveSuggestionReply(reply);
			return new JsonResponse(true, null, reply.getId());
		} catch(Exception e){
			return new JsonResponse(false, e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="/suggestion/editSuggestionReply/{id}")
	public String prepareEdit(@PathVariable long id,Model model){
		TReply s = srservice.getById(id);
		model.addAttribute("suggestionReply", s);
		model.addAttribute("suggestionId",s.getSuggestionId());
		return "suggestion/createSuggestionReply";
	}
	
	@RequestMapping(value="/suggestion/suggestionReply/{id}")
	public String prepareRead(@PathVariable long id,Model model){
		TReply s = srservice.getById(id);
		model.addAttribute("suggestionReply", s);
		return "suggestion/readSuggestionReply";
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/suggestion/delSuggestionReply/{id}")
	public @ResponseBody JsonResponse deleteSuggestionReply(@PathVariable long id){
		try{
			srservice.deleteSuggestionReply(id);
			return new JsonResponse(true, null, null);
		}catch(Exception e){
			return new JsonResponse(false, e.getMessage(), null);
		}
	}
}
