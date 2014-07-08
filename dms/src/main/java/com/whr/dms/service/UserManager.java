package com.whr.dms.service;

import java.util.List;

import com.whr.dms.exceptions.ParameterCheckException;
import com.whr.dms.models.TUser;

public interface UserManager {
	/**
	 * 获取全部用户列表
	 * @return
	 */
	public List<TUser> getUserList();
	
	/**
	 * 根据id获取用户
	 * @param id
	 * @return
	 */
	public TUser getUserById(long id);
	
	/**
	 * 根据登录名获取用户
	 * @param loginName
	 * @return
	 */
	public TUser getTUserByLoginName(String loginName);
	
	/**
	 * 保存、更改用户
	 * @param u
	 * @throws ParameterCheckException 
	 */
	public void saveUser(TUser u) throws ParameterCheckException;
	
	/**
	 * 删除用户
	 * @param id
	 */
	public void deleteUser(long id);
	
	/**
	 * 设置用户科室
	 * @param userId
	 * @param departmentId
	 */
	public void setDepartment(long userId,long departmentId);

}
