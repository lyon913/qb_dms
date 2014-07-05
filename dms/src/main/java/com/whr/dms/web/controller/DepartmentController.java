package com.whr.dms.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.whr.dms.models.TDepartment;
import com.whr.dms.models.TRole;
import com.whr.dms.service.DepartmentManager;
import com.whr.dms.service.RoleManager;
import com.whr.dms.web.ajax.ContentRangeHeader;
import com.whr.dms.web.ajax.DojoSort;
import com.whr.dms.web.ajax.PageableRange;

@Controller
@RequestMapping("/admin/department")
public class DepartmentController {
	@Resource
	DepartmentManager dm;
	
	@Resource
	RoleManager rm;
	
	@RequestMapping(value = "/departmentMana")
	public String noticeListPage(Model m) {
		return "department/departmentList";
	}

	/**
	 * 已发布通知列表JSON
	 * @param sort
	 * @param range
	 * @return
	 */
	@RequestMapping(value = "/list")
	public ResponseEntity<List<TDepartment>> departmentList(@RequestParam(required = false)String sort,@RequestHeader("Range") String range) {
		PageableRange pr = new PageableRange(range,new DojoSort(sort).toSpringSort());
		Page<TDepartment> page = dm.getDepartments(pr);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Range", new ContentRangeHeader(pr.getOffset(), page.getNumberOfElements(), page.getTotalElements()).toString());
		
		return new ResponseEntity<List<TDepartment>>(page.getContent(), headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(TDepartment d,Long[] roleIds){
		if(roleIds != null && roleIds.length > 0){
			List<TRole> roles = new ArrayList<TRole>();
			for(long roleId : roleIds){
				roles.add(rm.getById(roleId));
			}
			d.setRoles(roles);
		}
		dm.saveDepartment(d);
		return "forward:/admin/department/departmentMana";
	}
	
	@RequestMapping(value = "/del/{id}")
	public String del(@PathVariable long id){
		dm.deleteDepartment(id);
		return "forward:/admin/department/departmentMana";
	}
	
	@RequestMapping(value = "/edit")
	public String add(Model m){
		m.addAttribute("roles", rm.findAllRoles());
		return "department/departmentEdit";
	}
	
	@RequestMapping(value = "/edit/{id}")
	public String edit(@PathVariable long id, Model m){
		m.addAttribute("d", dm.getById(id));
		m.addAttribute("roles", rm.findAllRoles());
		return "department/departmentEdit";
	}
}
