package com.whr.dms.web.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whr.dms.models.TReply;
import com.whr.dms.models.TSuggestion;
import com.whr.dms.models.TUser;
import com.whr.dms.security.SecurityUtil;
import com.whr.dms.service.SuggestionReplyService;
import com.whr.dms.service.SuggestionService;
import com.whr.dms.service.UserManager;
import com.whr.dms.web.ajax.ContentRangeHeader;
import com.whr.dms.web.ajax.DojoSort;
import com.whr.dms.web.ajax.JsonResponse;
import com.whr.dms.web.ajax.PageableRange;

@Controller
public class SuggestionController {
	public TUser getUser(){
		String username = SecurityUtil.getCurrentUserDetials().getUsername();
		TUser u = um.getTUserByLoginName(username);
		return u;
	}
	
	@Resource
	UserManager um;
	
	@Autowired
	SuggestionService sservice;
	@Autowired
	SuggestionReplyService srservice;
	
	@RequestMapping(value="/suggestion/suggestionMana")
	public String tolist(){
		return "suggestion/suggestionList";
	}
	@RequestMapping(value="/suggestion/suggestionList")
	public ResponseEntity<List<TSuggestion>> suggestionList(@RequestParam(required = false)String sort,@RequestHeader("Range") String range) {
		PageableRange pr = new PageableRange(range,new DojoSort(sort).toSpringSort());
		Page<TSuggestion> page = sservice.getSuggestion(pr);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Range", new ContentRangeHeader(pr.getOffset(), page.getNumberOfElements(), page.getTotalElements()).toString());
		
		return new ResponseEntity<List<TSuggestion>>(page.getContent(), headers, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/suggestion/create")
	public String noticeCreatePage(){
		return "suggestion/createSuggestion";
	}
	

	
	
	@RequestMapping(value="/suggestion",method = RequestMethod.POST)
	public @ResponseBody JsonResponse saveSuggestion(TSuggestion suggestion){
		try{
			TUser user = getUser();
			suggestion.setAuthorId(user.getId());
			suggestion.setSuggestionDate(new Date());
			suggestion.setAuthor(user.getName());
			sservice.saveSuggestion(suggestion);
			return new JsonResponse(true, null, suggestion.getId());
		} catch(Exception e){
			return new JsonResponse(false, e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="/suggestion/edit/{id}")
	public String prepareEdit(@PathVariable long id,Model model){
		TSuggestion s = sservice.getById(id);
		if(s.getAuthorId() != getUser().getId()){
			throw new AccessDeniedException("只有作者才能修改该意见。");
		}
		model.addAttribute("suggestion", s);
		return "suggestion/createSuggestion";
	}
	
	@RequestMapping(value="/suggestion/{id}")
	public String prepareRead(@PathVariable long id,Model model){
		TSuggestion s = sservice.getById(id);
		List<TReply> list = srservice.getSuggestionReply(id);
		model.addAttribute("suggestion", s);
		model.addAttribute("replyList",list);
		return "suggestion/readSuggestion";
	}
	
	/**
	 * 在全部意见中查找
	 * @param sort
	 * @param range
	 * @return
	 */
	@RequestMapping(value = "/suggestion/searchAll", method = RequestMethod.POST)
	public @ResponseBody List<TSuggestion> searchAll(String key) {
		return sservice.searchAllSuggestions(key);

	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/suggestion/del/{id}")
	public @ResponseBody JsonResponse deleteSuggestion(@PathVariable long id){
		try{
			sservice.deleteSuggestion(id);
			return new JsonResponse(true, null, null);
		}catch(Exception e){
			return new JsonResponse(false, e.getMessage(), null);
		}
	}
}
