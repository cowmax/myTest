package com.service;

import java.util.List;

import com.bean.ParaValueType;



public interface ParaValueTypeService {
	/**
	 * 获取所有参数信息
	 */
	public List<ParaValueType> allParaSardataType();	
	
	
	/**
	 * 通过Id获取参数信息
	 */
	public 	ParaValueType findParaSardataTypeById(String valTypeId);
	
	
	/**
	 * 添加参数信息
	 */
	public void saveParaSardataType(ParaValueType paraValueType);
	
}
