package com.service;

import java.util.List;

import com.bean.ParaValueType;

public interface ParaValueTypeService {
	/**
	 * ��ȡ���в�����Ϣ
	 */
	public List<ParaValueType> allParaSardataType();

	/**
	 * ͨ��Id��ȡ������Ϣ
	 */
	public ParaValueType findParaSardataTypeById(String valTypeId);

	/**
	 * ��Ӳ�����Ϣ
	 */
	public void saveParaSardataType(ParaValueType paraValueType);

}
