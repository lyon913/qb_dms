package com.whr.dms.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.whr.dms.exceptions.ParameterCheckException;
import com.whr.dms.models.TDepartment;
import com.whr.dms.models.TUser;
import com.whr.dms.models.TUserRole;
import com.whr.dms.models.TUser_TDepartment;
import com.whr.dms.security.RoleType;
import com.whr.dms.service.DepartmentManager;
import com.whr.dms.service.RoleManager;
import com.whr.dms.service.UserManager;
import com.whr.dms.web.propertyEditor.EntityPropertyEditor;
import com.whr.dms.web.propertyEditor.RolePropertyEditor;

@Controller
@SessionAttributes({ "user" })
@RequestMapping("/admin/user")
public class UserController {
	@Autowired
	UserManager um;

	@Autowired
	DepartmentManager dm;

	@Autowired
	RoleManager rm;

	@ModelAttribute("departList")
	public List<TDepartment> departList() {
		return dm.getAllDepartments();
	}

	@ModelAttribute("roleList")
	public List<RoleType> roleList() {
		return rm.findAllRoles();
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		//注册转换器
		binder.registerCustomEditor(TDepartment.class, new EntityPropertyEditor(TDepartment.class));
		binder.registerCustomEditor(TUserRole.class, new RolePropertyEditor());
	}

	/**
	 * 用户列表
	 * 
	 * @param m
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model m) {
		m.addAttribute("userList", um.findUserList());
		return "user/userList";
	}

	/**
	 * 初始化创建用户的表单
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String initCreateUserForm(Model m) {
		TUser user = new TUser();
		//新用户默认密码
		user.setPassword("1234");
		m.addAttribute("user", user);
		return "user/userEdit";
	}

	/**
	 * 保存用户
	 * 
	 * @param u
	 * @param departmentId
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String processCreateUserForm(@ModelAttribute("user") @Valid TUser user, 
			BindingResult bind,	SessionStatus status) {
		if(bind.hasErrors()) {
			return "user/userEdit";
		}
		try {
			um.saveUser(user);
			status.setComplete();
		} catch (ParameterCheckException e) {
			bind.rejectValue("loginName", "",e.getMessage());
			return "user/userEdit";
		}
		
		return "forward:/admin/user/list";
	}
	
	/**
	 * 初始化编辑用户表单
	 * @param id
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/{id}/edit" , method = RequestMethod.GET)
	public String initEditUserForm(@PathVariable long id, Model m) {
		TUser user = um.findUserById(id);
		m.addAttribute("user", user);
		return "user/userEdit";
	}
	
	/**
	 * 处理更新user的表单
	 * @param user
	 * @param bind
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public String processEditUserForm(@ModelAttribute("user") @Valid TUser user, BindingResult bind,
			SessionStatus status) {
		if(bind.hasErrors()) {
			return "user/userEdit";
		}
		
		try {
			um.saveUser(user);
			status.setComplete();
		} catch (ParameterCheckException e) {
			bind.rejectValue("loginName", "",e.getMessage());
			return "user/userEdit";
		}
		
		return "forward:/admin/user/list";
	}

	@RequestMapping(value = "/{id}/delete")
	public String del(@PathVariable long id) {
		um.deleteUser(id);
		return "forward:/admin/user/list";
	}
	
	@RequestMapping(value = "/{id}/resetPwd")
	public @ResponseBody String resetPwd(@PathVariable long id) {
		um.resetPwd(id);
		return "success";
	}

	@RequestMapping(value="/{id}/setReadDepartments",method=RequestMethod.GET)
	public String initSetReadDepartments(@PathVariable long id,Model m){
		List<TUser_TDepartment> udList = um.getReadDeparments(id);
		m.addAttribute("udList", udList);
		return "user/setReadDepartments";
	}
}
