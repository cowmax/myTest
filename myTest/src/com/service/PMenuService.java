package com.service;

import java.util.List;

import com.bean.PMenu;

public interface PMenuService {
	/**
	 * 根据用户Id获取相关权限
	 * @param 用户ID
	 * @return
	 */
	public List<PMenu> getNodeByUserId(String userId);
}
