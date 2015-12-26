package com.service;

import java.util.List;

import com.bean.Store;



public interface StoreService {
	/**
	 * 获取所有产品信息
	 *
	 */
	public List<Store> getStoreList();
	
}
