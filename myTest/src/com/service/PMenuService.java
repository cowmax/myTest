package com.service;

import java.util.List;

import com.bean.PMenu;

public interface PMenuService {
	/**
	 * ����û�Id��ȡ���Ȩ��
	 * 
	 * @param �û�ID
	 * @return
	 */
	public List<PMenu> getNodeByUserId(String userId);
}
