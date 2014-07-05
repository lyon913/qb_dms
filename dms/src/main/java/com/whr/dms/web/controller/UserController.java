package com.whr.dms.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.whr.dms.models.TUser;
import com.whr.dms.service.DepartmentManager;
import com.whr.dms.service.UserManager;

@Controller
@RequestMapping("/admin/user")
public class UserController {
	@Resource
	UserManager um;
	
	@Resource
	DepartmentManager dm;
	
	@RequestMapping("/list")
	public String list(Model m){
		m.addAttribute("userList", um.getUserList());
		return "user/userList";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(TUser u, long departmentId, Model m) {
		if (u.getId() == null && um.getTUserByLoginName(u.getLoginName()) != null) {
			m.addAttribute("error", "该用户名已存在。");
			return "forward:/admin/user/edit";
		}
		u.setDepartment(dm.getById(departmentId));
		um.saveUser(u);
		return "forward:/admin/user/list";
	}
	
	@RequestMapping(value = "/del/{id}")
	public String del(@PathVariable long id){
		um.deleteUser(id);
		return "forward:/admin/user/list";
	}
	
	@RequestMapping(value = "/edit")
	public String prePareEdit(Model m){
		m.addAttribute("departList", dm.getAllDepartments());
		return "user/userEdit";
	}
	
	@RequestMapping(value = "/edit/{id}")
	public String edit(@PathVariable long id, Model m){
		m.addAttribute("u", um.getUserById(id));
		m.addAttribute("departList", dm.getAllDepartments());
		return "user/userEdit";
	}

}
