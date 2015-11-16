package com.service;

import java.util.List;

import com.bean.PUser;

public interface PUserService {
	/**
	 * 获取所有用户信息
	 * @return 用户集合
	 */
	public List<PUser> allUsers();
	
	/**
	 * 通过userId获取用户信息
	 * @param userId
	 * @return PUser
	 */
	public PUser findUserById(String userId);
	
	/**
	 * 删除用户信息
	 * @param pu
	 */
	public void delUser(PUser pu);
	/**
	 * 修改用户信息
	 * @param pu
	 * @return
	 */
	public PUser mergeUser(PUser pu);
	/**
	 * 添加用户信息
	 * @param pu
	 */
	public void saveUser(PUser pu);
	
	/**
	 * 用户登录验证
	 * @param userName
	 * @param userPwd
	 * @return
	 */
	public PUser userLogin(String userId);
	
}
