package com.whr.dms.models;

import javax.persistence.Entity;
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
	private static final long serialVersionUID = 8509909032998444960L;

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
