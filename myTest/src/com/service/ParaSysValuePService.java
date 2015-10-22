package com.service;

import java.util.List;

import com.bean.ParaSysValueP;

public interface ParaSysValuePService {
	/**
	 * 获取所有类目信息
	 */
	public List<ParaSysValueP> allParaSysValueP();

	/**
	 * 通过Id获取类目信息
	 */
	public ParaSysValueP findParaSysValuePById(String tyna);

	/**
	 * 添加类目信息
	 */
	public void saveParaSordataP(ParaSysValueP paraSysValueP);

	/**
	 * 通过id来删除参数信息
	 */
	public void delParaSysValuePById(ParaSysValueP tyna);

	/**
	 * 通过id来修改参数信息
	 */
	public ParaSysValueP updateParaSysValueP(ParaSysValueP tyna);

	/**
	 * 获取每页的数据
	 */
	public List<ParaSysValueP> getParaSysValuePPage(String page, String rows)
			throws Exception;

	/**
	 * 获取一共有多少条数据
	 * 
	 * @throws Exception
	 */
	public int getParaSysValuePTotal() throws Exception;
}
