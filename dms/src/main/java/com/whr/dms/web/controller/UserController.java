package com.whr.dms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.whr.dms.models.TDepartment;
import com.whr.dms.models.TRole;
import com.whr.dms.models.TUser;
import com.whr.dms.service.DepartmentManager;
import com.whr.dms.service.RoleManager;
import com.whr.dms.service.UserManager;

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
	public List<TRole> roleList(){
		return rm.findAllRoles();
	}

	/**
	 * 用户列表
	 * @param m
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model m) {
		m.addAttribute("userList", um.getUserList());
		return "user/userList";
	}

	/**
	 * 初始化创建用户的表单
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String initCreateUserForm(Model m) {
		TUser user = new TUser();
		m.addAttribute("user", user);
		return "user/userEdit";
	}

	/**
	 * 
	 * @param u
	 * @param departmentId
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(TUser u, long departmentId, Model m) {
		if (u.getId() == null
				&& um.getTUserByLoginName(u.getLoginName()) != null) {
			m.addAttribute("error", "该用户名已存在。");
			return "forward:/admin/user/edit";
		}
		u.setDepartment(dm.getById(departmentId));
		um.saveUser(u);
		return "forward:/admin/user/list";
	}

	@RequestMapping(value = "/del/{id}")
	public String del(@PathVariable long id) {
		um.deleteUser(id);
		return "forward:/admin/user/list";
	}

	@RequestMapping(value = "/edit")
	public String prePareEdit(Model m) {
		m.addAttribute("departList", dm.getAllDepartments());
		return "user/userEdit";
	}

	@RequestMapping(value = "/edit/{id}")
	public String edit(@PathVariable long id, Model m) {
		m.addAttribute("u", um.getUserById(id));
		m.addAttribute("departList", dm.getAllDepartments());
		return "user/userEdit";
	}

}
