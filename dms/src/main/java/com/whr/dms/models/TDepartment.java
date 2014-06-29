package com.whr.dms.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * 
 * @author Yaner
 *
 * @since 2012-7-16
 *
 * @Description
 * 科室
 */
@Entity
public class TDepartment extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8580981563203591359L;

	/**
	 * 科室名称
	 */
	@Size(max = 50)
	@NotNull
	private String name;

	/**
	 * 负责人
	 */
	@Size(max = 50)
	private String head;

	/**
	 * 电话
	 */
	@Size(max = 50)
	private String telephone;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="TDepartment_TRole",
				joinColumns = @JoinColumn(name = "departmentId"),
				inverseJoinColumns = @JoinColumn(name="roleId"))
	private List<TRole> roles;

	public List<TRole> getRoles() {
		return roles;
	}

	public void setRoles(List<TRole> roles) {
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
}
