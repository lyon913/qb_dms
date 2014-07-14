package com.whr.dms.web.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.whr.dms.models.SuggestionState;
import com.whr.dms.models.SuggestionType;
import com.whr.dms.models.TSuggestion;

/**
 * 院长信箱
 * 
 * @author Lyon
 *
 */
@Controller
@RequestMapping("/pm")
@SessionAttributes({ "sugg" })
public class PresidentMailboxController {

	/**
	 * 初始化创建意见的表单
	 * 
	 * @return
	 */
	public String initCreateForm(Model m) {
		TSuggestion s = new TSuggestion();
		// 初始化类型和状态
		// 类型为院长信箱
		s.setType(SuggestionType.President);
		// 状态私有
		s.setState(SuggestionState.Private);
		m.addAttribute("sugg", s);
		return "pm/createOrUpdate";
	}

	public String processCreateForm(
			@ModelAttribute("sugg") @Valid TSuggestion s, BindingResult bind,
			SessionStatus status) {
		if(bind.hasErrors()) {
			return "pm/createOrUpdate";
		}
		
		return null;
	}

	public String initUpdateForm() {
		return null;
	}

	public String processUpdateForm() {
		return null;
	}

	/**
	 * 已公开的意见列表
	 * 
	 * @return
	 */
	public String publicList() {
		return null;
	}

	/**
	 * 待回复的列表（管理员可见）
	 * 
	 * @return
	 */
	public String notRepliedList() {
		return null;
	}

	/**
	 * 全部列表（管理员可见）
	 * 
	 * @return
	 */
	public String allList() {
		return null;
	}

}
