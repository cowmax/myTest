package com.service;

import java.util.List;

public interface ParaDtSSkuService  {

	/**
	 * 获取所有产品SKU明细
	 */
	public List getAllParaDtSSku();
	
	/**
	 * 根据id获取数据caseId
	 */
	public List getCaseIdParaDtSSku(int caseId);
}
