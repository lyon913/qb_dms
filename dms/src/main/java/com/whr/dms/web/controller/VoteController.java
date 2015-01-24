package com.whr.dms.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.whr.dms.models.TUser;
import com.whr.dms.models.TVote;
import com.whr.dms.models.TVoteOption;
import com.whr.dms.models.VoteResult;
import com.whr.dms.security.SecurityUtil;
import com.whr.dms.service.VoteService;

@Controller
@RequestMapping("/vote")
@SessionAttributes(value = { "vote", "option" })
public class VoteController {
	@Autowired
	private VoteService vs;

	/**
	 * 初始化新增投票的表单
	 * 
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String initCreateForm(@RequestParam long suggId, Model m) {
		TUser u = SecurityUtil.getCurrentUser();
		
		TVote v = new TVote();
		v.setIsOpen(false);
		v.setIsMulti(false);
		v.setMaxVotes(2);
		v.setAuthorId(u.getId());
		v.setAuthorName(u.getUsername());
		v.setEndDate(new Date());
		
		m.addAttribute("vote", v);
		m.addAttribute("suggId", suggId);
		return "vote/createOrUpdate";
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
	public String processCreateForm(@RequestParam long suggId, String[] opt, @ModelAttribute("vote") @Valid TVote vote,
			BindingResult bind, SessionStatus status, Model m) throws ParameterCheckException {
		if(opt == null || opt.length < 2) {
			bind.rejectValue("title", null, "至少需要2个投票选项");
		}
		
		if(vote.getIsMulti() && vote.getMaxVotes()> opt.length) {
			bind.rejectValue("maxVotes", null,"必须大于2并且不超过选总项数");
		}
		
		if(vote.getIsMulti() && vote.getMaxVotes()< 2) {
			bind.rejectValue("maxVotes", null,"必须大于2并且不超过选项总数");
		}
		
		if(vs.isExpired(vote.getEndDate())) {
			bind.rejectValue("endDate", null,"投票截止日期必须大于当前日期");
		}
		
		if (bind.hasErrors()) {
			m.addAttribute("opt", opt);
			return "vote/createOrUpdate";
		}

		List<TVoteOption> optList = new ArrayList<TVoteOption>();
		for(String optionTitle : opt) {
			TVoteOption option = new TVoteOption();
			option.setTitle(optionTitle);
			option.setVote(vote);
			optList.add(option);
		}
		
		vote.setOptions(optList);
		vs.addVote(vote, suggId);
		status.setComplete();
		return "redirect:/vote/" + vote.getId();
	}

	/**
	 * 初始化修改投票的表单
	 * 
	 * @param id
	 * @param m
	 * @return
	 */
//	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
//	public String initEditForm(@PathVariable long id, Model m) {
//		TVote v = vs.findById(id);
//		m.addAttribute("vote", v);
//		return "vote/createOrUpdate";
//	}

	/**
	 * 处理修改投票的表单
	 * 
	 * @param vote
	 * @param status
	 * @param bind
	 * @return
	 */
//	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
//	public String prosessEditForm(@ModelAttribute("vote") @Valid TVote vote,
//			BindingResult bind, SessionStatus status) {
//		if (bind.hasErrors()) {
//			return "vote/createOrUpdate";
//		}
//		vs.saveVote(vote);
//		status.setComplete();
//		return "redirect:/vote/{id}/edit";
//	}

//	@RequestMapping(value = "/{voteId}/options/new", method = RequestMethod.GET)
//	public String initOptionCreateForm(@PathVariable long voteId, Model m)
//			throws ParameterCheckException {
//		TVote v = vs.findById(voteId);
//		if (v == null) {
//			throw new ParameterCheckException("voteId无效");
//		}
//		TVoteOption opt = new TVoteOption();
//		m.addAttribute("option", opt);
//		return "vote/createOrUpdateOpt";
//	}
//
//	@RequestMapping(value = "/{voteId}/options/new", method = RequestMethod.POST)
//	public String processOptionCreateForm(@PathVariable long voteId, 
//			@ModelAttribute("option") @Valid TVoteOption opt, 
//			BindingResult bind, SessionStatus status) {
//		if (bind.hasErrors()) {
//			return "vote/createOrUpdate";
//		}
//		vs.saveVoteOption(voteId, opt);
//		status.setComplete();
//		return "redirect:/vote/{id}/edit";
//	}

	/**
	 * 显示投票统计结果
	 * @param id
	 * @param m
	 * @return
	 * @throws ParameterCheckException 
	 */
	@RequestMapping(value = "/{id}")
	public String show(@PathVariable long id, Model m) throws ParameterCheckException {
		TVote v = vs.findById(id);
		VoteResult vr = vs.getVoteResult(id);
		
		m.addAttribute("v", v);
		m.addAttribute("result", vr);
		m.addAttribute("isExpired", vs.isExpired(v.getEndDate()));
		return "vote/show";
	}
	

	/**
	 * 进行投票操作
	 * @param id
	 * @param optionId
	 * @param ra
	 * @return
	 */
	@RequestMapping(value = "/{id}/doVote", method = RequestMethod.POST)
	public String vote(@PathVariable long id, @RequestParam("optSel") long[] optionId,
			RedirectAttributes ra) {

		try {
			//投票操作
			TUser u = SecurityUtil.getCurrentUser();
			vs.vote(id, optionId, u.getId(), u.getName());
		} catch (ParameterCheckException e) {
			//错误信息反馈到跳转后的页面中
			ra.addFlashAttribute("err", e.getMessage());
		}
		return "redirect:/vote/{id}";
	}
	
	/**
	 * 获取记名投票的投票明细信息
	 * @param id
	 * @param m
	 * @return
	 * @throws ParameterCheckException
	 */
	@RequestMapping(value = "/{id}/details", method = RequestMethod.GET)
	public String showDetails(@PathVariable long id,Model m) throws ParameterCheckException {
		m.addAttribute("result", vs.getVoteDetails(id));
		return "vote/details";
	}

}
